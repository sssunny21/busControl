<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
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
</script>
<h4>배차 현황</h4>
<jsp:useBean id="now" class="java.util.Date" />

<form:form method="post" modelAttribute="allocate">
	<div class="row" style="margin-top: 20px;">
		<div class="col-lg-8" style="padding-left: 30px;">
			<div class="form-inline">
			<div id="column-right">
				<select id="selection" name="selection" class="form-control">
					<option value="allo_date" ${ selection == "allo_date" ? "selected" : ""}>일자</option>
					<option value="bus_num" ${ selection == "bus_num" ? "selected" : ""}>차량번호</option>
					<option value="name" ${ selection == "name" ? "selected" : ""}>기사이름</option>
				</select>
				<input name="search" id="search" class="form-control" />
				<button type="submit" class="btn btn-info" name="cmd" value="allocateSearch">검색</button>
			</div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<table class="table table-bordered" id="table_s">
			<thead>
				<tr>
					<th>차량번호</th>
					<th>차랑상태</th>
					<th>배정된 기사</th>
					<th>배차 일자</th>
					<th>배차 여부</th>
					<th>취소 사유</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="allocateList" items="${allocateList}">
					<tr>
						<td>${allocateList.bus_num }</td>
						<td>${allocateList.state }</td>
						<td>${allocateList.name }</td>
						<td>${allocateList.allo_date }</td>
						<td>${allocateList.cancel_check == true ? "취소" : "완료"}</td>
						<td>${allocateList.cancel_reason }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div align = right style="padding-right: 15px;">
		<button type="submit" class="btn btn-success" name="cmd" value="excel">엑셀 다운로드</button>
	</div>
</form:form>
