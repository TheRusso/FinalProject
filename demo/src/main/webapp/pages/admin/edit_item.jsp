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
        <div id="container" class="container" style="max-width: 600px; margin-bottom: 200px; margin-top:100px">
            <div class="row">
                <div class="col-sm-10 offset-sm-1 text-center">
                    <h1 class="display-3">Edit item</h1>

                </div>
            </div>
            <div class="info-form">
                <form action="${pageContext.request.contextPath}/view/admin/edit_item_command" class="justify-content-center"  enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${item.id}">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Item title</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" name="title" value="${item.title}" aria-describedby="emailHelp" placeholder="Enter title" required>
                    </div>

                    <div class="form-group">
                        <label>Description</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="description">${item.description}</textarea>
                    </div>

                    <div class="form-group">
                        <label>Price (Format 000.00)</label>
                        <input type="text" pattern="[\d]+.[\d]{0,2}"  class="form-control" name="price" value="${item.price}" placeholder="Price" required>
                    </div>

<%--                    <div class="form-group">--%>
<%--                        <input type="file" name="file" class="form-control-file" accept="image/png, image/gif, image/jpeg">--%>
<%--                    </div>--%>


                    <div class="row">
                        <div class="col-sm">
                            <a href="${pageContext.request.contextPath}/view/admin/delete_item?id=${item.id}">Delete</a>
                        </div>

                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</section>



<%@ include file="/jspf/footer.jsp"%>

</body>

</html>