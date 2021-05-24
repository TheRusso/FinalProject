<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>
<head>
    <title>Title</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Menu" scope="page" />

<body>
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${menuItems}">
        <c:set var="k" value="${k+1}"/>
        <tr>
            <td><c:out value="${k}"/></td>
            <td>${item.title}</td>
            <td>${item.price}</td>
            <td><input type="checkbox" name="itemId" value="${item.id}"/></td>
        </tr>
    </c:forEach>
</body>
</html>
