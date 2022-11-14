<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<%--<shiro:hasPermission name="user:view">--%>
    <h2>Picture Upload History</h2>


<!-- 触发弹窗 - 图片改为你的图片地址 -->
<%--<img decoding="async" loading="lazy" id="myImg" src="img.jpg" alt="文本描述信息" width="300" height="200">--%>

<!-- 弹窗 -->
<%--<div id="myModal" class="modal">--%>

<%--    <!-- 关闭按钮 -->--%>
<%--    <span class="close" onclick="document.getElementById('myModal').style.display='none'">&times;</span>--%>
<%--&lt;%&ndash;    <input type="text">&ndash;%&gt;--%>
<%--    <!-- 弹窗内容 -->--%>
<%--    <img decoding="async" class="modal-content" id="img01" max-width=60% max-height=60%>--%>

<%--    <!-- 文本描述 -->--%>
<%--    <div id="caption"></div>--%>
<%--</div>--%>

    <div class="table-wrapper">
        <table class="fl-table" id="pictureViewTable">
            <thead>
            <tr>
                <th>File Name</th>
                <th>Description</th>
                <th>Delete</th>
                <th>View</th>
            </tr>
            </thead>
            <tbody id="pictureViewTableBody">
<%--            <tbody>--%>

            </tbody>
        </table>
    </div>



<%--</shiro:hasPermission>--%>