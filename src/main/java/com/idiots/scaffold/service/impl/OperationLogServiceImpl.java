package com.idiots.scaffold.service.impl;

import com.idiots.scaffold.configuration.IRestClientConfiguration;
import com.idiots.scaffold.model.pojo.LogInfo;
import com.idiots.scaffold.repository.OperationLogRepository;
import com.idiots.scaffold.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author idiots-devil
 * @since 2023-05-30
 */
@Slf4j
@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;


    @Override
    public Boolean createdIndex() {
        log.info("创建es索引...");
        return true;
    }

    @Override
    public Boolean deletedIndex() {
        String delete = elasticsearchTemplate.delete(LogInfo.class);
        log.info("删除es索引:{}...", delete);
        return true;
    }


    @Resource
    private ApplicationContext applicationContext;

    // 每天0点
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateEsIndex() {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        // 销毁指定实例
        defaultListableBeanFactory.destroySingleton("esIndex");
        String index = IRestClientConfiguration.esIndex();
        // 重新注册
        defaultListableBeanFactory.registerSingleton("esIndex", index);

        // 对索引进行一次手动创建(因为自动创建会丢失shards参数)
        IndexOperations indexOperations = elasticsearchTemplate.indexOps(LogInfo.class);
        if (!indexOperations.exists()) {
            indexOperations.create();
        }
    }

    @Resource
    private OperationLogRepository operationLogRepository;

    @Override
    public void insertLogInfo(LogInfo info){
        LogInfo logInfo = operationLogRepository.save(info);
        log.info("{}",logInfo);
    }


}
