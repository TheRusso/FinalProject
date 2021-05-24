

<head>
    <title>
        ${title}
    </title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/pages/motanka/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/pages/motanka/css/style2.css"/>">
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/pages/motanka/css/carousel.css"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="<c:url value="${pageContext.request.contextPath}/pages/motanka/js/carousel.js"/>"></script>
    <script src="<c:url value="${pageContext.request.contextPath}/pages/motanka/js/custom.js"/>"></script>
    <script src="<c:url value="${pageContext.request.contextPath}/pages/motanka/js/confirm_pass.js"/>" defer></script>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/pages/motanka/img/logo.ico" type="image/png">
</head>

<%
    HttpSession session1 = request.getSession();
%>