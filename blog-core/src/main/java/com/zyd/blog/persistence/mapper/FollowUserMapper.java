package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.entity.FolloUser;
import com.zyd.blog.business.vo.FollowUserConditionVO;
import com.zyd.blog.business.vo.SensitiveWordsConditionVO;
import com.zyd.blog.persistence.beans.BizFollowUser;
import com.zyd.blog.persistence.beans.SysSensitiveWords;
import com.zyd.blog.plugin.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 *  2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface FollowUserMapper extends BaseMapper<BizFollowUser> {

    List<BizFollowUser> findPageBreakByCondition(FollowUserConditionVO vo);

    List<BizFollowUser> findPageByUserIdAndFollowingUserId(@Param("userId") String userId, @Param("followingUserId") String followingUserId);

    List<BizFollowUser> findPageByUserId(@Param("userId") String userId);



}
