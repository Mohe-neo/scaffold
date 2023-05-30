package com.idiots.scaffold.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * @author idiots-devil
 * @since 2023-05-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "operation_#{@esIndex}")
public class LogInfo {
    @Id
    private String id;
    private LocalDateTime timestamp;
    @Field(type = FieldType.Text)
    private String username;
    @Field(type = FieldType.Text)
    private String uri;
    @Field(type = FieldType.Keyword)
    private String method;
    @Field(type = FieldType.Text)
    private String host;
    @Field(type = FieldType.Text)
    private String addr;
    @Field(type = FieldType.Keyword)
    private String result;
    @Field(type = FieldType.Text)
    private String desc;
}
