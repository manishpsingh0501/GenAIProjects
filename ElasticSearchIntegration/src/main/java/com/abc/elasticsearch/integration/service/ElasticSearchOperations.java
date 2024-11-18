package com.abc.elasticsearch.integration.service;

import com.abc.elasticsearch.integration.model.dto.ElasticAPIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class ElasticSearchOperations {
	private final Logger log = LoggerFactory.getLogger(ElasticSearchOperations.class);
	public ElasticSearchOperations() {
	}
	
		
	public void sendIndexData(String index,String applicationName, Map<String, Object> data) {
		
		String strEndpoint = "http://localhost:9200/"+index+"/_doc/"+applicationName;

		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
	    
	    ElasticAPIResponse respObj = new ElasticAPIResponse();
		try{	    
			RestTemplate restTemplate = new RestTemplate();
			log.info("\nSending request");

			//Response respObj=restTemplate.getForObject(url, Response.class);
			restTemplate.put(strEndpoint, request);

				log.info("\nResponse: \nStatus: {}, FailureReason:{}, RollbackStatus:{}",
						respObj.getResponseStatus(),respObj.getFailureReason(),respObj.getRollbackStatus());
		}
		catch (Exception e){
	        log.error("\nException:{}",e);
			respObj.setFailureReason("Configuration API Down");
			respObj.setResponseStatus("failure");
			respObj.setRollbackStatus("NA");
			log.error("\nError Response: \nStatus: {}, FailureReason:{}, RollbackStatus:{}",
					respObj.getResponseStatus(),respObj.getFailureReason(),respObj.getRollbackStatus());

		}
	}

	private String JSON(String data) {
		// TODO Auto-generated method stub
		return null;
	}
//	public Map<String, Object> getIndexData(String apiInteractionId, String customerId,
//											String searchType, String searchText) throws Exception{
//		String url = "http://localhost:9200/_all/_search/q="+apiInteractionId;
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//
//		HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(null, headers);
//
//		RestTemplate restTemplate = new RestTemplate();
//		log.info("\nSending request:{}", url);
//
//		Map<String, Object> response=restTemplate.getForObject(url, Map.class);
//		response.put("gitCodeSnippetURL",gitOperations.getRepoURL());
//
//
//		log.info("Response:{}",response);
//		return response;
//	}
}
