<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Register" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>
<%@include file="/jspf/header.jsp"%>


<style>
    select *{
        color: black;
    }

    select{
        color: black;
    }
</style>

<section id="cover">
    <div id="cover-caption">
        <div id="container" class="container" style="max-width: 600px; margin-bottom: 200px;">
            <div class="row">
                <div class="col-sm-10 offset-sm-1 text-center">
                    <h1 class="display-3">Make order</h1>

                </div>
            </div>
            <div class="info-form">
                <form action="${pageContext.request.contextPath}/view/make_order" method="post" class="justify-content-center" style="padding: 30px 0;">
                    <div class="form-group">
                        <label>Country</label>
                        <select name="country_id">
                            <option value="1">Ukraine</option>
                            <option value="2">USA</option>
                            <option value="3">England</option>
                            <option value="4">Russia</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="city_input">City</label>
                        <input type="text" class="form-control" name="city" id="city_input" placeholder="City" required>
                    </div>

                    <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" class="form-control" name="address" id="address" placeholder="Address" required>
                    </div>

                    <div class="form-group">
                        <label for="address">Delivery Type</label>
                        <select name="delivery_type_id">
                            <option value="1">Nova poshta</option>
                            <option value="2">Ukr poshta</option>
                            <option value="3">Meest express</option>
                            <option value="4">Pickup</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Order</button>
                </form>
            </div>
        </div>
    </div>
</section>



<%@ include file="/jspf/footer.jsp"%>

</body>

</html>