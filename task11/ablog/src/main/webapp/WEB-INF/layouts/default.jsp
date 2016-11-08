<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<html>
<head>
<title>title</title>
</head>
<body>
	<t:insertAttribute name="header"/>
	<t:insertAttribute name="body"/>
	<t:insertAttribute name="footer"/>
</body>
</html>