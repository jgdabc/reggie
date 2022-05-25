<%--
  Created by IntelliJ IDEA.
  User: jgdabc
  Date: 2022/5/20
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<style>
    * {
        margin: 0px;
        padding: 0px;
    }

    video {
        position: fixed;
        right: 0px;
        bottom: 0px;
        min-width: 100%;
        min-height: 100%;
        height: auto;
        width: auto;
        /*加滤镜*/
        /*filter: blur(15px); //背景模糊设置 */
        /*-webkit-filter: grayscale(100%);*/
        /*filter:grayscale(100%); //背景灰度设置*/
        z-index: -11
    }

    source {
        min-width: 100%;
        min-height: 100%;
        height: auto;
        width: auto;
    }

    p {
        width: 100%;
        text-align: center;
        font-size: 40px;
        color: white;
    }
</style>

<!--<video autoplay="autoplay" loop="loop" id="video" x-webkit-airplay="true" webkit-playsinline="true" muted="">-->
<!--  <source src="D:\\KuGou\温奕心 - 一路生花.mkv" type="video/mp4">-->
<!--</video>-->
<!--<img src="imgs/Desert.jpg">-->
<video autoplay loop muted>
    <source src="video/begin.mp4"/>
</video>
<meta http-equiv="refresh" content="10;url=">



</body>
<script type="text/javascript">
    function jumpurl()
    {
        location.href="/brand-demo01/login.jsp"
    }
    setTimeout("jumpurl()",10000)
</script>
</html>
