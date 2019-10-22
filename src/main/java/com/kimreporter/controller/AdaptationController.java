package com.kimreporter.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.domain.UserInfoVO;
import com.kimreporter.service.AdaptationService;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// Adaptation: Service 단에서 크롤링 한 이후로 modify구문이 작동하지 않음. 

@Controller
@RequestMapping("/adaptation/*")
public class AdaptationController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdaptationController.class);
	
	@Inject
	private AdaptationService service;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET) 
	public void createGET(AdaptationVO adaptation, Model model) throws Exception {
		logger.info("Adaptation Create GET");
		service.regist(adaptation);
		model.addAttribute("msg", "SUCCESS");
		logger.info("CRAWLING STARTED");
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("adaptation_id") String adaptation_id, Model model) throws Exception{
		logger.info("Read GET");
		model.addAttribute(service.read(adaptation_id));
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("List of All Adaptations GET");
		model.addAttribute("list", service.listAll());
		logger.info(service.listAll().toString());
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("adaptation_id") String adaptation_id, RedirectAttributes rttr) throws Exception {
		logger.info("Delete GET");
		service.delete(adaptation_id);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/adaptation/listAll";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(String adaptation_id, Model model) throws Exception{
		logger.info("Modify GET");
		model.addAttribute(service.read(adaptation_id));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(AdaptationVO adaptation, RedirectAttributes rttr) throws Exception {
		logger.info("Modify POST");
		
		service.modify(adaptation);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/adaptation/listAll";
	}
	
	// NUGU PLAY와 연결하는 부분 
	
	@RequestMapping(value = "/s_n_default", method = RequestMethod.GET)
	public JSONObject listAllDefault(Model model) throws Exception {
		JSONObject output = new JSONObject();
		model.addAttribute("list", service.listAll());
		List<AdaptationVO > all_list = service.listAll();
		for (int i = 1; i < 6; i++) {
	    	output.put("news" + String.valueOf(i), String.valueOf(i) + "번. " + all_list.get(i).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
	@RequestMapping(value = "/s_n_next1", method = RequestMethod.GET)
	public JSONObject listAllNext1(Model model) throws Exception {
		JSONObject output = new JSONObject();
		model.addAttribute("list", service.listAll());
		List<AdaptationVO > all_list = service.listAll();
		for (int i = 6; i < 11; i++) {
	    	output.put("news" + String.valueOf(i), String.valueOf(i) + "번. " + all_list.get(i).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
	@RequestMapping(value = "/s_n_next2", method = RequestMethod.GET)
	public JSONObject listAllNext2(Model model) throws Exception {
		JSONObject output = new JSONObject();
		model.addAttribute("list", service.listAll());
		List<AdaptationVO > all_list = service.listAll();
		for (int i = 11; i < 16; i++) {
	    	output.put("news" + String.valueOf(i), String.valueOf(i) + "번. " + all_list.get(i).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
	@RequestMapping(value = "/s_n_next3", method = RequestMethod.GET)
	public JSONObject listAllNext3(Model model) throws Exception {
		JSONObject output = new JSONObject();
		model.addAttribute("list", service.listAll());
		List<AdaptationVO > all_list = service.listAll();
		for (int i = 16; i < 21; i++) {
	    	output.put("news" + String.valueOf(i), String.valueOf(i) + "번. " + all_list.get(i).getAdaptation_content());
	    }
	    JSONObject response_json = JSONBuilder(output);
		logger.info(response_json.toString());
	    return response_json;
	}
	
	@RequestMapping(value = "/s_n_num", method = RequestMethod.GET)
	public JSONObject readByNum(Model model) throws Exception {
		JSONObject output = new JSONObject();
		model.addAttribute("list", service.listAll());
		List<AdaptationVO > all_list = service.listAll();
		for (int i = 16; i < 21; i++) {
	    	output.put("news" + String.valueOf(i), String.valueOf(i) + "번. " + all_list.get(i).getAdaptation_content());
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
