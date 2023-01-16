package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.SensitiveWords;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.entity.UserPwd;

import com.zyd.blog.business.service.SysSensitiveWordsService;
import com.zyd.blog.business.vo.SensitiveWordsConditionVO;
import com.zyd.blog.fiter.SensitiveWordInit;
import com.zyd.blog.fiter.SensitivewordEngine;

import com.zyd.blog.persistence.beans.SysSensitiveWords;
import com.zyd.blog.persistence.mapper.SysSensitiveWordsMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 用户
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysSensitiveWordsServiceImpl implements SysSensitiveWordsService {

    @Autowired
    private SysSensitiveWordsMapper sysSensitiveWordsMapper;

    @Override
    public PageInfo<SensitiveWords> findPageBreakByCondition(SensitiveWordsConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysSensitiveWords> sysUsers = sysSensitiveWordsMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<SensitiveWords> users = new ArrayList<>();
        for (SysSensitiveWords su : sysUsers) {
            users.add(new SensitiveWords(su));
        }
        PageInfo bean = new PageInfo<SysSensitiveWords>(sysUsers);
        bean.setList(users);
        return bean;
    }

    @Override
    public SysSensitiveWords insert(SysSensitiveWords entity) {
        Assert.notNull(entity, "entity不可为空！");
        sysSensitiveWordsMapper.insertSelective(entity);
        return entity;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysSensitiveWordsMapper.deleteByPrimaryKey(primaryKey) > 0;

    }

    @Override
    public boolean updateSelective(SysSensitiveWords entity) {
        Assert.notNull(entity, "entity不可为空！");
        return sysSensitiveWordsMapper.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public SysSensitiveWords getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysSensitiveWords sysUser = sysSensitiveWordsMapper.selectByPrimaryKey(primaryKey);
        return sysUser;
    }

    /**
     * 根据敏感词查找
     *
     * @param value
     * @return
     */
    @Override
    public SensitiveWords getByValue(String value) {
        SysSensitiveWords sysSensitiveWords = this.sysSensitiveWordsMapper.getByValue(value);
        return null == sysSensitiveWords ? null : new SensitiveWords(sysSensitiveWords);
    }

    /**
     * 敏感词过滤
     * @param text 前端传入的文本
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Set<String> sensitiveWordFiltering(String text) {
        // 初始化敏感词库对象
        SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
        // 从数据库中获取敏感词对象集合（调用的方法来自Dao层，此方法是service层的实现类）
        List<SysSensitiveWords> sensitiveWords = sysSensitiveWordsMapper.selectAll();
        // 构建敏感词库
        Map sensitiveWordMap = sensitiveWordInit.initKeyWord(sensitiveWords);
        // 传入SensitivewordEngine类中的敏感词库
        SensitivewordEngine.sensitiveWordMap = sensitiveWordMap;
        // 得到敏感词有哪些，传入2表示获取所有敏感词
        Set<String> set = SensitivewordEngine.getSensitiveWord(text, 2);
        return set;
    }





}
