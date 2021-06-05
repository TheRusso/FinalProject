<%@include file="page.jsp"%>
<%@include file="taglib.jsp"%>

<div class="col-sm-2" style="background-color: #484848; min-height: 700px; padding-top: 40px; color: #ffffff">
    <h3>
        <a href="${pageContext.request.contextPath}/view/admin/orders"><fmt:message key="cabinet.orders" /></a>
    </h3>
    <h3>
        <a href="${pageContext.request.contextPath}/view/admin/users"><fmt:message key="cabinet.users" /></a>
    </h3>
    <h3>
        <a href="${pageContext.request.contextPath}/view/admin/add_item"><fmt:message key="cabinet.add_item" /></a>
    </h3>
    <h3 style="margin-top: 30px">
        <a href="${pageContext.request.contextPath}/view/logout"><fmt:message key="header.logout" /></a>
    </h3>
</div>