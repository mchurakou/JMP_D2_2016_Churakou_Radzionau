<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<s:url value="/resources/static/bootstrap/css/bootstrap.min.css"
	var="bootstrapCss" />
<s:url value="/resources/static/img/logo.jpg" var="logoImg" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${logoImg}" rel="icon" />
<title><t:insertAttribute name="pageTitle" /></title>
</head>

<body style="padding-top: 70px;">

	<t:insertAttribute name="header" />

	<div class="col-md-8 col-md-offset-2">
		<c:if test="${errors != null}">
			<c:forEach items="${errors}" var="error">
				<p class="bg-danger">${error}</p>
			</c:forEach>
		</c:if>
		<t:insertAttribute name="body" />
	</div>

	<t:insertAttribute name="footer" />


</body>


</html>