<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<%-- <div class="col-md-8 col-md-offset-2">
	<div>${footerMsg}</div>
</div> --%>

<s:url value="/resources/static/js/jquery.js" var="jQueryJs" />
<s:url value="/resources/static/bootstrap/js/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${jQueryJs}"></script>
<script src="${bootstrapJs}"></script>

<script>
$('[data-toggle="tooltip"]').tooltip();
</script>