<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Recent posts</h2>

<c:forEach items="${posts}" var="post">
	<div class="panel panel-default">
		<div class="panel-body">
			<h3><a href="${pageContext.request.contextPath}/post/${post.id}">${post.title}</a></h3>
			<p>${post.text}</p>
		</div>
		<div class="panel-footer">
			<span>By <strong><a href="${pageContext.request.contextPath}/user/${post.autor.login}">${post.autor.login}</a></strong></span>
			<span>at <strong><fmt:formatDate value="${post.creationDate}" pattern="HH:mm:ss dd.MM.YYYY"/></strong> </span>
		</div>
	</div>
</c:forEach>