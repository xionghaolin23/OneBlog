package com.zyd.blog.business.service;

import java.util.List;
import java.util.Map;

/**
 * 归档目录
 *
 */
public interface BizArticleArchivesService {

    /**
     * 获取归档目录列表
     *
     * @return
     */
    Map<String, List> listArchives();
}
