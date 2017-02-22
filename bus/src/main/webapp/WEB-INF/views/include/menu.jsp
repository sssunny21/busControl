<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/bus">Bus Control System</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/bus/bus/busList.gnt">버스차량 관리</a></li>
				<li><a href="/bus/driver/driverList.gnt">운전기사 관리</a></li>
				<li><a href="/bus/bus/allocateList.gnt">배차현황 관리</a></li>
				<li><a href="/bus/operate/operateList.gnt">지도화면 관리</a></li>
				<li><a href="/bus/operate/operateStatistics.gnt">운행현황 통계표</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="not authenticated">
					<li><a href="/bus/home/login.gnt">로그인</a></li>
				</sec:authorize>
				<sec:authorize access="authenticated">
					<li style="margin-top: 15px;">${id }</li>
					<li><a href="/bus/home/logout.gnt">로그아웃</a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>
