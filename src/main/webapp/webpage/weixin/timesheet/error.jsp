<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/28
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>问题</title>
    <link rel="stylesheet" href="plug-in/weiui/dist/style/weui.css"/>
    <link rel="stylesheet" href="plug-in/weiui/dist/example/example.css"/>
</head>
<body ontouchstart>
<div class="container" id="container">
    <div class="weui_msg">
        <div class="weui_icon_area"><i class="weui_icon_msg weui_icon_warn"></i></div>
        <div class="weui_text_area">
            <h2 class="weui_msg_title">发生问题了</h2>
            <p class="weui_msg_desc">${errmsg}</p>
        </div>
    </div>
</div>
<script src="plug-in/weiui/dist/example/zepto.min.js"></script>
<script src="plug-in/weiui/dist/example/router.min.js"></script>
<script src="plug-in/weiui/dist/example/example.js"></script>
</body>
</html>
