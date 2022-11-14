<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<%--<shiro:hasPermission name="user:view">--%>
<h2>Picture Upload History</h2>

<div class="table-wrapper">
    <table class="fl-table" id="uploadHistoryTable">
        <thead>
        <tr>
            <th>User Name</th>
            <th>File Name</th>
            <th>Description</th>
            <th>Delete</th>
            <th>View</th>
        </tr>
        </thead>
        <tbody id="uploadHistoryBody">
        <%--            <tbody>--%>

        </tbody>
    </table>
</div>



<%--</shiro:hasPermission>--%>