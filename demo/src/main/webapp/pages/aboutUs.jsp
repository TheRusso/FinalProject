<%@ taglib prefix="date" uri="http://mycompany.com" %>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | About Us" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>

<%@include file="/jspf/header.jsp"%>

<div class="container block">
    <div class="container">
        <img src="<c:url value="/pages/motanka/img/separate_line.png" /> " alt="" class="img-fluid d-block mx-auto">
        <h1 class="display-2 text-center brusnika">Про нас</h1>
    </div>

    <div class="row">
        <div class="col-md-6">
            <blockquote class="blockquote">
                <p>"For 50 years, WWF has been protecting the future of nature. The worlds leading conservation organization, WWF works in 100 countries and is supported by 1.2 million members in the United States and close to 5 million globally."</p>
                <footer class="blockquote-footer">From WWFs website</footer>
            </blockquote>
            <br>
            <br>
            <br>
            <br>
            <p style="color: #ccc">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <br>
            <p style="color: #ccc">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
        </div>
        <div class="col-md-6">
            <img src="<c:url value="/pages/motanka/img/acoustic-1851248_1920.jpg" />" alt="" class="img-fluid mt-50">
            <img src="<c:url value="/pages/motanka/img/acoustic-1851248_1920.jpg" />" alt="" class="img-fluid mt-50">
        </div>
    </div>

</div>

<%@ include file="/jspf/footer.jsp"%>

</body>

</html>