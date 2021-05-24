<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="404" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>

<h1 style="color: red">Error</h1>
<p>${errorMessage}</p>

</body>

</html>