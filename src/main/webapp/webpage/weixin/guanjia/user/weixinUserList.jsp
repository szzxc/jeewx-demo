<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinUserList" checkbox="true" fitColumns="false" title="微信用户" actionUrl="weixinUserController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关注"  field="subscribe"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="openid"  field="openid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="加密openid"  field="wechatUrlToken"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="昵称"  field="nickname"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="sex"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="城市"  field="city"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="语言"  field="language"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="省份"  field="province"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="国家"  field="country"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="头像"  field="headimgurl"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关注时间"  field="subscribe_time"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户id"  field="userId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="组id"  field="groupid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标签"  field="tagid_list"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="绑定时间"  field="wechatBindTime"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinUserController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinUserController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinUserController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinUserController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinUserController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/guanjia/user/weixinUserList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinUserController.do?upload', "weixinUserList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinUserController.do?exportXls","weixinUserList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinUserController.do?exportXlsByT","weixinUserList");
}
 </script>