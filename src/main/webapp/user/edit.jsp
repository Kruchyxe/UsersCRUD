<%--
  Created by IntelliJ IDEA.
  User: krzysiek
  Date: 16.12.2020
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
        <a href="<c:url value="/user/list"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50"></i> Users list</a>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">User edit</h6>
        </div>
        <div class="card-body">
            <form method="post">
                <input type="hidden" name="id" value="${user.id}"/>
                <div class="form-group">
                    <label for="userName">Name</label>
                    <input value="${user.userName}" name="userName" type="text" class="form-control" id="userName" placeholder="Nazwa użytkownika">
                </div>
                <div class="form-group">
                    <label for="userEmail">Email</label>
                    <input value="${user.email}" name="userEmail" type="email" class="form-control" id="userEmail" placeholder="Email użytkownika">
                </div>
                <div class="form-group">
                    <label for="userPassword">Password</label>
                    <input name="userPassword" type="password" class="form-control" id="userPassword" placeholder="Hasło użytkownika">
                </div>

                <button type="submit" class="btn btn-primary">Edit</button>
            </form>

        </div>
    </div>
</div>
<%@ include file="/footer.jsp" %>








