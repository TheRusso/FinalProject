<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Cart" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>

<%@ include file="/jspf/header.jsp"%>

<style>
    .bg-white * {
        color: black;
    }
</style>

<script>
    function updateTextInput1(val, id) {
        document.getElementById('num' + id).value = val;

        $.ajax({
            async: false,
            type: "POST",
            url: "/view/update_quantity",
            data_type: 'text',
            data: 'id=' + id + '&quantity=' + val
        });
    }

    function updateRangeInput1(val, id) {
        document.getElementById('range' + id).value = val;
    }

    function deleteFromCart(id){
        console.log('add' + id);
        $.ajax({
            async: false,
            type: "POST",
            url: "/view/cart_actions",
            data_type: 'text',
            data: 'command=cart_actions&action=delete&id=' + id,
            error: function (){
                alert('Error');
            },
            success: function (){
                alert('Success');
            }
        });


    }
</script>

<form action="${pageContext.request.contextPath}/view/cart_actions">
    <input type="hidden" name="action" value="order">
    <div class="container">
        <div class="row bg-white">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Items</th>
                </tr>
                </thead>
                <tbody>

                <c:set var="k" value="1"/>
                <c:forEach var="item" items="${cart_items}">
                    <tr>
                        <th scope="row">${k}</th>
                        <c:set var="k" value="${k + 1}"/>

                        <input type="hidden" name="item_id" value="${item.id}">

                        <td>
                            <img src="${pageContext.request.contextPath}${item.img}" height="150px" alt="" style="float: left">
                            <h4 style="padding-left: 170px;" class="text-center">${item.title}</h4>
                            <p class="text-center">${item.price}</p>
                            <div style="margin-left: 390px; position: absolute">
                                <p class="text-center">Quantity</p>
                                <input type="range" id="range${item.id}" onchange="updateTextInput1(this.value, ${item.id})" value="${item.quantity}" min="1" max="20">
                                <br>
                                <input type="number" id="num${item.id}" onchange="updateRangeInput1(this.value, ${item.id})" name="quantity" value="${item.quantity}" min="1" max="20">
                            </div>
                        </td>

                        <td>
                            <a href="${pageContext.request.contextPath}/view/cart" onclick="deleteFromCart(${item.id})">Delete</a>

                        </td>
                    </tr>
                </c:forEach>


                </tbody>

            </table>
            <c:choose>
                <c:when test="${k == 1}">
                    <div class="container" style="height: 300px">
                        <h1 style="color: black; margin-top: 50px" class="text-center">
                            Empty
                        </h1>
                    </div>
                </c:when>
                <c:otherwise>
                    <input type="submit" class="text_from_input" value="Заказать" style="margin: 30px">
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</form>

<%@include file="/jspf/footer.jsp"%>

</body>

</html>
