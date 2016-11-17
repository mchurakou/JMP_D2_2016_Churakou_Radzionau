<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Awesome Blog</a>
			</div>
			<c:if test="${currUser != null}">
			<ul class="nav navbar-nav navbar-left">
				<li>
					<a href="${pageContext.request.contextPath}/post/create">New Post</a>
				</li>
			</ul>
			</c:if>
			<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${currUser != null}">
					<li>
						<p class="navbar-text">Hello <a href="${pageContext.request.contextPath}/user/${currUser.login}">${currUser.login}</a>!</p>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/logout">Log out</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="${pageContext.request.contextPath}/login">Log In</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/register">Register</a>
					</li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
	</div>
</nav>
