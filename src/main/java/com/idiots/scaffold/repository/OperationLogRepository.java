package com.idiots.scaffold.repository;

import com.idiots.scaffold.model.pojo.LogInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author idiots-devil
 * @since 2023-05-30
 */
@Repository
public interface OperationLogRepository extends ElasticsearchRepository<LogInfo, String> {
}
