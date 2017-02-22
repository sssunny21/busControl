<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<div class="jumbotron">
	<h1 align="center">Bus Control System</h1>
	<p align="right">지앤티솔루션 인턴과제 -박은선-</p>
	<sec:authorize access="not authenticated">
		<p align="center">
			<a class="btn btn-primary btn-lg" href="/bus/home/login.gnt"
				role="button">Login</a>
		</p>
	</sec:authorize>
</div>
