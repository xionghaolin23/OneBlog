package com.zyd.blog.util;

import com.zyd.blog.business.consts.SessionConst;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.framework.holder.RequestHolder;

import java.util.UUID;

/**
 * Session工具类
 *

 */
public class SessionUtil {

    /**
     * 当前是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return null != SessionUtil.getUser();
    }

    public static boolean isLogin1() {
        return null != SessionUtil.getUser1();
    }
    /**
     * 获取session中的用户信息
     *
     * @return User
     */
    public static User getUser() {
        return (User) RequestHolder.getSession(SessionConst.ADMIN_USER_SESSION_KEY);
    }

    public static User getUser1() {
        return (User) RequestHolder.getSession(SessionConst.USER_SESSION_KEY);
    }

    /**
     * 添加session
     *
     * @param user
     */
    public static void setUser(User user) {
        RequestHolder.setSession(SessionConst.ADMIN_USER_SESSION_KEY, user);
    }

    /**
     * 删除session信息
     */
    public static void removeUser() {
        RequestHolder.removeSession(SessionConst.ADMIN_USER_SESSION_KEY);
    }

    /**
     * 获取session中的Token信息
     *
     * @return String
     */
    public static String getToken(String key) {
        return (String) RequestHolder.getSession(key);
    }

    /**
     * 添加Token
     */
    public static void setToken(String key) {
        RequestHolder.setSession(key, UUID.randomUUID().toString());
    }

    /**
     * 删除Token信息
     */
    public static void removeToken(String key) {
        RequestHolder.removeSession(key);
    }

    /**
     * 获取验证码
     */
    public static String getKaptcha() {
        return (String) RequestHolder.getSession(SessionConst.KAPTCHA_SESSION_KEY);
    }

    /**
     * 保存验证码
     */
    public static void setKaptcha(String kaptcha) {
        RequestHolder.setSession(SessionConst.KAPTCHA_SESSION_KEY, kaptcha);
    }

    /**
     * 删除验证码
     */
    public static void removeKaptcha() {
        RequestHolder.removeSession(SessionConst.KAPTCHA_SESSION_KEY);
    }

    /**
     * 删除所有的session信息
     */
    public static void removeAllSession() {
        String[] keys = RequestHolder.getSessionKeys();
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                RequestHolder.removeSession(key);
            }
        }
    }
}
