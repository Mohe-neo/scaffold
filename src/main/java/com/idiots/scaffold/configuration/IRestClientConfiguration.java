package com.idiots.scaffold.configuration;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author idiots-devil
 * @since 2023-05-30
 */
@Data
@Slf4j
@Configuration
public class IRestClientConfiguration {
    @Bean(name = "esIndex")
    public static String esIndex() {
        // 此处模拟按日期创建索引
        // 由于方便测试，索引直接返回时间戳，作为索引名称，时间可以为yyyy-MM-dd
        String date = DateUtil.format(DateUtil.parse(DateUtil.now()), "yyyy-MM-dd");
        log.info("date:{}", date);
        return date;
    }

}
