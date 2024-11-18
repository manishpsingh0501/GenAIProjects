package com.abc.elasticsearch.integration.model.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import javax.print.attribute.standard.DocumentName;

@Data
@Document(indexName = "_all", createIndex = false)
public class MobileIndex {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String UUID;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String apiinteractionid;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String channel;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String county;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String current_timestamp;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String errorCode;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String logs;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String message;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String payload;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String serviceCode;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String statusCode;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String type;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String url;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String gitCodeURL;
}
