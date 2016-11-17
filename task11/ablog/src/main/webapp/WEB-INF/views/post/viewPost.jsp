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
					<img src="${pageContext.request.contextPath}/img/${img.id}" class="img-responsive">
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
	</div>
</div>
<c:if test="${f:length(post.comments) > 0}">
<h3>Comments:</h3>
</c:if>

<c:if test="${currUser != null}">
	<h4>Your comment:</h4>
	<form action="${pageContext.request.contextPath}/post/${post.id}/comment" method="post">
		<div class="form-group">
			<textarea rows="5" cols="20" class="form-control" name="text" id="text"></textarea>
		</div>
		<button type="submit" class="btn btn-primary">Comment</button>
	</form>
</c:if>