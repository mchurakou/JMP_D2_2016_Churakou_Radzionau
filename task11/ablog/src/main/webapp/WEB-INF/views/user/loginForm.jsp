<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<form:form action="login" method="post" commandName="credentials">
	<div class="form-group">
		<label for="login">Login</label>
		<form:input path="login" cssClass="form-control" />
	</div>
	<div class="form-group">
		<label for="password">Password</label>
		<form:password path="password" cssClass="form-control" />
	</div>
	<button type="submit" class="btn btn-default btn-primary">Login</button>
</form:form>