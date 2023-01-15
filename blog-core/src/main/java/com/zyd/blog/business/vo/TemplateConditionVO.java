package com.zyd.blog.business.vo;

import com.zyd.blog.business.entity.Template;
import com.zyd.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**

 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TemplateConditionVO extends BaseConditionVO {
	private Template template;
}

