package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.FolloUser;
import com.zyd.blog.business.entity.SensitiveWords;
import com.zyd.blog.business.service.FollowUserService;
import com.zyd.blog.business.service.SysSensitiveWordsService;
import com.zyd.blog.business.vo.FollowUserConditionVO;
import com.zyd.blog.business.vo.FollowUserVO;
import com.zyd.blog.business.vo.SensitiveWordsConditionVO;
import com.zyd.blog.persistence.beans.BizFollowUser;
import com.zyd.blog.persistence.beans.SysSensitiveWords;
import com.zyd.blog.persistence.beans.SysUser;
import com.zyd.blog.persistence.mapper.FollowUserMapper;
import com.zyd.blog.persistence.mapper.SysSensitiveWordsMapper;
import com.zyd.blog.persistence.mapper.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 *  2018/4/16 16:26
 * @since 1.0
 */
@Service
public class FollowUserServiceImpl implements FollowUserService {

    @Autowired
    private FollowUserMapper followUserMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public PageInfo<FolloUser> findPageBreakByCondition(FollowUserConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizFollowUser> sysUsers = followUserMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<FolloUser> users = new ArrayList<>();
        for (BizFollowUser su : sysUsers) {
            users.add(new FolloUser(su));
        }
        PageInfo bean = new PageInfo<BizFollowUser>(sysUsers);
        bean.setList(users);
        return bean;
    }

    @Override
    public List<BizFollowUser> findPageByUserIdAndFollowingUserId(String userId, String followingUserId) {
        return followUserMapper.findPageByUserIdAndFollowingUserId(userId, followingUserId);
    }

    @Override
    public List<FollowUserVO> getAllFollowers() {
        //当前用户信息
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        List<BizFollowUser> followUsers = followUserMapper.findPageByUserId(userId.toString());
        if(CollectionUtils.isEmpty(followUsers)){
            return null;
        }
        List<FollowUserVO> followUserList = new ArrayList<>();
        followUsers.forEach(
                i->{
                    SysUser sysUser = sysUserMapper.selectByPrimaryKey(i.getFollowUserId());
                    FollowUserVO vo = new FollowUserVO();
                    if(StringUtils.isBlank(sysUser.getNickname())){
                        vo.setNickname(sysUser.getUsername());
                    }else{
                        vo.setNickname(sysUser.getNickname());
                    }
                    followUserList.add(vo);
                }
        );
        return followUserList;
    }


    @Override
    public BizFollowUser insert(BizFollowUser entity) {
        Assert.notNull(entity, "entity不可为空！");
        followUserMapper.insertSelective(entity);
        return entity;
    }



    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return followUserMapper.deleteByPrimaryKey(primaryKey) > 0;

    }


    @Override
    public boolean updateSelective(BizFollowUser entity) {
        Assert.notNull(entity, "entity不可为空！");
        return followUserMapper.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public BizFollowUser getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizFollowUser bizFollowUser = followUserMapper.selectByPrimaryKey(primaryKey);
        return bizFollowUser;
    }

    @Override
    public List<BizFollowUser> listAll() {
        return followUserMapper.selectAll();

    }


}
