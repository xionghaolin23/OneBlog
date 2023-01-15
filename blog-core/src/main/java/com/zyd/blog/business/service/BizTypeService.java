package com.zyd.blog.business.service;


import com.zyd.blog.framework.object.AbstractService;
import com.zyd.blog.business.entity.Type;
import com.zyd.blog.business.vo.TypeConditionVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分类
 *

 */
public interface BizTypeService extends AbstractService<Type, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Type> findPageBreakByCondition(TypeConditionVO vo);

    List<Type> listParent();

    List<Type> listTypeForMenu();

    List<Type> listTypeByPosition(String position);
}
