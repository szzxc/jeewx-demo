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
    <title>普通填报</title>
    <link rel="stylesheet" href="plug-in/weiui/dist/style/weui.css"/>
    <link rel="stylesheet" href="plug-in/weiui/dist/example/example.css"/>
</head>
<body ontouchstart>
<div class="container" id="container">
    <form method="post" action="timesheet.do?add" id="form">
        <div class="hd">
            <p class="page_title">日报填写</p>
        </div>
        <div class="bd spacing">
            <div class="weui_cells_title">日期</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="date" name="date" value="${timesheet.date}"/>
                        <input class="weui_input" id="id" type="hidden" name="id" value="${timesheet.id}"/>
                        <input class="weui_input" id="status" type="hidden" name="status" value="${timesheet.status}"/>
                        <input class="weui_input" id="userId" type="hidden" name="userId" value="${userId}"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_title">工时</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" name="hours" type="number" pattern="[0-9]*" placeholder="请输入数字" value="${timesheet.hours}"/>
                    </div>
                </div>
            </div>
            <div class="weui_cells_title">项目</div>
            <div class="weui_cells">
                <div class="weui_cell weui_cell_select">
                    <div class="weui_cell_bd weui_cell_primary">
                        <select class="weui_select" name="projectId">
                            <option value="0"></option>
                            <option value="1">国资委运维</option>
                            <option value="2">科技超市</option>
                            <option value="3">日志管理系统</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="weui_cells_title">阶段</div>
            <div class="weui_cells">
                <div class="weui_cell weui_cell_select">
                    <div class="weui_cell_bd weui_cell_primary">
                        <select class="weui_select" name="stageId">
                            <option value="0"></option>
                            <option value="1">需求调研</option>
                            <option value="2">开发</option>
                            <option value="3">测试</option>
                            <option value="4">运维</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="weui_cells_title">描述</div>
            <div class="weui_cells weui_cells_form">
                <div class="weui_cell">
                    <div class="weui_cell_bd weui_cell_primary">
                        <textarea class="weui_textarea" placeholder="请输入任务描述" rows="3" name="memo"></textarea>
                        <div class="weui_textarea_counter"><span>0</span>/200</div>
                    </div>
                </div>
            </div>

            <div class="weui_cells_title"></div>
            <a href="javascript:;" class="weui_btn weui_btn_primary" id="showDialog">提交日志</a>
            <a href="javascript:;" class="weui_btn weui_btn_default" id="showToast">保存草稿</a>

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
        <div id="toast" style="display: none;">
            <div class="weui_mask_transparent"></div>
            <div class="weui_toast">
                <i class="weui_icon_toast"></i>
                <p class="weui_toast_content">成功</p>
            </div>
        </div>
    </form>
</div>
<script src="plug-in/weiui/dist/example/zepto.min.js"></script>
<script src="plug-in/weiui/dist/example/router.min.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $('#container').on('click', '#showDialog', function () {
            $('#dialog').show().on('click', '.cancel', function () {
                $('#dialog').off('click').hide();
            }).on('click', '.ok', function () {
                $("#status").val("1");
                $("#form").submit();
                $('#dialogBody').hide();
            });
        }).on('click', '#showToast', function () {
            $('#toast').show();
            setTimeout(function () {
                $('#toast').hide();
            }, 2000);
        });
    });

</script>
</body>
</html>
