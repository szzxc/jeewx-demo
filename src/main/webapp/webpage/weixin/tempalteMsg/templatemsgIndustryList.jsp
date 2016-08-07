<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinTemplatemsgIndustryList" checkbox="true" fitColumns="false" title="消息模板行业设置" actionUrl="weixinTemplatemsgIndustryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主行业"  field="nameOneFirst"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="副行业"  field="nameOneSecond"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="行业代码"  field="codeOne"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第二主行业"  field="nameTwoFirst"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第二副行业"  field="nameTwoSecond"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第二行业代码"  field="codeTwo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinTemplatemsgIndustryController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinTemplatemsgIndustryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinTemplatemsgIndustryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinTemplatemsgIndustryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinTemplatemsgIndustryController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/fengyuntec/com.fengyuntec.weixin.templatemsg/weixinTemplatemsgIndustryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#weixinTemplatemsgIndustryListtb").find("input[name='createDate']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#weixinTemplatemsgIndustryListtb").find("input[name='updateDate']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinTemplatemsgIndustryController.do?upload', "weixinTemplatemsgIndustryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinTemplatemsgIndustryController.do?exportXls","weixinTemplatemsgIndustryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinTemplatemsgIndustryController.do?exportXlsByT","weixinTemplatemsgIndustryList");
}
 </script>