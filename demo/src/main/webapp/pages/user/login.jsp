<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Login" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>
<%@include file="/jspf/header.jsp"%>



<section id="cover">
    <div id="cover-caption">
        <div id="container" class="container" style="max-width: 600px; margin-bottom: 200px; margin-top:100px">
            <div class="row">
                <div class="col-sm-10 offset-sm-1 text-center">
                    <h1 class="display-3">Login</h1>

                </div>
            </div>
            <div class="info-form">
                <form action="${pageContext.request.contextPath}/view/enter" method="post" class="justify-content-center">
                    <input type="hidden" name="to" value="${to}">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" required>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password" required>
                    </div>

                    <div class="row">
                        <div class="col-sm">

                        </div>

                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </div>

                </form>

                <div class="container div-wrapper" style="margin-top: 20px">
                    <div class="row">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-11">
<%--                            <form action="view/register" method="get">--%>
<%--                                <label for="register"></label>--%>
<%--                                <input class="text_from_input" id="register" type="submit" value="<fmt:message key="register.register"/>"  style="margin-top: 10px;text-decoration: underline;">--%>
<%--                            </form>--%>
                            <fmt:message key="register.newUser"/> <a href="${pageContext.request.contextPath}/view/register<c:choose><c:when test="${to != null}">?to=${to}</c:when></c:choose>" style="margin-left: 10px;color: white;text-decoration: underline;"><fmt:message key="register.register"/> </a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>



<%@ include file="/jspf/footer.jsp"%>

</body>

</html>