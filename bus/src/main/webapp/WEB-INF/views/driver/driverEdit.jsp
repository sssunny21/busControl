<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
label { margin-top: 10px; }
</style>
<script>
var error = "${errorMsg}";
if(error) alert(error);
$(function(){
	$(document).on("keyup", "input:text[datetimeOnly]", function() {$(this).val( $(this).val().replace(/[^0-9:\-]/gi,"") );});
});
</script>
<h4>기사 정보 수정</h4>
<div class="col-lg-4">
<form:form method="post" modelAttribute="driver">
	<label>이름 </label>
	<form:input path="name" class="form-control" maxlength="30"/>
	<br>
	<label>생년월일 </label>
	<form:input path="birth" class="form-control datepicker" datetimeOnly="true"/>
	<br>
	<label>입사일자 </label>
	<form:input path="join_date" class="form-control datepicker" datetimeOnly="true"/>
	<br>
	<label>상태 </label>
	<form:select path="state" class="form-control">
		<form:option value = "근무" label = "근무" />
		<form:option value = "휴가" label = "휴가" />
		<form:option value = "외근" label = "외근" />
		<form:option value = "퇴사" label = "퇴사" />
	</form:select>
	<div style="margin-top: 10px;" align = "center">
		<input type="submit" class="btn btn-primary" value="저장" />
		<a href="/bus/driver/driverDelete.gnt?driverid=${driver.driverid}" class="btn btn-danger">삭제</a>
		<a href="driverList.gnt" class="btn btn-default">목록으로
        </a>
	</div>
</form:form>
</div>