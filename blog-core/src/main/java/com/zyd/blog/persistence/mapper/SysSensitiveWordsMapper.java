package com.zyd.blog.persistence.mapper;

import com.zyd.blog.business.entity.SensitiveWords;
import com.zyd.blog.business.vo.SensitiveWordsConditionVO;
import com.zyd.blog.business.vo.UserConditionVO;
import com.zyd.blog.persistence.beans.SysSensitiveWords;
import com.zyd.blog.persistence.beans.SysUser;
import com.zyd.blog.plugin.BaseMapper;
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
public interface SysSensitiveWordsMapper extends BaseMapper<SysSensitiveWords> {

    List<SysSensitiveWords> findPageBreakByCondition(SensitiveWordsConditionVO vo);


    SysSensitiveWords getByValue(String value);
}
