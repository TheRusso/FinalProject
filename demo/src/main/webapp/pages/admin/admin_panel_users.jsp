<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jspf/page.jsp" %>
<%@ include file="/jspf/taglib.jsp"%>

<html>

<c:set var="title" value="Motanka | Login" scope="page"/>
<%@ include file="/jspf/head_page.jsp"%>

<body>
<%@include file="/jspf/header.jsp"%>

<script>
    function updateRole(user_id, role_id){
        $.ajax({
            async: false,
            type: "POST",
            url: "/view/update_role",
            data_type: 'text',
            data: 'user_id=' + user_id + '&role_id=' + role_id
        });
    }

    function banUser(user_id, ban){
        $.ajax({
            async: false,
            type: "POST",
            url: '/view/ban_user',
            data_type: 'text',
            data: 'user_id=' + user_id + '&ban=' + ban,
            success: function (){
                alert("Success")
            },
            error: function (){
                alert("error")
            }
        });

        if(ban == 1){
            $('.ban_user' + user_id).text('Unban');
            $('.ban_user' + user_id).val(0);
        }else{
            $('.ban_user' + user_id).text('Ban');
            $('.ban_user' + user_id).val(1);
        }

        location.reload();
    }
</script>

<div class="container-fluid">
    <div class="row">
            
        <%@include file="/jspf/adminSideBar.jsp"%>
        
        <div class="col-sm-1">

        </div>
        <div class="col-sm-9" style="padding-right: 50px;">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Second Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Role</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${users_bean}" var="bean">
                    <c:choose>
                        <c:when test="${bean.isBanned == 1}">
                            <tr style="background-color: #ff00004f">
                        </c:when>
                        <c:otherwise>
                            <tr>
                        </c:otherwise>
                    </c:choose>
                        <th scope="row">${bean.id}</th>
                        <td>${bean.first_name}</td>
                        <td>${bean.second_name}</td>
                        <td>${bean.email}</td>
                        <td>
                            <select name="role" id="" onchange="updateRole(${bean.id}, value)">
                                <option value="1" <c:choose><c:when test="${bean.role_id == 1}">selected</c:when></c:choose> >User</option>
                                <option value="0" <c:choose><c:when test="${bean.role_id == 0}">selected</c:when></c:choose> >Admin</option>
                            </select>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${bean.isBanned == 0}">
                                    <button class="ban_user${bean.id}" style="padding: 0 10px; color: black; cursor: pointer" onclick="banUser(${bean.id}, value)" value="1">
                                        Ban
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button class="ban_user${bean.id}" style="padding: 0 10px; color: black; cursor: pointer" onclick="banUser(${bean.id}, value)" value="0">
                                        Unban
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</div>



<%@ include file="/jspf/footer.jsp"%>

</body>

</html>