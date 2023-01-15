package com.zyd.blog.business.vo;

import com.zyd.blog.business.entity.ArticleTags;
import com.zyd.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleTagsConditionVO extends BaseConditionVO {
	private ArticleTags articleTags;
}

