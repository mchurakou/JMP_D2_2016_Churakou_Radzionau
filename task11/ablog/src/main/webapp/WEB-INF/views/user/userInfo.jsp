<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<c:if test="${user != null}">
	
	<c:if test="${f:length(user.avatar) > 0}">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Avatar</h3>
		</div>
		<div class="panel-body"><img src="${pageContext.request.contextPath}/user/${user.login}/avatar" alt="" class="img-rounded img-responsive"></div>
	</div>
	</c:if>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Name</h3>
		</div>
		<div class="panel-body">${user.name}</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Email</h3>
		</div>
		<div class="panel-body">${user.email}</div>
	</div>
	<c:if test="${currUser != null}">
		<c:if test="${currUser.login == user.login}">
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/user/edit" role="button">Edit Info</a>
		</c:if>
	</c:if>
</c:if>