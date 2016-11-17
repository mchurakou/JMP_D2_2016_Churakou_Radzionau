<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<form:form action="${pageContext.request.contextPath}/post/create" method="post" commandName="createPostForm" enctype="multipart/form-data">
	<div class="form-group">
		<label for="title">Title</label>
		<form:input path="title" cssClass="form-control" />
	</div>
	<div class="form-group">
		<label for="text">Text</label>
		<form:textarea path="text" rows="20" cssClass="form-control"/>
	</div>
	<div class="form-group">
		<label for="pic">Pictures</label>
		<input name="pic[]" type="file"/>
		<input name="pic[]" type="file"/>
		<input name="pic[]" type="file"/>
		<input name="pic[]" type="file"/>
		<input name="pic[]" type="file"/>
	</div>
	<button type="submit" class="btn btn-primary">Post</button>
</form:form>