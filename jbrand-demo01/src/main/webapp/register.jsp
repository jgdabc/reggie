<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1 style="text-align: center">欢迎注册</h1>
        <span style="padding-left: 50px" >已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/brand-demo01/registerServlet" method="post"  >

        <table >

            <tr >
                <td width="100"style="height: 10px">用户名</td>
                <td class="inputs">

                    <input name="username" type="text" id="username" style="margin-right: 65px">
                    <br>
                    <span id="username_err" class="err_msg" >${register_msg}</span>
                </td>

            </tr>

            <tr>
                <td width="60"style="height:50px">密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password" style="margin-right: 65px">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>


            <tr>
                <td style="padding-bottom: 70px">验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode" style="margin-right: 65px">
                    <img id="checkCodeImg" src="/brand-demo01/checkCodeServlet" style="margin-right: 10px">
                    <a href="#" id="changeImg" >看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn" style="margin-right: 65px;margin-top: 10px" >
        </div>
        <br class="clear">
    </form>

</div>

<script>
    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "/brand-demo01/checkCodeServlet?"+new Date().getMilliseconds();
    }

</script>
</body>
</html>