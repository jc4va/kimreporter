package com.kimreporter.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.service.AdaptationService;

@Controller
public class ActionController {
	
private static final Logger logger = LoggerFactory.getLogger(ActionController.class);
	
	@Inject
	private AdaptationService service;
	
	public JSONObject JSONBuilder(JSONObject output) {
	    JSONObject response_json = new JSONObject();
	    JSONArray directives = new JSONArray();
	    JSONObject direct_info = new JSONObject();
	    JSONObject audioItem = new JSONObject();
	    JSONObject stream = new JSONObject(); 
	    JSONObject progressReport = new JSONObject();
	    JSONObject metaData = new JSONObject();
	    
	    // Outer
	    response_json.put("directives", directives);
	    response_json.put("resultCode", "OK");
	    response_json.put("version", "2.0");
	    response_json.put("output", output);
	    
	    // directives
	    directives.add(direct_info);
	    
	    // directive_info
	    direct_info.put("type", "AudioPlayer.Play");
	    direct_info.put("audioItem", audioItem);
	    
	    // audioItem
	    audioItem.put("stream", stream);
	    audioItem.put("metadata", metaData);
	    
	    // Stream
	    stream.put("url", "");
	    stream.put("offsetInMilliseconds", "");
	    stream.put("token", "");
	    stream.put("expectedPreviousToken", "");
	    stream.put("progressReport", progressReport);
	    
	    // ProgressReport
	    progressReport.put("progressReportDelayInMilliseconds", "");
	    progressReport.put("progressReportIntervalInMilliseconds", "");
	    
		return response_json;
		
	}
	
	@RequestMapping(value = "/s_n_default", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONObject summarizeNews(HttpServletRequest request) throws Exception {
	    // final String request_json = IOUtils.toString(request.getInputStream(), "UTF-8");
	    JSONObject output = new JSONObject();
	    List<AdaptationVO > all_list = service.listAll();
	    for (int i = 1; i < 6; i++) {
	    	output.put("news" + String.valueOf(i), String.valueOf(i) + "번. " + all_list.get(i).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
	    return response_json;
	}
	
	@RequestMapping(value = "/s_n_next1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONObject s_n_next1(HttpServletRequest request) throws Exception {
	    // final String request_json = IOUtils.toString(request.getInputStream(), "UTF-8");
	    JSONObject output = new JSONObject();
	    List<AdaptationVO > all_list = service.listAll();
	    for (int i = 6; i < 11; i++) {
	    	output.put("news" + String.valueOf(i), String.valueOf(i) + "번. " + all_list.get(i).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
	    return response_json;
	}
	
	@RequestMapping(value = "/s_n_next2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONObject s_n_next2(HttpServletRequest request) throws Exception {
	    // final String request_json = IOUtils.toString(request.getInputStream(), "UTF-8");
	    JSONObject output = new JSONObject();
	    List<AdaptationVO > all_list = service.listAll();
	    for (int i = 11; i < 16; i++) {
	    	output.put("news" + String.valueOf(i), String.valueOf(i) + "번. " + all_list.get(i).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
	    return response_json;
	}
	
	@RequestMapping(value = "/s_n_next3", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONObject s_n_next3(HttpServletRequest request) throws Exception {
	    // final String request_json = IOUtils.toString(request.getInputStream(), "UTF-8");
	    JSONObject output = new JSONObject();
	    List<AdaptationVO > all_list = service.listAll();
	    for (int i = 16; i < 20; i++) {
	    	output.put("news" + String.valueOf(i), String.valueOf(i) + "번. " + all_list.get(i).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
	    return response_json;
	}

}
