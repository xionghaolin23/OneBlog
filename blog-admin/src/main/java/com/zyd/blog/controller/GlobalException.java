package com.zyd.blog.controller;

import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO exception(Exception e) {
        log.error("系统全局发生错误:[出错原因:{},出错信息:{}]", e.getCause(), e.getMessage());
        if (e instanceof AuthorizationException) {
            return ResultUtil.error("没有权限访问");
        }
        return ResultUtil.error(e.getMessage());
    }
}
