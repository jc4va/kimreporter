package com.kimreporter.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.domain.UserInfoVO;
import com.kimreporter.service.AdaptationService;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// Adaptation: Service 단에서 크롤링 한 이후로 modify구문이 작동하지 않음. 

@Controller
@RequestMapping("/adaptation/*")
public class AdaptationController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdaptationController.class);
	
	@Inject
	private AdaptationService service;
	
	@RequestMapping(value = "/w/create", method = RequestMethod.GET) 
	public void createGET(AdaptationVO adaptation, Model model) throws Exception {
		logger.info("Adaptation Create GET");
		service.regist(adaptation);
		model.addAttribute("msg", "SUCCESS");
		logger.info("CRAWLING STARTED");
	}
	
	@RequestMapping(value = "/w/read", method = RequestMethod.GET)
	public void read(@RequestParam("adaptation_id") String adaptation_id, Model model) throws Exception{
		logger.info("Read GET");
		model.addAttribute(service.read(adaptation_id));
	}
	
	@RequestMapping(value = "/w/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("List of All Adaptations GET");
		model.addAttribute("list", service.listAll());
		logger.info(service.listAll().toString());
	}
	
	@RequestMapping(value = "/w/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("adaptation_id") String adaptation_id, RedirectAttributes rttr) throws Exception {
		logger.info("Delete GET");
		service.delete(adaptation_id);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/adaptation/w/listAll";
	}
	
	@RequestMapping(value = "/w/modify", method = RequestMethod.GET)
	public void modify(String adaptation_id, Model model) throws Exception{
		logger.info("Modify GET");
		model.addAttribute(service.read(adaptation_id));
	}
	
	@RequestMapping(value = "/w/modify", method = RequestMethod.POST)
	public String modifyPOST(AdaptationVO adaptation, RedirectAttributes rttr) throws Exception {
		logger.info("Modify POST");
		
		service.modify(adaptation);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/adaptation/w/listAll";
	}
	
	// NUGU PLAY와 연결하는 부분 
	@RequestMapping(value = "/p/s_n_default", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject listAllDefault(@RequestBody JSONObject request) throws Exception {
		JSONObject output = new JSONObject();

		List<AdaptationVO > all_list = service.listAll();
		for (int i = 0; i < 5; i++) {
	    	output.put("news" + String.valueOf(i+1), String.valueOf(i+1) + "번. " + all_list.get(i).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
	@RequestMapping(value = "/p/s_n_next1", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject listAllNext1(@RequestBody JSONObject request) throws Exception {
		JSONObject output = new JSONObject();
		List<AdaptationVO > all_list = service.listAll();
		for (int i = 0; i < 5; i++) {
	    	output.put("news" + String.valueOf(i+1), String.valueOf(i+1) + "번. " + all_list.get(i+5).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
	@RequestMapping(value = "/p/s_n_next2", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject listAllNext2(@RequestBody JSONObject request) throws Exception {
		JSONObject output = new JSONObject();
		List<AdaptationVO > all_list = service.listAll();
		for (int i = 0; i < 5; i++) {
	    	output.put("news" + String.valueOf(i+1), String.valueOf(i+1) + "번. " + all_list.get(i+10).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
	@RequestMapping(value = "/p/s_n_next3", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject listAllNext3(@RequestBody JSONObject request) throws Exception {
		JSONObject output = new JSONObject();
		List<AdaptationVO > all_list = service.listAll();
		for (int i = 0; i < 5; i++) {
	    	output.put("news" + String.valueOf(i+1), String.valueOf(i+1) + "번. " + all_list.get(i+15).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
	@RequestMapping(value = "/p/s_n_numSingle", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject readByNumSingle(@RequestBody JSONObject request) throws Exception {
		JSONObject output = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode obj = mapper.readTree(request.toString());
		int index = Integer.valueOf(obj.at("/action/parameters/index/value").asText());
		List<AdaptationVO > all_list = service.listAll();
		output.put("news" + String.valueOf(index), String.valueOf(index) + "번. " + all_list.get(index - 1).getAdaptation_content());
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
	@RequestMapping(value = "/p/s_n_numFrom", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject readByNumFrom(@RequestBody JSONObject request) throws Exception {
		JSONObject output = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode obj = mapper.readTree(request.toString());
		int index = Integer.valueOf(obj.at("/action/parameters/index/value").asText());
		List<AdaptationVO > all_list = service.listAll();
		for (int i = 0; i < 5; i++) {
	    	output.put("news" + String.valueOf(i+1), String.valueOf(i+1) + "번. " + all_list.get(index).getAdaptation_content());
	    	index = index + 1;
	    }
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
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
	
}
