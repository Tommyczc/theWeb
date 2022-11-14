<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <!------ Include the above in your HEAD tag ---------->
    <link href="../bootstrap-4.1.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="../bootstrap-4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
    <%--main page files--%>
    <link rel="stylesheet" type="text/css" href="../css/welcomePage.css"/>
    <script src="../js/welcomePage.js"></script>

    <%--upload picture files--%>
    <link rel="stylesheet" type="text/css" href="../css/userPage/userUploadPage.css"/>
    <script src="../js/userPage/userUploadPage.js"></script>

    <%--view pictures files--%>
    <link rel="stylesheet" type="text/css" href="../css/userPage/userViewPage.css"/>
    <link rel="stylesheet" type="text/css" href="../css/userPage/pictureModel.css"/>
    <script src="../js/userPage/userViewPage.js "></script>

    <%--admin file--%>
    <script src="../js/adminPage/adminUserInfoPage.js "></script>

    <%--admin user update--%>
    <link rel="stylesheet" type="text/css" href="../css/adminPage/adminUserInfoPage.css"/>

    <title>Welcome Page</title>
</head>
<body>

<div class="navbar navbar-expand-md navbar-dark bg-dark mb-4" role="navigation">
    <a class="navbar-brand" href="#">The Web</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a id="navHome" class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="https://github.com/Tommyczc" target="_blank">Github</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login" target="_blank">Login</a>
            </li>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link disabled" href="#">Disabled</a>--%>
<%--            </li>--%>



            <shiro:lacksRole name="user">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle disabled" id="dropdown4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin Only</a>
                </li>
            </shiro:lacksRole>

            <shiro:hasRole name ="user">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="dropdown1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">File Management</a>
                    <ul class="dropdown-menu" aria-labelledby="dropdown1">
                        <li class="dropdown-item" id="viewPictureButton" href="#"><a>view pictures</a></li>
                        <li class="dropdown-item" id="uploadPictureButton" href="#"><a>Upload pictures</a></li>
                    </ul>
                </li>
            </shiro:hasRole>



            <shiro:lacksRole name="admin">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle disabled" id="dropdown3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin Only</a>
            </li>
            </shiro:lacksRole>


            <shiro:hasRole name ="admin">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="dropdown2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin</a>
                <ul class="dropdown-menu" aria-labelledby="dropdown2">
                    <li class="dropdown-item" id="adminUserInformation" href="#"><a>User Information</a></li>
                    <li class="dropdown-item" id="adminUploadHistory" href="#"><a>Upload History</a></li>
<%--                    <li class="dropdown-item" id="adminInsertUser" href="#"><a>Insert User</a></li>--%>
<%--                    <li class="dropdown-item dropdown">--%>
<%--                        <a class="dropdown-toggle" id="dropdown2-1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown2.1</a>--%>
<%--                        <ul class="dropdown-menu" aria-labelledby="dropdown2-1">--%>
<%--                            <li class="dropdown-item" href="#"><a>Action 2.1 A</a></li>--%>
<%--                            <li class="dropdown-item" href="#"><a>Action 2.1 B</a></li>--%>
<%--                            <li class="dropdown-item" href="#"><a>Action 2.1 C</a></li>--%>
<%--                            <li class="dropdown-item dropdown">--%>
<%--                                <a class="dropdown-toggle" id="dropdown2-1-1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown2.1.1</a>--%>
<%--                                <ul class="dropdown-menu" aria-labelledby="dropdown2-1-1">--%>
<%--                                    <li class="dropdown-item" href="#"><a>Action 2.1.1 A</a></li>--%>
<%--                                    <li class="dropdown-item" href="#"><a>Action 2.1.1 B</a></li>--%>
<%--                                    <li class="dropdown-item" href="#"><a>Action 2.1.1 C</a></li>--%>
<%--                                </ul>--%>
<%--                            </li>--%>
<%--                            <li class="dropdown-item dropdown">--%>
<%--                                <a class="dropdown-toggle" id="dropdown2-1-2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown2.1.2</a>--%>
<%--                                <ul class="dropdown-menu" aria-labelledby="dropdown2-1-2">--%>
<%--                                    <li class="dropdown-item" href="#"><a>Action 2.1.2 A</a></li>--%>
<%--                                    <li class="dropdown-item" href="#"><a>Action 2.1.2 B</a></li>--%>
<%--                                    <li class="dropdown-item" href="#"><a>Action 2.1.2 C</a></li>--%>
<%--                                </ul>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
                </ul>
            </li>
            </shiro:hasRole>

        </ul>
<%--        <form class="form-inline mt-2 mt-md-0">--%>
<%--            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">--%>
<%--            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
<%--        </form>--%>
    </div>
</div>

<shiro:authenticated>

<main role="main" class="container">

    <div id="myModal" class="modal">

        <!-- 关闭按钮 -->
        <span class="close" onclick="document.getElementById('myModal').style.display='none'">&times;</span>

        <!-- 弹窗内容 -->
        <img decoding="async" class="modal-content" id="img01" max-width=60% max-height=60%>

        <!-- 文本描述 -->
        <div id="caption"></div>
    </div>

    <shiro:hasRole name="user">
        <div id="home">
            <jsp:include  page="homePage.jsp" flush="true"/>
        </div>

        <div id="userViewPicture" class="userView">
            <jsp:include page="userPage/userViewPicture.jsp" flush="true"/>
        </div>

        <div id="userUploadPicture" class="userUpload">
            <jsp:include page="userPage/userUploadPicture.jsp" flush="true"/>
        </div>
    </shiro:hasRole>



    <shiro:hasRole name="admin">
        <div id="adminUserInfo" class="userInfo">
           <jsp:include page="adminPage/adminUserInfoPage.jsp" flush="true"/>
        </div>

        <div id="adminUserUpload" class="userUpload">
            <jsp:include page="adminPage/adminUploadHistoryPage.jsp" flush="true"/>
        </div>
    </shiro:hasRole>

</main>
</shiro:authenticated>


</body>
</html>