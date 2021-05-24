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
                            <form action="project">
                                <input type="hidden" name="command" value="media_page">
                                <input type="submit" class="text_from_input" value="<fmt:message key="header.media" /> ">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="project">
                                <input type="hidden" name="command" value="contacts_page">
                                <input type="submit" class="text_from_input" value="<fmt:message key="header.contacts" /> ">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="project">
                                <input type="hidden" name="command" value="shop_page">
                                <input type="submit" class="text_from_input" value="<fmt:message key="header.shop" /> ">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="project">
                                <input type="hidden" name="command" value="aboutUs_page">
                                <input type="submit" class="text_from_input" value="<fmt:message key="header.aboutUs" /> ">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="project">
                                <input type="hidden" name="command" value="press_page">
                                <input type="submit" class="text_from_input" value="<fmt:message key="header.press" /> ">
                            </form>
                        </li>

                        <c:if test="${empty sessionScope.user}">
                            <li class="nav-item">
                                <form action="project">
                                    <input type="hidden" name="command" value="enter">
                                    <input type="submit" class="text_from_input" value="<fmt:message key="header.login" /> ">
                                </form>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.user}">
                            <li class="nav-item">
                                <form action="project">
                                    <input type="hidden" name="command" value="logout">
                                    <input type="submit" class="text_from_input" value="<fmt:message key="header.logout" /> ">
                                </form>
                            </li>
                        </c:if>

                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>