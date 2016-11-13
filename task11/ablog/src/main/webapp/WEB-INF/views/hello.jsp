<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div>${helloBodyMsg}</div>

<div>${user.login}</div>
<div>${user.name}</div>
<div>${user.email}</div>
<div>${user.password}</div>
<div>${user.userType}</div>

<button class="btn btn-default" type="submit">Button</button>