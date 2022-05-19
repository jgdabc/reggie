<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>

    <link href="css/login.css" rel="stylesheet">

</head>

<body>

<div id="loginDiv">
    <form action="/brand-demo01/loginServlet" method="post" id="form">
        <h1 id="loginMsg">欢迎登录系统</h1>
        <div id="errorMsg" style="color: red" tex >${login_msg } ${register_msg} </div>
        <p>Username:<input id="username" name="username"value="${cookie.username.value}" type="text"></p>

        <p>Password:<input id="password" name="password" type="password"value="${cookie.password.value}"></p>
        <p>Remember:<input id="remember" name="remember"value="1" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">没有账号？点击注册</a>
        </div>
    </form>
</div>

</body>
</html>