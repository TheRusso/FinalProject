<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Media" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>

<%@include file="/jspf/header.jsp"%>

<div class="container block">
    <div class="row">
        <div class="col-md-6">
            <h3 style="padding-left: 20px">
                Новий альбом
            </h3>
            <h1 class="display-3">
                Motanka
            </h1>
            <br>
            <br>
            <br>
            <p style="color: #CCC">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p style="color: #CCC">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
        </div>
        <div class="col-md-6">
            <div class="d-flex justify-content-center">
                <iframe src="https://open.spotify.com/embed/artist/71U8pkzbOG5ynnXw3ZtnGf" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
            </div>
        </div>
    </div>
</div>

<%@ include file="/jspf/footer.jsp"%>

</body>

</html>