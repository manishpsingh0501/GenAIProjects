package com.abc.elasticsearch.integration.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DataToBeAnalyzed {
	String system;
	String platform;
	String customerId;
	String apiInteractionId;
	String searchType;
	String searchText;
}
