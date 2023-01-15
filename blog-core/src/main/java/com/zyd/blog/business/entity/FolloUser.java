package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.framework.object.AbstractBO;
import com.zyd.blog.persistence.beans.BizFollowUser;
import com.zyd.blog.persistence.beans.SysSensitiveWords;

/**
 * 关注人
 *
 * @Author:xionghl
 * @Date:2023/1/4 21:16
 */
public class FolloUser extends AbstractBO {
    private BizFollowUser bizFollowUser;

    public FolloUser() {
        this.bizFollowUser = new BizFollowUser();
    }

    public FolloUser(BizFollowUser bizFollowUser) {
        this.bizFollowUser = bizFollowUser;
    }


    @JsonIgnore
    public BizFollowUser getBizFollowUser() {
        return this.bizFollowUser;
    }

    public Long getId() {
        return this.bizFollowUser.getId();
    }

    public void setId(Long id) {
        this.bizFollowUser.setId(id);
    }

    public String getUserId() {
        return this.bizFollowUser.getUserId();
    }

    public void setUserId(String userId) {
        this.bizFollowUser.setUserId(userId);
    }

    public String getFollowUserId() {
        return this.bizFollowUser.getFollowUserId();
    }

    public void setFollowUserId(String followUserId) {
        this.bizFollowUser.setFollowUserId(followUserId);
    }
}
