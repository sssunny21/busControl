<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
label {
	margin-top: 10px;
}
</style>
<script>
var error = "${errorMsg}";
if(error) alert(error);
</script>
<h4>기사 등록</h4>
<div class="col-lg-4">
<form:form method="post" modelAttribute="driver">
	<label>성명 </label>
	<form:input path="name" class="form-control" maxlength="30"/>
	<br>
	<label>생년월일 </label>
	<form:input path="birth" class="form-control datepicker"/>
	<br>
	<label>입사일자 </label>
	<form:input path="join_date" class="form-control datepicker"/>
	<br>
	<label>상태 </label>
	<form:select path="state" class="form-control">
		<form:option value="근무" label="근무" />
		<form:option value="휴가" label="휴가" />
		<form:option value="외근" label="외근" />
		<form:option value="퇴사" label="퇴사" />
	</form:select>
	<form:hidden path="driverid"/>
	<div>
		<input type="submit" class="btn btn-primary" style="margin-top: 10px;" value="저장" />
	</div>
</form:form>
</div>