package com.zyd.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.SensitiveWords;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.ResponseStatus;
import com.zyd.blog.business.service.SysSensitiveWordsService;
import com.zyd.blog.business.service.SysUserRoleService;
import com.zyd.blog.business.service.SysUserService;
import com.zyd.blog.business.vo.SensitiveWordsConditionVO;
import com.zyd.blog.business.vo.UserConditionVO;
import com.zyd.blog.framework.object.PageResult;
import com.zyd.blog.framework.object.ResponseVO;
import com.zyd.blog.persistence.beans.SysSensitiveWords;
import com.zyd.blog.util.PasswordUtil;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 敏感管理
 */
@RestController
@RequestMapping("/sensitiveWords")
public class RestSensitiveWordsController {
    @Autowired
    private SysSensitiveWordsService sysSensitiveWordsService;
    @Autowired
    private SysUserRoleService userRoleService;

    @PostMapping("/list")
    public PageResult list(SensitiveWordsConditionVO vo) {
        PageInfo<SensitiveWords> pageInfo = sysSensitiveWordsService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @PostMapping(value = "/add")
    @BussinessLog("添加敏感词")
    public ResponseVO add(SensitiveWords sensitiveWords) {
        SensitiveWords u = sysSensitiveWordsService.getByValue(sensitiveWords.getValue());
        if (u != null) {
            return ResultUtil.error("该敏感词[" + sensitiveWords.getValue() + "]已存在！");
        }
        try {
            SysSensitiveWords sysSensitiveWords = new SysSensitiveWords();
            sysSensitiveWords.setValue(sensitiveWords.getValue());
            sysSensitiveWordsService.insert(sysSensitiveWords);
            return ResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("error");
        }
    }

    @PostMapping("/edit")
    @BussinessLog("编辑敏感词")
    public ResponseVO edit(SysSensitiveWords sensitiveWords) {
        try {
            sysSensitiveWordsService.updateSelective(sensitiveWords);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("用户修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

    @PostMapping(value = "/remove")
    @BussinessLog("删除敏感词")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            sysSensitiveWordsService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个敏感词");
    }

    @PostMapping("/get/{id}")
    @BussinessLog("获取敏感词")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.sysSensitiveWordsService.getByPrimaryKey(id));
    }


    /**
     * 敏感词过滤
     *
     * @param text
     * @return
     */
    @GetMapping(value = "/word/filter")
    public ResponseVO sensitiveWordFiltering(String text) throws Exception {
        text = "<p>敏感词</p>";
        try {
            Set<String> set = sysSensitiveWordsService.sensitiveWordFiltering(text);
            return ResultUtil.success(set);
        } catch (Exception e) {
            throw new Exception("过滤敏感词出错，请联系维护人员");
        }

    }


}
