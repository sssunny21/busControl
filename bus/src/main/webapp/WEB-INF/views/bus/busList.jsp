<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function working(busid) {
		location.href = "../driver/workingList.gnt?busid=" + busid;
		event.stopPropagation();
	}
	function reworking(busid, allocateid) {
		location.href = "../driver/reworkingList.gnt?id="+allocateid+"&&busid=" + busid;
		event.stopPropagation();
	}
	function cancel(allocateid) {
		location.href = "../driver/cancel.gnt?id=" + allocateid;
		event.stopPropagation();
	}
	$(function() {
		$("tr[data-url]").click(function() {
			location.href = $(this).attr("data-url");
		});
		$("[data-auto-submit=true]").change(function() {
			$(this).parents("form").submit();
		});
		$("[data-confirm]").click(function() {
			return confirm($(this).attr("data-confirm"));
		})
	});
	var error = "${errorMsg}";
	if (error)
		alert(error);
	
</script>
<h4>차량정보 목록</h4>
<jsp:useBean id="now" class="java.util.Date" />
<form:form method="post" modelAttribute="busList">
	<div class="row" style="margin-top: 20px;">
		<div class="col-lg-9" style="padding-left: 30px;">
			<div class="form-inline">
				<input name="bus_num" id="bus_num" class="form-control"
					value="${ bus_num ne null ? bus_num : " "}" />
				<button type="submit" class="btn btn-info" name="cmd"
					value="busSearch">번호 검색</button>
			</div>
		</div>
		<div class="col-lg-3" style="padding-left: 50px;">
			<div class="form-inline">
				<div id="column-right">
					<label>상태 : </label> &nbsp; <select id="state" name="state"
						class="form-control">
						<option value="운행" ${ state eq "운행" ? "selected" : ""}>운행</option>
						<option value="예비" ${ state eq "예비" ? "selected" : ""}>예비</option>
						<option value="정비" ${ state eq "정비" ? "selected" : ""}>정비</option>
						<option value="폐기" ${ state eq "폐기" ? "selected" : ""}>폐기</option>
					</select>
					<button type="submit" class="btn btn-default" name="cmd"
						value="selectByState">상태 조회</button>
				</div>
			</div>
		</div>
	</div>

	<div class="panel-body">
		<table class="table table-bordered" id="table_s">
			<thead>
				<tr>
					<th>차량번호</th>
					<th>승차인원</th>
					<th>도입년도</th>
					<th>차량상태</th>
					<th style="width: 170px;">배정</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="busList" items="${busList}">
					<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
					<tr data-url="busEdit.gnt?busid=${busList.busid}">
						<td>${busList.bus_num }</td>
						<td>${busList.limit_passenger }</td>
						<td>${busList.intro_year }</td>
						<td>${busList.state }</td>
						<td>
							<!-- 오늘이 아닌 날짜에 배정했을 때 --> 
							<c:if test="${busList.state eq '운행' and busList.allo_date ne today}">
							배정 미완료 &nbsp;&nbsp;
							<div class="btn btn-primary btn-xs" onclick="working('${busList.busid }')">배정</div>
							</c:if> 
							<!-- 오늘 배정했지만 이미 취소한 배정일 때 --> 
							<c:if test="${busList.state eq '운행' and busList.allo_date eq today and busList.cancel_check eq true}">
							배정 취소됨 &nbsp;&nbsp;
							<div class="btn btn-primary btn-xs" onclick="reworking('${busList.busid }','${busList.allocateid }')">배정</div>
							</c:if> 
							<!-- 오늘 배정했고 취소도 아직 안했을 때 취소 가능 --> 
							<c:if test="${busList.state eq '운행' and busList.allo_date eq today and busList.cancel_check eq false}">
							배정 완료 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="btn btn-danger btn-xs" onclick="cancel('${busList.allocateid }')">배정취소</div>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div align = right style="padding-right: 15px;">
		<button type="submit" class="btn btn-success" name="cmd" value="excel">엑셀 다운로드</button>
	</div>

</form:form>

<center>
	<a href="/bus/bus/busCreate.gnt" class="btn btn-default">새 버스 등록</a>
</center>