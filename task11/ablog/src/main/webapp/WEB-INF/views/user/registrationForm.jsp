<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<form:form action="${pageContext.request.contextPath}/register" method="post" commandName="registrationForm" enctype="multipart/form-data">
	<div class="form-group">
		<label for="login">Login</label>
		<form:input path="login" cssClass="form-control" />
	</div>
	<div class="form-group">
		<label for="password">Password</label>
		<form:password path="password" cssClass="form-control" />
	</div>
	<div class="form-group">
		<label for="passwordRepeat">Repeat password</label>
		<form:password path="passwordRepeat" cssClass="form-control" />
	</div>
	<div class="form-group">
		<label for="name">Name</label>
		<form:input path="name" cssClass="form-control" />
	</div>
	<div class="form-group">
		<label for="email">Email</label>
		<form:input path="email" cssClass="form-control" />
	</div>
	<div class="form-group">
		<label for="avatar">Avatar</label>
		<input name="user_avatar" type="file"/>
	</div>
	<button type="submit" class="btn btn-primary">Register account</button>
</form:form>