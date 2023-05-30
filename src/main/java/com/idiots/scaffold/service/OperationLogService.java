package com.idiots.scaffold.service;

import com.idiots.scaffold.model.pojo.LogInfo;

/**
 * @author idiots-devil
 * @since 2023-05-30
 */
public interface OperationLogService {
    /**
     * 创建索引
     * @return true|false
     */
    Boolean createdIndex();

    /**
     * 删除索引
     * @return true|false
     */
    Boolean deletedIndex();

    void insertLogInfo(LogInfo info);
}
