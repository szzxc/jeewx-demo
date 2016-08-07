<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>消息模板行业设置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinTemplatemsgIndustryController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinTemplatemsgIndustryPage.id }">
					<input id="createName" name="createName" type="hidden" value="${weixinTemplatemsgIndustryPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${weixinTemplatemsgIndustryPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinTemplatemsgIndustryPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${weixinTemplatemsgIndustryPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${weixinTemplatemsgIndustryPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${weixinTemplatemsgIndustryPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							主行业:
						</label>
					</td>
					<td class="value">
					     	 <input id="nameOneFirst" name="nameOneFirst" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主行业</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							副行业:
						</label>
					</td>
					<td class="value">
					     	 <input id="nameOneSecond" name="nameOneSecond" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">副行业</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							行业代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="codeOne" name="codeOne" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">行业代码</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							第二主行业:
						</label>
					</td>
					<td class="value">
					     	 <input id="nameTwoFirst" name="nameTwoFirst" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第二主行业</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							第二副行业:
						</label>
					</td>
					<td class="value">
					     	 <input id="nameTwoSecond" name="nameTwoSecond" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第二副行业</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							第二行业代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="codeTwo" name="codeTwo" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第二行业代码</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/fengyuntec/com.fengyuntec.weixin.templatemsg/weixinTemplatemsgIndustry.js"></script>		