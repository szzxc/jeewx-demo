<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/4
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>绑定微信</title>
    <link rel="stylesheet" href="plug-in/weiui/dist/style/weui.css"/>
    <link rel="stylesheet" href="plug-in/weiui/dist/example/example.css"/>
</head>
<body ontouchstart>
<div class="container" id="container">
    <div class="weui_msg">
        <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
        <div class="weui_text_area">
            <h2 class="weui_msg_title">绑定成功</h2>
            <p class="weui_msg_desc">${userRole.userName}，你的角色为：${userRole.roleName}</p>
        </div>
        <div class="weui_opr_area">
            <p class="weui_btn_area">
                <a href="home.do?index" class="weui_btn weui_btn_primary">开始使用</a>
            </p>
        </div>
    </div>
</div>
<script src="plug-in/weiui/dist/example/zepto.min.js"></script>
<script src="plug-in/weiui/dist/example/router.min.js"></script>
</body>
</html>
