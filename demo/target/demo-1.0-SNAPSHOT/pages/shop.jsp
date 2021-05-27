<%@ page import="java.util.Enumeration" %>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Shop" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>

<%@include file="/jspf/header.jsp"%>

<script>
    function addToCart(id){
        console.log('add' + id);
        $.ajax({
            async: false,
            type: "POST",
            url: ${pageContext.request.contextPath} + '/view',
            data_type: 'text',
            data: 'command=cart_actions&action=add&id=' + id,
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
    <div class="container">
        <img src="{% static 'images/separate_line.png' %}" alt="" class="img-fluid d-block mx-auto">
        <h1 class="display-2 text-center brusnika"><fmt:message key="shop.goods"/></h1>
    </div>

    <div style="margin-bottom: 20px">
        <form action="${pageContext.request.contextPath}/view/shop" method="GET" class="inner_pointer">
            <label>Sort by:</label><br>
            <input type="hidden" name="page" value="1">
            <div class="row">
                <div class="col-sm-2">
                    <label for="choice1">Name</label>
                    <input type="radio" id="choice1" name="sort" value="name"
                        <c:choose>
                             <c:when test="${param.sort.equals('name')}">
                                checked
                             </c:when>
                        </c:choose>
                    >
                </div>
                <div class="col-sm-2">
                    <label for="choice4">Price</label>
                    <input type="radio" id="choice4" name="sort" value="price"
                        <c:choose>
                            <c:when test="${param.sort.equals('price')}">
                                   checked
                            </c:when>
                        </c:choose>>
                </div>
                <div class="col-sm-2">
                    <label for="choice6">New</label>
                    <input type="radio" id="choice6" name="sort" value="id"
                        <c:choose>
                            <c:when test="${param.sort.equals('id')}">
                                   checked
                            </c:when>
                        </c:choose>
                    >
                </div>
            </div>

            <div class="row">
                <div class="col-sm-2">
                    <label for="choice31">ASC</label>
                    <input type="radio" id="choice31" name="order" value="ASC"
                        <c:choose>
                            <c:when test="${param.order.equals('ASC')}">
                                   checked
                            </c:when>
                        </c:choose>
                    >
                </div>
                <div class="col-sm-2">
                    <label for="choice34">DESC</label>
                    <input type="radio" id="choice34" name="order" value="DESC"
                        <c:choose>
                            <c:when test="${param.order.equals('DESC')}">
                                   checked
                            </c:when>
                        </c:choose>
                    >
                </div>
            </div>

            <hr>

            <label>Category</label>
            <div class="row">
                <div class="col-sm-2">
                    <label for="choice21">Dress</label>
                    <input type="checkbox" id="choice21" name="clothes"
                        <c:choose>
                            <c:when test="${param.clothes.equals('on')}">
                                   checked
                            </c:when>
                        </c:choose>
                    >
                </div>
                <div class="col-sm-2">
                    <label for="choice22">Music</label>
                    <input type="checkbox" id="choice22" name="music"
                        <c:choose>
                            <c:when test="${param.music.equals('on')}">
                                   checked
                            </c:when>
                        </c:choose>>
                </div>
                <div class="col-sm-2">
                    <label for="choice23">Other</label>
                    <input type="checkbox" id="choice23" name="other"
                        <c:choose>
                            <c:when test="${param.other.equals('on')}">
                                   checked
                            </c:when>
                        </c:choose>
                    >
                </div>
            </div>
            <input type="submit" id="filter_shop" class="text_from_input hover_effect">
        </form>
    </div>

    <div class="row d-flex justify-content-around">

        <c:forEach var="item" items="${itemsList}">
            <div class="card col-xl-3 col-lg-5 col-md-8 col-sm-9 m-10-20">
                <a href="${pageContext.request.contextPath}/view/item?item_id=${item.id}">
                    <img class="card-img-top" src="<c:url value="${item.img}" /> " alt="Card image">
                </a>
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/view/item?item_id=${item.id}">
                        <div class="info_product_card">
                            <h4 class="card-title text-dark">${item.title}</h4>
                            <p class="card-text text-dark">
                                <c:if test="${item.description.length() > 20}">
                                    ${fn:substring(item.description, 0, 20)}...
                                </c:if>
                                <c:if test="${item.description.length() <= 20}">
                                    ${item.description}
                                </c:if>
                            </p>
                        </div>
                    </a>
                    <div class="d-flex justify-content-between">
                        <p class="price text-dark">$${item.price}</p>
                        <a class="btn btn-primary" onclick="addToCart(${item.id})"><fmt:message key="shop.buy"/></a>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>


    <c:set var="currentPage" value="${currentPage}" scope="page"/>
    <c:set var="countOfPages" value="${countOfPages}" scope="page"/>
    <%
        StringBuilder path = new StringBuilder(request.getContextPath() + "?");


        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()){
            String paramName = (String) paramNames.nextElement();

            if(request.getParameter(paramName) != null){
                if(!paramName.equals("page")){
                    path.append(paramName);
                    path.append("=");
                    path.append(request.getParameter(paramName));
                    path.append("&");
                }
            }
        }
    %>
    <div class="d-flex justify-content-center mt-50">
        <ul class="pagination custom-pagination">
            <c:choose>
                <c:when test="${currentPage > 1}">
                    <li class="page-item"><a class="page-link" href="&page=<%= path %>page=${currentPage - 1}">Previous</a></li>
                </c:when>
            </c:choose>

            <c:forEach begin="1" end="${countOfPages}" var="i">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <li class="page-item active"><a class="page-link" href="<%= path %>page=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="<%= path %>page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${currentPage < countOfPages}">
                    <li class="page-item"><a class="page-link" href="<%= path %>page=${currentPage + 1}">Next</a></li>
                </c:when>
            </c:choose>


        </ul>
    </div>
</div>

<%@ include file="/jspf/footer.jsp"%>

</body>

</html>