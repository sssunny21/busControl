<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    });
});
$(function(){
	$('#excel').click(function() {
	    alert("다운로드 완료!");
	})
});
var error = "${errorMsg}";
if(error) alert(error);
</script>
<h4>기사정보 목록</h4>

<form:form method="post" modelAttribute="driverList">
	<div class="row" style="margin-top: 20px;">
		<div class="col-lg-8">
			<div class="form-inline">
				<label>상태 : </label> &nbsp; <select id="state" name="state"
					class="form-control">
					<option value="근무" ${ state == "근무" ? "selected" : ""}>근무</option>
					<option value="휴가" ${ state == "휴가" ? "selected" : ""}>휴가</option>
					<option value="외근" ${ state == "외근" ? "selected" : ""}>외근</option>
					<option value="퇴사" ${ state == "퇴사" ? "selected" : ""}>퇴사</option>
				</select>
				<button type="submit" class="btn btn-default" name="cmd"
					value="selectByState">상태 조회</button>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="form-inline">
				<div id="column-right">
					<input name="name" id="name" class="form-control" />
					<button type="submit" class="btn btn-info" name="cmd"
						value="nameSearch">이름 검색</button>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="table_s">
				<thead>
					<tr>
						<th>성명</th>
						<th>생년월일</th>
						<th>입사일자</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="driverList" items="${driverList}">
						<tr data-url="driverEdit.gnt?driverid=${driverList.driverid}">
							<td>${driverList.name }</td>
							<td>${driverList.birth }</td>
							<td>${driverList.join_date }</td>
							<td>${driverList.state }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div align = right><button type="submit" class="btn btn-primary" id = "excel" name="cmd" value="excel">엑셀 다운로드</button></div>
</form:form>
<center><a href="/bus/driver/driverCreate.gnt" class="btn btn-primary">새 기사 등록</a></center>