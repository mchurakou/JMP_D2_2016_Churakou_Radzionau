<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form action="${pageContext.request.contextPath}/user/edit" method="post" commandName="editUserForm" enctype="multipart/form-data">
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
		<input name="avatar" type="file"/>
	</div>
	<button type="submit" class="btn btn-primary">Save</button>
</form:form>