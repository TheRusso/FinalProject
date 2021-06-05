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
                    <h1 class="display-3">Add item</h1>

                </div>
            </div>
            <div class="info-form">
                <form action="${pageContext.request.contextPath}/view/admin/add_item_command" class="justify-content-center">
                    <div class="form-group">
                        <label>Item title</label>
                        <input type="text" class="form-control" name="title" placeholder="Enter title" required>
                    </div>

                    <div class="form-group">
                        <label>Description</label>
                        <textarea class="form-control" name="description" id="exampleFormControlTextarea1" rows="3"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="">Price</label>
                        <input type="number" class="form-control" name="price" placeholder="Price" required>
                    </div>

                    <div class="form-group">
                        <label for="">Count</label>
                        <input type="number" class="form-control" name="count" placeholder="Count" required>
                    </div>



                    <div class="form-group">
                        <label for="">Category</label>
                        <select name="category" id="">
                            <option value="1">clothes</option>
                            <option value="2">music</option>
                            <option value="3">other</option>
                        </select>
                    </div>



                    <div class="row">
                        <div class="col-sm">

                        </div>

                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-primary">Add</button>
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