<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<style>
.jumbotron{
background : #000000;
	opacity : 0.7;
	width : 100%;
	height : 400px;
	}
</style>

<div class="jumbotron" align="left">
	<h1 align="center" style="color:white; margin-top: 60px; margin-bottom: 100px;">Bus Control System
	</h1>
	<sec:authorize access="not authenticated">
		<p align="center">
			<a class="btn btn-primary btn-lg" href="/bus/home/login.gnt"
				role="button">Login</a>
		</p>
	</sec:authorize>
	<p align="right">
		<img src = "http://imageshack.com/a/img922/1766/IIH5dL.png"/>
	<img src = "http://imageshack.com/a/img922/7168/vJVVAH.png"/>
	<img src = "http://imageshack.com/a/img922/1766/IIH5dL.png"/>
	</p>
</div>

