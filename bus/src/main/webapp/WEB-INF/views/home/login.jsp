<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>LOGIN</h1>

<div class="login">
	<form class="form-horizontal" method="POST" action="login_processing.gnt">
		<div class="form-group">
			<label for="inputID" class="col-sm-2 control-label">ID</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="id" placeholder="ID">
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" name="password" placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">LOGIN</button>
			</div>
		</div>
	</form>
</div>

<c:if test="${ param.error != null }">
	<div class="alert alert-error">로그인 실패</div>
</c:if>
