package com.zyd.blog.business.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 *  2018/6/6 16:34
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo {
    private Long id;
    @NotNull(message = "昵称不可为空")
    private String nickname;
    @NotNull(message = "手机号码不可为空")
    @Length(max = 11, min = 11, message = "手机号码为11位数字")
    private String mobile;
    @NotNull(message = "邮箱不可为空")
    private String email;
}
