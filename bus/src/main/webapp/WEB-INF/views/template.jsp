<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <tiles:insertAttribute name="head" />
</head>
<body>
    <tiles:insertAttribute name="menu" />
    <div class="container">
        <div style="min-height: 700px">
            <tiles:insertAttribute name="content" />       
        </div>
    </div>
</body>
</html>