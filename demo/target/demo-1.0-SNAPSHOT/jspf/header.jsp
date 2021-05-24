<%@include file="page.jsp"%>
<%@include file="taglib.jsp"%>

<c:if test="${sessionScope.lang == null}">
    <fmt:setLocale value="ru_RU"/>
</c:if>
<c:if test="${sessionScope.lang.equals('ru')}">
    <fmt:setLocale value="ru_RU"/>
</c:if>
<c:if test="${sessionScope.lang.equals('en')}">
    <fmt:setLocale value="en_US"/>
</c:if>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-lg-2">
            <a href="/"><div id="logo_img" class="mx-auto"></div></a>
        </div>
        <div class="col-sm-12 col-lg-10">
            <nav class="navbar navbar-expand-lg navbar-dark  justify-content-center">
                <button class="navbar-toggler but_tog" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse d-lg-flex flex-row-reverse" id="collapsibleNavbar">
                    <ul class="navbar-nav text_nav">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/view/media"><fmt:message key="header.media" /></a>
<%--                            <form action="view" method="post">--%>
<%--                                <input type="hidden" name="command" value="media_page">--%>
<%--                                <input type="submit" class="text_from_input" value="<fmt:message key="header.media" /> ">--%>
<%--                            </form>--%>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/view/contacts"><fmt:message key="header.contacts" /></a>

                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/view/shop"><fmt:message key="header.shop" /></a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/view/about_us"><fmt:message key="header.aboutUs" /></a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/view/press_list"><fmt:message key="header.press" /></a>
                        </li>

                        <c:if test="${empty sessionScope.user}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/view/login"><fmt:message key="header.login" /></a>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.user}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/view/logout"><fmt:message key="header.logout" /></a>
                            </li>
                        </c:if>

                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>