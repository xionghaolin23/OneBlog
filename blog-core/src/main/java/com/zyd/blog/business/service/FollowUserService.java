package com.zyd.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.FolloUser;
import com.zyd.blog.business.entity.SensitiveWords;
import com.zyd.blog.business.vo.FollowUserConditionVO;
import com.zyd.blog.business.vo.FollowUserVO;
import com.zyd.blog.business.vo.SensitiveWordsConditionVO;
import com.zyd.blog.framework.object.AbstractService;
import com.zyd.blog.persistence.beans.BizFollowUser;
import com.zyd.blog.persistence.beans.SysSensitiveWords;

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
public interface FollowUserService extends AbstractService<BizFollowUser, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<FolloUser> findPageBreakByCondition(FollowUserConditionVO vo);

    List<BizFollowUser> findPageByUserIdAndFollowingUserId(String userId, String followingUserId);


    List<FollowUserVO> getAllFollowers();





}
