<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>我的日报</title>
    <link rel="stylesheet" href="plug-in/weiui/dist/style/weui.css"/>
    <link rel="stylesheet" href="plug-in/weiui/dist/example/example.css"/>
</head>
<body ontouchstart>
<div class="container" id="container">
    <div class="hd">
        <p class="page_title">日报列表</p>
    </div>
    <div class="bd">
        <div class="weui_panel weui_panel_access">
            <div class="weui_panel_bd">
                <c:forEach var="timesheet" items="${timesheetList}">
                    <div class="weui_media_box weui_media_text title close">
                        <p class="weui_media_desc">
                            <c:if test="${timesheet.status == 1}">
                                <i class="weui_icon_success_circle"></i>
                            </c:if>
                            <c:if test="${timesheet.status == 0}">
                                <i class="weui_icon_waiting_circle"></i>
                            </c:if>
                            &nbsp;&nbsp;${timesheet.date}</p>
                    </div>
                    <div class="weui_media_box weui_media_text info">
                        <p class="weui_media_desc"></p>
                        <div class="button_sp_area">
                            <a href="timesheet.do?update&id=${timesheet.id}" class="weui_btn weui_btn_mini weui_btn_default alter">修改</a>
                            <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_primary submit">提交</a>
                            <a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_warn delete">删除</a>
                            <input type="hidden" value="${timesheet.id}"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <div class="weui_dialog_confirm" id="dialog" style="display: none;">
        <div class="weui_mask"></div>
        <div class="weui_dialog" id="dialogBody">
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">确认</strong></div>
            <div class="weui_dialog_bd">提交后无法修改，确认吗？</div>
            <div class="weui_dialog_ft">
                <a href="javascript:;" class="weui_btn_dialog default cancel">取消</a>
                <a href="javascript:;" class="weui_btn_dialog primary ok">确定</a>
            </div>
        </div>
    </div>
</div>
<style type="text/css">
    .info {
        display: none;
    }

    .open {
        color: red;
    }

    .close {
        color: black;
    }
</style>
<script src="plug-in/weiui/dist/example/zepto.min.js"></script>
<script src="plug-in/weiui/dist/example/router.min.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $(".delete").click(function () {
            var id = $(this).next("input").val();
            $('#dialog').show().on('click', '.cancel', function () {
                $('#dialog').off('click').hide();
            }).on('click', '.ok', function () {
                $('#dialogBody').hide();
                window.location.href = 'timesheet.do?delete&id=' + id;
            });
        });
        $(".title").click(function () {
            if ($(this).hasClass("close")) {
                $(this).removeClass("close").addClass("open");
                $(this).next(".info").show();
            } else {
                $(this).removeClass("open").addClass("close");
                $(this).next(".info").hide();
            }
        });
    });
</script>
</body>
</html>
