<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Contacts" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>
<%@include file="/jspf/header.jsp"%>



<div class="container block">
    <div class="container">
        <img src="img/separate_line.png" alt="" class="img-fluid d-block mx-auto">
        <h1 class="display-2 text-center brusnika"><fmt:message key="contacts.contacts" /> </h1>
    </div>

    <form action="project">
        <input type="hidden" name="command" value="send_message_contacts">
        <div class="form-group">
            <label for="name"><fmt:message key="contacts.name" /></label>
            <input type="text" class="form-control" id="name" name="name" placeholder="<fmt:message key="contacts.your_name" />" required>
        </div>
        <div class="form-group">
            <label for="email"><fmt:message key="contacts.email" /></label>
            <input type="email" class="form-control" id="email" name="email" placeholder="<fmt:message key="contacts.email" />" required>
        </div>
        <div class="form-group">
            <label for="number"><fmt:message key="contacts.number" />:</label>
            <input type="number" class="form-control" id="number" name="number" placeholder="<fmt:message key="contacts.number" />" required>
        </div>
        <div class="form-group">
            <label for="pwd"><fmt:message key="contacts.message" /></label>
            <textarea class="form-control" name="message" id="pwd" rows="5" placeholder="<fmt:message key="contacts.message" />" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary bg-light text-dark float-right"><fmt:message key="contacts.submit" /></button>
    </form>

</div>

<div class="container block" style="margin-bottom: 100px">

    <div class="text-center block" style="margin-top: 100px">
        <h3><fmt:message key="contacts.management" /></h3>
        <h6 style="margin-top: 10px">Вероніка</h6>
        <p style="color: #ccc; margin-top: -10px">info@mysite.ua</p>
        <p style="color: #ccc; margin-top: -20px"><fmt:message key="contacts.phone" />: +380123456789</p>
    </div>
    <div class="text-center block">
        <h3><fmt:message key="contacts.tickets" /></h3>
        <h6 style="margin-top: 10px">Анатолій</h6>
        <p style="color: #ccc; margin-top: -10px">info@mysite.ua</p>
        <p style="color: #ccc; margin-top: -20px"><fmt:message key="contacts.phone" />: +380123456789</p>
    </div>
</div>



<%@ include file="/jspf/footer.jsp"%>

</body>

</html>