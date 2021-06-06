<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | ${shop_item.title}" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>

<%@ include file="/jspf/header.jsp"%>

<script>
    function addToCart(id){
        console.log('add' + id);
        $.ajax({
            async: false,
            type: "POST",
            url: "/view/cart_actions",
            data_type: 'text',
            data: 'action=add&id=' + id,
            error: function (){
                alert('Error');
            },
            success: function (){
                alert('Success');
            }
        });
    }
</script>

<div class="container">
    <div class="row bg-white">
        <div class="col-sm-6">
            <img src="<c:url value="${pageContext.request.contextPath}${shop_item.img}"/>" alt="" class="img-fluid">
        </div>
        <div class="col-sm-6 text-dark">
            <h1 class="display-4 text-center">${shop_item.title}</h1>
            <h4 class="text-center" style="color: black">$${shop_item.price}</h4>
            <div class="row text-center">
                <div class="col-sm-12 text-dark text-center">
                    <a class="btn btn-primary" style="margin-top: 40px;padding: 10px 20px;color: white;" onclick="addToCart(${shop_item.id})">В ведро</a>
                </div>
                <div class="col-sm-12 text-dark text-center">
                    <a href="/view/cart" style="margin-top: 40px;color: black;text-decoration: underline;/* color: white; */">Ведро</a>
                </div>
            </div>


            <div class="mt-50">
                <p class="text-dark">
                    ${shop_item.description}
                </p>
            </div>
        </div>
    </div>
</div>

<%@include file="/jspf/footer.jsp"%>

</body>

</html>
