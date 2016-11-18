<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>${post.title}</h3>
<div class="panel panel-default">
	<div class="panel-body">
		<p>${post.text}</p>
		<c:if test="${f:length(post.images) > 0}">
			<p>
				<c:forEach items="${post.images}" var="img">
					<img src="${pageContext.request.contextPath}/img/${img.id}"
						class="img-responsive">
				</c:forEach>
			</p>
		</c:if>
	</div>
	<div class="panel-footer">
		<span>By <strong><a
				href="${pageContext.request.contextPath}/user/${post.autor.login}">${post.autor.login}</a></strong></span>
		<span>at <strong><fmt:formatDate
					value="${post.creationDate}" pattern="HH:mm:ss dd.MM.YYYY" /></strong>
		</span>
		<c:if test="${currUser != null and currUser.login == post.autor.login}">
			<a href="${pageContext.request.contextPath}/post/${post.id}/delete" type="button" data-toggle="tooltip" data-placement="left" title="Delete post" class="btn btn-danger pull-right"> <span
				class="glyphicon glyphicon glyphicon-remove-circle"
				aria-hidden="true"></span>
			</a>
		</c:if>
	</div>
</div>
<c:if test="${f:length(post.comments) > 0}">
	<h3>Comments:</h3>

	<c:forEach items="${post.comments}" var="comment">

		<div class="panel panel-default">
			<div class="panel-body">
				<p>${comment.text}</p>
			</div>
			<div class="panel-footer">
				<span>By <strong><a
						href="${pageContext.request.contextPath}/user/${comment.autor.login}">${comment.autor.login}</a></strong></span>
				<span>at <strong><fmt:formatDate
							value="${comment.date}" pattern="HH:mm:ss dd.MM.YYYY" /></strong>
				</span> 
				<c:if test="${currUser != null and currUser.login == comment.autor.login}">
					<a href="${pageContext.request.contextPath}/post/${post.id}/comment/${comment.id}/delete" type="button" data-toggle="tooltip" data-placement="left" title="Delete comment" class="btn btn-danger pull-right"> 
						<span class="glyphicon glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</a>
				</c:if>
			</div>
		</div>
	</c:forEach>
</c:if>

<c:if test="${currUser != null}">
	<h4>Your comment:</h4>
	<form
		action="${pageContext.request.contextPath}/post/${post.id}/comment"
		method="post">
		<div class="form-group">
			<textarea rows="5" cols="20" class="form-control" name="text"
				id="text"></textarea>
		</div>
		<button type="submit" class="btn btn-primary">Comment</button>
	</form>
</c:if>