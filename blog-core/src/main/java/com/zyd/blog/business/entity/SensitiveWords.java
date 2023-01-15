package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.business.enums.*;
import com.zyd.blog.framework.object.AbstractBO;
import com.zyd.blog.persistence.beans.SysSensitiveWords;
import com.zyd.blog.persistence.beans.SysUser;
import com.zyd.blog.util.PasswordUtil;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 *  2018/4/16 16:26
 * @since 1.0
 */
public class SensitiveWords extends AbstractBO {
    private SysSensitiveWords sysSensitiveWords;

    public SensitiveWords() {
        this.sysSensitiveWords = new SysSensitiveWords();
    }

    public SensitiveWords(SysSensitiveWords sysSensitiveWords) {
        this.sysSensitiveWords = sysSensitiveWords;
    }


    @JsonIgnore
    public SysSensitiveWords getSensitiveWords() {
        return this.sysSensitiveWords;
    }

    public Long getId() {
        return this.sysSensitiveWords.getId();
    }

    public void setId(Long id) {
        this.sysSensitiveWords.setId(id);
    }

    public String getValue() {
        return this.sysSensitiveWords.getValue();
    }

    public void setValue(String value) {
        this.sysSensitiveWords.setValue(value);
    }


}
