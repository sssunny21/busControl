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
    });
});

</script>
<h4>기사 선택</h4>
<jsp:useBean id="now" class="java.util.Date" />
<form:form method="post" modelAttribute="driverList">
	<div class="table-responsive">
		<table class="table table-bordered" id="table_s">
			<thead>
				<tr>
					<th>성명</th>
					<th>생년월일</th>
					<th>입사일자</th>
					<th>상태</th>
					<th>운행여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="driverList" items="${driverList}">
					<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
					<tr data-url="/bus/driver/selectionDriver.gnt?d_id=${driverList.driverid }&b_id=${busid}&c=${today}">
						<td>${driverList.name }</td>
						<td>${driverList.birth }</td>
						<td>${driverList.join_date }</td>
						<td>${driverList.state }</td>
						<td>
						<c:choose>
							<c:when test="${driverList.allocateid == 0}">
								미운행
							</c:when>
							<c:otherwise>
								운행
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form:form>

