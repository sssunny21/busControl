<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
label { margin-top: 10px; }
</style>
<script>
//메세지 출력
var error = "${errorMsg}";
if(error) alert(error);
//숫자만 입력 가능하도록
$(function(){
	 $(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
});
</script>
<h4>버스 정보 수정</h4>
<div class = "col-lg-4">
<form:form method = "post" modelAttribute = "bus">
	<label>차량번호 </label>
	<form:input path = "bus_num" class = "form-control" maxlength = "8"/>
	<br>
	<label>승차인원 </label>
	<form:input path = "limit_passenger" class = "form-control" numberOnly = "true" />
	<br>
	<label>도입년도 </label>
	<form:input path = "intro_year" class = "form-control" numberOnly = "true" />
	<br>
	<label>상태 </label>
	<form:select path = "state" class = "form-control">
		<form:option value = "운행" label = "운행" />
		<form:option value = "예비" label = "예비" />
		<form:option value = "정비" label = "정비" />
		<form:option value = "폐기" label = "폐기" />
	</form:select>
	<div style="margin-top: 10px;" align = "center">
		<input type = "submit" class = "btn btn-primary" value = "저장" />
		<a href = "/bus/bus/busDelete.gnt?busid=${bus.busid}" class = "btn btn-danger">삭제</a>
		<a href = "busList.gnt" class = "btn btn-default">목록으로</a>
	</div>
</form:form>
</div>