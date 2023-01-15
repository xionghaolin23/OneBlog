package com.zyd.blog.business.vo;

import com.zyd.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *

 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TypeConditionVO extends BaseConditionVO {
	private Long pid;
	private String name;
	private String description;
	private Integer sort;
	private Boolean available;
	private String icon;
}

