package com.abc.elasticsearch.integration.controller;


import com.abc.elasticsearch.integration.model.dto.DataToBeAnalyzed;
import com.abc.elasticsearch.integration.model.entity.MobileIndex;
import com.abc.elasticsearch.integration.service.MobileIndexService;
import com.abc.elasticsearch.integration.service.ElasticSearchOperations;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/operations")
@Api(tags = "Elastic Search Operations")
@CrossOrigin(origins = "*")
public  class ElasticSearchController {
	private final Logger log = LoggerFactory.getLogger(ElasticSearchController.class);


	@Autowired
	ElasticSearchOperations elasticSearchOperations;

	@Autowired
	MobileIndexService mobileIndexService;

	@PostMapping("/push-log-payload")
	public String pushLogs (@RequestBody Map<String, Object> loggingRequest) throws ParseException {

		try {
			String index_id = (String) loggingRequest.get("index_id");
			String applicationName = (String) loggingRequest.get("application_name");
			Map<String, Object> mPayLoad = (Map<String, Object>) loggingRequest.get("log_processed");
			mPayLoad.put("apiinteractionid", (String) loggingRequest.get("apiinteractionid"));
			mPayLoad.put("logs", (String) loggingRequest.get("logs"));
			LocalDateTime currentTime = LocalDateTime.now();
			mPayLoad.put("current_timestamp", currentTime.toString());
			elasticSearchOperations.sendIndexData(index_id,applicationName + currentTime, mPayLoad);
			return "Success";
		}catch (Exception exception){
			String index_id = (String) loggingRequest.get("index_id");
			String applicationName = (String) loggingRequest.get("application_name");
			Map<String, Object> errorPayLoad = new HashMap<>();
			errorPayLoad.put("apiinteractionid", (String) loggingRequest.get("apiinteractionid"));
			errorPayLoad.put("logs", (String) loggingRequest.get(exception.getLocalizedMessage()));
			elasticSearchOperations.sendIndexData(applicationName, index_id + "-2024", errorPayLoad);
			log.error("Error: {}", exception.getLocalizedMessage()) ;
			return "Failed";
		}

	}
	@PostMapping("/search-error-logs")
	public List<MobileIndex> getLogs (@RequestBody Map<String, Object> loggingRequest) throws ParseException {

		try {
			String system = (String) loggingRequest.get("system");
			String index_id = "mobile_cluster";
			String customerId = (String) loggingRequest.get("customerId");
			String searchType = (String) loggingRequest.get("searchType");
			String searchText = (String) loggingRequest.get("searchText");
			String interactionId = (String) loggingRequest.get("apiInteractionId");
			//return elasticSearchOperations.getIndexData(interactionId, customerId, searchType, searchText);
			List<MobileIndex> listMobileIndex = mobileIndexService.getMobileIndexByInteractionId(interactionId);
			log.info(listMobileIndex.toString());
			return listMobileIndex;

		}catch (Exception exception){
			log.error("Error: {}", exception.getLocalizedMessage()) ;
			return null;
		}

	}
	@GetMapping("/get-data-by-jiraid")
	public DataToBeAnalyzed getDataByJiraId(@RequestParam String jiraId) throws ParseException {

		try {
			return DataToBeAnalyzed
					.builder()
					.system("MobileApp")
					.platform("microservices")
					.customerId("DD1234")
					.apiInteractionId("")
					.searchType("Error")
					.searchText("Customer Details not found")
					.build();

		}catch (Exception exception){
			log.error("Error: {}", exception.getLocalizedMessage()) ;
			return null;
		}

	}

}

