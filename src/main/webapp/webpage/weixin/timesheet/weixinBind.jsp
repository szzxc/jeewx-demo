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
    <form action="timesheet.do?bind" method="post">
        <div class="hd">
            <p class="page_desc">请绑定微信和公司邮箱。</p>
        </div>
        <div class="bd">
            <div class="weui_panel weui_panel_access">
                <div class="weui_panel_bd">
                    <div class="weui_media_box weui_media_appmsg">
                        <div class="weui_media_hd">
                            <img class="weui_media_appmsg_thumb" src="${headImgUrl}" alt="">
                        </div>
                        <div class="weui_media_bd">
                            <h4 class="weui_media_title">${userName}</h4>
                            <p class="weui_media_desc">${addr}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="bd spacing">
            <div class="weui_cells_title"></div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <input name="openid"  type="hidden" value="${openid}"/>
                        <input name="email" class="weui_input" type="text" placeholder="请输入邮箱"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_title"></div>
            <button type="submit" class="weui_btn weui_btn_primary">绑定</button>
        </div>
    </form>
</div>
<script src="plug-in/weiui/dist/example/zepto.min.js"></script>
<script src="plug-in/weiui/dist/example/router.min.js"></script>
</body>
</html>
