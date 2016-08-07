package weixin.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.jeecgframework.core.util.ResourceUtil;
import org.openkoala.koala.commons.InvokeResult;
import weixin.dto.TimesheetDTO;
import weixin.dto.UserRole;
import weixin.guanjia.core.util.HTTPClient;
import weixin.guanjia.core.util.HttpRequestUtil;
import weixin.guanjia.core.util.JacksonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxc on 2016/5/9.
 */
public class WSO2Util {
    private static final Log LOG = LogFactory.getLog(WSO2Util.class);

    private static final String BIND_USER_URL = "/weixin/bindUser";
    private static final String GET_USER_ROLE_URL = "/weixin/getUserRole";
    private static final String TIMESHEET_GET_BY_USER_URL = "/timesheet/getByUserId";
    private static final String TIMESHEET = "/timesheet";

    private static String dataServer;
    private static String wso2key;
    private static String wso2value;
    private static Map<String, String> wso2tokenMap = new HashMap<>();

    private static String getDataServer() {
        if(StringUtils.isEmpty(dataServer)) {
            dataServer = ResourceUtil.getConfigByName("wso2Server");
        }
        return dataServer;
    }
    private static String getWso2Info() {
        if(StringUtils.isEmpty(wso2key)) {
            wso2key = ResourceUtil.getConfigByName("wso2key");
        }
        if(StringUtils.isEmpty(wso2value)) {
            wso2value = ResourceUtil.getConfigByName("wso2value");
        }
        return wso2key + "=" + wso2value;
    }

    private static Map getWso2Token() {
        //"Authorization", "Bearer " + "567915788025607c52df9c288a624061"
        if (wso2tokenMap.get("Authorization") == null) {
//            String wso2token = TokenGen.getToken();
//            LOG.info("wso2 token:" + wso2token);
            wso2tokenMap.put("Authorization", "Bearer " + "81ff4e92da4f1b9e615deaf432fcbb68");
        }
        return wso2tokenMap;
    }

    public static InvokeResult bindUser(String openid, String email) {
        String result = HttpRequestUtil.executeRquest(getDataServer() + BIND_USER_URL,
                getWso2Info() + "&openid=" + openid + "&email=" + email,
                HttpRequestUtil.POST, getWso2Token(), null, "utf-8", null, null, null);
        InvokeResult invokeResult = null;
        try {
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
            if(Boolean.valueOf(jsonObject.get("hasErrors").toString())) {
                invokeResult = InvokeResult.failure(jsonObject.get("errorMessage").toString().replaceAll("\"",""));
            } else {
                invokeResult = InvokeResult.success(JacksonUtil.fromObject(jsonObject.get("data").toString(), UserRole.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invokeResult;
    }

    public static UserRole getUserRole(String userId) {
        UserRole userRole = new UserRole();
        try {
            HttpResponse response = new HTTPClient().doGet(getDataServer() + GET_USER_ROLE_URL + "?" + getWso2Info() + "&userId=" + userId
                    , String.valueOf(getWso2Token().get("Authorization")));
            String result = IOUtils.toString(response.getEntity().getContent(), "utf-8");
            userRole = JacksonUtil.fromObject(result, UserRole.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userRole;
    }

    public static InvokeResult saveTimesheet(TimesheetDTO timesheetDTO) {
        InvokeResult invokeResult = null;
        if(timesheetDTO == null || timesheetDTO.getId() == null) {
            String params = getWso2Info()
                    + "&userId=" + (timesheetDTO.getUserId() == null ? "" : timesheetDTO.getUserId())
                    + "&date=" + timesheetDTO.getDate()
                    + "&hours=" + timesheetDTO.getHours()
                    + "&projectId=" + timesheetDTO.getProjectId()
                    + "&stageId=" + timesheetDTO.getStageId()
                    + "&status=" + timesheetDTO.getStatus()
                    + "&memo=" + timesheetDTO.getMemo();
            String result = HttpRequestUtil.executeRquest(getDataServer() + TIMESHEET, params,
                    HttpRequestUtil.POST
                    , getWso2Token(),
                    null, "utf-8", null, null, null);
            try {
                JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
                if (Boolean.valueOf(jsonObject.get("hasErrors").toString())) {
                    invokeResult = InvokeResult.failure(jsonObject.get("errorMessage").toString().replaceAll("\"", ""));
                } else {
                    invokeResult = InvokeResult.success(JacksonUtil.fromObject(jsonObject.get("data").toString(), TimesheetDTO.class));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String playload = "{\"id\":"+timesheetDTO.getId()+"," +
                    "\"userId\":"+timesheetDTO.getUserId()+"," +
                    "\"date\":\""+timesheetDTO.getDate()+"\"," +
                    "\"hours\":"+timesheetDTO.getHours()+"," +
                    "\"projectId\":"+timesheetDTO.getProjectId()+"," +
                    "\"stageId\":"+timesheetDTO.getStageId()+"," +
                    "\"status\":"+timesheetDTO.getStatus()+"," +
                    "\"memo\":\""+timesheetDTO.getMemo()+"\"" +
                    "}";
            try {
                HttpResponse response = new HTTPClient().doPut(getDataServer() + TIMESHEET+ "?" + getWso2Info(),
                        String.valueOf(getWso2Token().get("Authorization")),
                        playload,
                        "application/json");
                String result = IOUtils.toString(response.getEntity().getContent(), "utf-8");
                JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
                if (Boolean.valueOf(jsonObject.get("hasErrors").toString())) {
                    invokeResult = InvokeResult.failure(jsonObject.get("errorMessage").toString().replaceAll("\"", ""));
                } else {
                    invokeResult = InvokeResult.success(JacksonUtil.fromObject(jsonObject.get("data").toString(), TimesheetDTO.class));
                }
                LOG.info(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return invokeResult;
    }

    public static List<TimesheetDTO> getTimesheetByUserId(String userId) {
        List<TimesheetDTO> list = new ArrayList<>();
        try {
            HttpResponse response = new HTTPClient().doGet(getDataServer() + TIMESHEET_GET_BY_USER_URL + "?" + getWso2Info() + "&userId=" + userId
                    , String.valueOf(getWso2Token().get("Authorization")));
            String result = IOUtils.toString(response.getEntity().getContent(), "utf-8");
            list = JSON.parseArray(result, TimesheetDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static InvokeResult deleteTimesheet(String id) {
        try {
            new HTTPClient().doDelete(getDataServer() + TIMESHEET + "/" + id +
                    "?" + getWso2Info(), String.valueOf(getWso2Token().get("Authorization")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return InvokeResult.success();
    }

    public static TimesheetDTO getTimesheet(String id) {
        TimesheetDTO timesheetDTO = new TimesheetDTO();
        try {
            HttpResponse response = new HTTPClient().doGet(getDataServer() + TIMESHEET + "/" + id +
                    "?" + getWso2Info(), String.valueOf(getWso2Token().get("Authorization")));
            String result = IOUtils.toString(response.getEntity().getContent(), "utf-8");
            timesheetDTO = JacksonUtil.fromObject(result, TimesheetDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timesheetDTO;
    }

}
