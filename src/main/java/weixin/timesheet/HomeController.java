package weixin.timesheet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.dto.UserRole;
import weixin.util.WSO2Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhouxc on 2016/5/9.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping(params = "index")
    public ModelAndView index(ModelMap model,HttpServletRequest request,HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        UserRole userRole = WSO2Util.getUserRole(userId);
        model.addAttribute("userRole", userRole);
        model.addAttribute("headImgUrl", request.getSession().getAttribute("headImgUrl"));
        return new ModelAndView("weixin/timesheet/home");
    }
}
