package com.zyd.blog.business.vo;

import com.zyd.blog.business.entity.ArticleLook;
import com.zyd.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *

 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleLookConditionVO extends BaseConditionVO {
	private ArticleLook articleLook;
}

