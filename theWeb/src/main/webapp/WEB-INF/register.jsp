<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/register.css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    <title>Login Page</title>
</head>

<body>

<div th:text="${failInf}" id="msg" style="display:none">${failInf}</div>


<div id="login_frame">

    <div id="hellword">
        <h1>Register Page</h1>
    </div>

    <form action="${pageContext.request.contextPath}/registerLogic" method="POST">
        <p><label class="label_input">Address</label><input name='txtAddress' type='text' /></p>
        <p><label class="label_input">Age</label><input name='txtAge' type='text' /></p>
        <p><label class="label_input">UserName</label><input name='txtUsername' type='text' /></p>
        <p><label class="label_input">Password</label><input name='txtPassword' type='password' /></p>
        <p><label class="label_input_con">Password again</label><input name='txtPasswordconfirm' type='password' /></p>
<%--        <input type="hidden" name="auth" value="<?php echo md5(session_id());?>">--%>
        <div id="login_control">
            <input type='submit' value='Register' >
            <br/><br/><a id="forget_pwd" href="/">Already have account?</a>
        </div>
    </form>
</div>



<script type="text/javascript">

    $(document).ready(function(){

        var message2=$("#msg").html();

        if(message2!="") {
            alert(message2)
        }

    })
</script>

</body>
</html>

