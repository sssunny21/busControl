<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4>버스 운행현황 통계표</h4>
<jsp:useBean id="now" class="java.util.Date" />

<form:form method="post" modelAttribute="operate">
	<div class="panel-body">
		<table class="table table-bordered" id="table_s">
			<thead>
				<tr>
					<th>운행일자</th>
					<th>차량번호</th>
					<th>노선운행횟수</th>
					<th>총 탑승인원</th>
					<th>1회 노선운행 평균 탑승인원</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="operateList" items="${operateList}">
					<tr>
						<td>${operateList.oper_date }</td>
						<td>${operateList.bus_num }</td>
						<td>${operateList.oper_count }</td>
						<td>${operateList.accu_passenger }</td>
						<td>${operateList.average}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form:form>
