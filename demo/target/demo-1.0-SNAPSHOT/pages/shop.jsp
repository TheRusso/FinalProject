<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Shop" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>

<%@include file="/jspf/header.jsp"%>

<div class="container">
    <div class="container">
        <img src="{% static 'images/separate_line.png' %}" alt="" class="img-fluid d-block mx-auto">
        <h1 class="display-2 text-center brusnika">Товари</h1>
    </div>

    <div class="row d-flex justify-content-around">

        <c:forEach var="item" items="${itemsList}">
            <div class="card col-xl-3 col-lg-5 col-md-8 col-sm-9 m-10-20">
                <a href="{% url 'item' i.id %}">
                    <img class="card-img-top" src="<c:url value="${item.img}" /> " alt="Card image">
                </a>
                <div class="card-body">
                    <a href="{% url 'item' i.id %}">
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
                        <p class="price text-dark">${item.price}грн</p>
                        <a href="#" class="btn btn-primary">Купити</a>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>


    <c:set var="currentPage" value="${currentPage}" scope="page"/>
    <c:set var="countOfPages" value="${countOfPages}" scope="page"/>
    <div class="d-flex justify-content-center mt-50">
        <ul class="pagination custom-pagination">
            <c:choose>
                <c:when test="${currentPage > 1}">
                    <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}">Previous</a></li>
                </c:when>
            </c:choose>

            <c:forEach begin="1" end="${countOfPages}" var="i">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <li class="page-item active"><a class="page-link" href="?page=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${currentPage < countOfPages}">
                    <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}">Next</a></li>
                </c:when>
            </c:choose>


        </ul>
    </div>
</div>

<%@ include file="/jspf/footer.jsp"%>

</body>

</html>