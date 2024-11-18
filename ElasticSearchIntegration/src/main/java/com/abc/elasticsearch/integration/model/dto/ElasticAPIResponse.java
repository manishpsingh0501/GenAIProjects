package com.abc.elasticsearch.integration.model.dto;

import lombok.Data;

@Data
public class ElasticAPIResponse {
	String responseStatus;
	String rollbackStatus;
	String failureReason;
}
