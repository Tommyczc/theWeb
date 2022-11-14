<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron text-center">
    <h1>Welcome, user ${username}</h1>
    <p class="lead text-info">Your Role: <c:forEach var="role" items="${roleList}" varStatus="loop">
        [ <code>${role.roleName}</code> ]
    </c:forEach></p>
    <p class="lead text-info">Your Permission: <c:forEach var="permission" items="${permissionList}" varStatus="loop">
        [ <code>${permission.permission}</code> ]
    </c:forEach></p>

    <shiro:hasRole name="user">
        <p class="lead bg-success">
            <code>
                User Read Only:
            </code>
            User can upload the picture or view the picture, and they can search other user infomation
        </p>
    </shiro:hasRole>

    <shiro:hasRole name="admin">
        <p class="lead bg-primary">
            <code>
                Admin Read Only:
            </code>
            Admin can view, change, update and delete user information.
        </p>
    </shiro:hasRole>
</div>