package weixin.guanjia.user.controller;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.*;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.user.entity.WeixinUserEntity;
import weixin.guanjia.user.service.WeixinUserServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;


/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 微信用户
 * @date 2016-05-05 14:58:33
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinUserController")
public class WeixinUserController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WeixinUserController.class);

    @Autowired
    private WeixinUserServiceI weixinUserService;
    @Autowired
    private SystemService systemService;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * 微信用户列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinUser")
    public ModelAndView weixinUser(HttpServletRequest request) {
        return new ModelAndView("weixin/guanjia/user/weixinUserList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     * @param
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinUserEntity weixinUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinUserEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinUser, request.getParameterMap());
        try {
            //自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinUserService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除微信用户
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinUserEntity weixinUser, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinUser = systemService.getEntity(WeixinUserEntity.class, weixinUser.getId());
        message = "微信用户删除成功";
        try {
            weixinUserService.delete(weixinUser);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信用户删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除微信用户
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信用户删除成功";
        try {
            for (String id : ids.split(",")) {
                WeixinUserEntity weixinUser = systemService.getEntity(WeixinUserEntity.class,
                        id
                );
                weixinUserService.delete(weixinUser);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信用户删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 添加微信用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinUserEntity weixinUser, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信用户添加成功";
        try {
            weixinUserService.save(weixinUser);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信用户添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新微信用户
     *
     * @param
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinUserEntity weixinUser, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信用户更新成功";
        WeixinUserEntity t = weixinUserService.get(WeixinUserEntity.class, weixinUser.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(weixinUser, t);
            weixinUserService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信用户更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 微信用户新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WeixinUserEntity weixinUser, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinUser.getId())) {
            weixinUser = weixinUserService.getEntity(WeixinUserEntity.class, weixinUser.getId());
            req.setAttribute("weixinUserPage", weixinUser);
        }
        return new ModelAndView("weixin/guanjia/user/weixinUser-add");
    }

    /**
     * 微信用户编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinUserEntity weixinUser, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinUser.getId())) {
            weixinUser = weixinUserService.getEntity(WeixinUserEntity.class, weixinUser.getId());
            req.setAttribute("weixinUserPage", weixinUser);
        }
        return new ModelAndView("weixin/guanjia/user/weixinUser-update");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        return new ModelAndView("weixin/guanjia/user/weixinUserUpload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(WeixinUserEntity weixinUser, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "微信用户";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader(
                        "content-disposition",
                        "attachment;filename="
                                + java.net.URLEncoder.encode(codedFileName,
                                "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"),
                        "ISO8859-1");
                response.setHeader("content-disposition",
                        "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            CriteriaQuery cq = new CriteriaQuery(WeixinUserEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinUser, request.getParameterMap());

            List<WeixinUserEntity> weixinUsers = this.weixinUserService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信用户列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinUserEntity.class, weixinUsers);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

    /**
     * 导出excel 使模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXlsByT")
    public void exportXlsByT(WeixinUserEntity weixinUser, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "微信用户";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader(
                        "content-disposition",
                        "attachment;filename="
                                + java.net.URLEncoder.encode(codedFileName,
                                "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"),
                        "ISO8859-1");
                response.setHeader("content-disposition",
                        "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信用户列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinUserEntity.class, null);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setSecondTitleRows(1);
            params.setNeedSave(true);
            try {
                List<WeixinUserEntity> listWeixinUserEntitys =
                        (List<WeixinUserEntity>) ExcelImportUtil.importExcelByIs(file.getInputStream(), WeixinUserEntity.class, params);
                for (WeixinUserEntity weixinUser : listWeixinUserEntitys) {
                    weixinUserService.save(weixinUser);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(e));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return j;
    }
}
