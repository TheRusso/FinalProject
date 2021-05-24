<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Register" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>
<%@include file="/jspf/header.jsp"%>



<section id="cover">
    <div id="cover-caption">
        <div id="container" class="container" style="max-width: 600px; margin-bottom: 200px;">
            <div class="row">
                <div class="col-sm-10 offset-sm-1 text-center">
                    <h1 class="display-3">Register</h1>

                </div>
            </div>
            <div class="info-form">
                <form action="view" class="justify-content-center" method="post">
                    <input type="hidden" name="command" value="insert_user">
                    <div class="form-group">
                        <label for="exampleInputEmail1"><fmt:message key="register.email" /> </label>
                        <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="<fmt:message key="register.enter_email" /> " required>
                        <small id="emailHelp" class="form-text text-muted"><fmt:message key="register.we_never_share_email" /></small>
                    </div>

                    <div class="form-group">
                        <label for="first_name"><fmt:message key="register.first_name" /></label>
                        <input type="text" name="first_name" class="form-control" id="first_name" placeholder="Joey" required>
                    </div>

                    <div class="form-group">
                        <label for="second_name"><fmt:message key="register.last_name" /></label>
                        <input type="text" name="second_name" class="form-control" id="second_name" placeholder="Jordison" required>
                    </div>

                    <div class="form-group">
                        <label for="password"><fmt:message key="register.password" /></label>
                        <input type="password" name="password" class="form-control" id="password" placeholder="<fmt:message key="register.password" /> " required>
                    </div>
                    <div class="form-group">
                        <label for="confirm_password"><fmt:message key="register.conf_password"/> </label>
                        <input type="password" name="conf_password" class="form-control" id="confirm_password" placeholder="<fmt:message key="register.conf_password"/>" required>
                    </div>


                    <button type="submit" class="btn btn-primary"><fmt:message key="register.submit" /> </button>
                </form>
            </div>
        </div>
    </div>
</section>



<%@ include file="/jspf/footer.jsp"%>

</body>

</html>