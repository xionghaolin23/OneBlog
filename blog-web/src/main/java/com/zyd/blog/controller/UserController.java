package com.zyd.blog.controller;

import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Role;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.service.SysRoleResourcesService;
import com.zyd.blog.business.service.SysRoleService;
import com.zyd.blog.business.service.SysUserRoleService;
import com.zyd.blog.business.service.SysUserService;
import com.zyd.blog.framework.holder.RequestHolder;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.persistence.beans.SysRole;
import com.zyd.blog.util.PasswordUtil;
import com.zyd.blog.util.ResultUtil;
import com.zyd.blog.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * 用户登录
 * @Author:xionghl
 * @Date:2023/1/3 20:53
 */
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRoleService userRoleService;


    @BussinessLog("进入登录页面")
    @GetMapping("/login")
    public ModelAndView login(Model model) {
        return ResultUtil.view("/login");
    }

    @BussinessLog("进入注册页面")
    @GetMapping("/register")
    public ModelAndView register(Model model) {
        return ResultUtil.view("/register");
    }


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @BussinessLog("[{1}]登录系统")
    @PostMapping("/signin")
    @ResponseBody
    public ResponseVO submitLogin(String username, String password, boolean rememberMe, String kaptcha) {

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到xxRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
         //   SavedRequest savedRequest = WebUtils.getSavedRequest(RequestHolder.getRequest());
            String historyUrl = null;
//            if(null != savedRequest) {
//                if(!savedRequest.getMethod().equals("POST")) {
//                    historyUrl = savedRequest.getRequestUrl();
//                }
//            }
//            historyUrl = null;
            return ResultUtil.success(null, historyUrl);
        } catch (Exception e) {
            log.error("登录失败，用户名[{}]：{}", username, e.getMessage());
            token.clear();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping(value = "/register")
    @BussinessLog("注册用户")
    @ResponseBody
    public ResponseVO add(String username, String password,String mobile,String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setUserType("USER");
        User u = userService.getByUserName(user.getUsername());
        if (u != null) {
            if(u.getScore() != null && u.getScore() == 1){
                return ResultUtil.error("该用户名["+user.getUsername()+"]已存在！请更改用户名");
            }
        }
        try {
            user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            //普通用户
            user.setScore(1);
            userService.insert(user);
            userRoleService.addUserRole(user.getId(), "4");
            return ResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("error");
        }
    }


    @BussinessLog("退出系统")
    @GetMapping("/logout")
    public ModelAndView logout(RedirectAttributes redirectAttributes) {
        // http://www.oschina.net/question/99751_91561
        // 此处有坑： 退出登录，其实不用实现任何东西，只需要保留这个接口即可，也不可能通过下方的代码进行退出
        // SecurityUtils.getSubject().logout();
        // 因为退出操作是由Shiro控制的
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return ResultUtil.redirect("index");
    }

}
