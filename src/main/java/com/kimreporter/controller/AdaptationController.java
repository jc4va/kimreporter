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
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/adaptation")
public class AdaptationController {

	private static final Logger logger = LoggerFactory.getLogger(AdaptationController.class);

	@Inject
	private AdaptationService service;
	private JSONObject response_json = new JSONObject();
	private ObjectMapper mapper = new ObjectMapper();

	// 웹에서 보여지는 부분

	// 번안 정보 조회
	@RequestMapping(value = "/w/read", method = RequestMethod.GET)
	public void read(@RequestParam("adaptation_id") String adaptation_id, Model model) throws Exception {
		logger.info("Read GET");
		model.addAttribute(service.read(adaptation_id));
	}

	// 전체 번안 가져오기
	@RequestMapping(value = "/w/listAll", method = RequestMethod.GET)
	public void listAll(Model model, HttpSession session) throws Exception {
		logger.info("List of All Adaptations GET");
		model.addAttribute("list", service.listAll());
		logger.info(session.getAttribute("login").toString());
		logger.info(service.listAll().toString());
	}

	// 번안 수정 GET
	@RequestMapping(value = "/w/modify", method = RequestMethod.GET)
	public void modify(String adaptation_id, Model model) throws Exception {
		logger.info("Modify GET");
		model.addAttribute(service.read(adaptation_id));
	}

	// 번안 수정 POST
	@RequestMapping(value = "/w/modify", method = RequestMethod.POST)
	public String modifyPOST(AdaptationVO adaptation, RedirectAttributes rttr) throws Exception {
		logger.info("Modify POST");

		service.modify(adaptation);

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/adaptation/w/listAll";
	}

	// NUGU PLAY와 연결하는 부분

	// 번안 삭제
	@RequestMapping(value = "/p/delete", method = RequestMethod.GET)
	public void delete(RedirectAttributes rttr) throws Exception {
		logger.info("Delete GET");
		service.delete();
		rttr.addFlashAttribute("msg", "SUCCESS");
	}

	// 크롤링 시작
	@RequestMapping(value = "/p/create", method = RequestMethod.GET)
	public void createGET(AdaptationVO adaptation, Model model) throws Exception {
		service.regist(adaptation);
		model.addAttribute("msg", "SUCCESS");
		logger.info("CRAWLING STARTED");
	}

	// 발화 시작
	@RequestMapping(value = "/p/summarizeNews", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject summarizeNews(@RequestBody JSONObject request) throws Exception {

		logger.info("SUMMARIZE NEWS");

		JSONObject output = new JSONObject();

		// 리스폰스 JSON에 디폴트 결과값만을 담아서 보냄
		response_json = JSONBuilder(output);
		return response_json;
	}

	// 처음 다섯개 뉴스 가져옴
	@RequestMapping(value = "/p/action.playNews", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject listAllDefault(@RequestBody JSONObject request) throws Exception {

		logger.info("S_N_DEFAULT");

		JSONObject output = new JSONObject();

		// 리스트 정보 가져오기
		List<AdaptationVO> all_list = service.listAll();

		// 포문 돌아가면서 리스폰스 결과 추가
		for (int i = 0; i < 5; i++) {
			output.put("news" + String.valueOf(i + 1), String.valueOf(i + 1) + "번. " + all_list.get(i).getAdaptation_content());
		}

		// 리스폰스 JSON 빌드
		response_json = JSONBuilder(output);
		logger.info(response_json.toString());
		return response_json;
	}

	// 다음 다섯개 뉴스 가져옴
	@RequestMapping(value = "/p/action.playNext1", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject listAllNext1(@RequestBody JSONObject request) throws Exception {

		logger.info("S_N_NEXT1");

		JSONObject output = new JSONObject();

		// 리스트 정보 가져오기
		List<AdaptationVO> all_list = service.listAll();

		// 포문 돌아가면서 리스폰스 결과 추가
		for (int i = 0; i < 5; i++) {
			output.put("news" + String.valueOf(i + 1), String.valueOf(i + 6) + "번. " + all_list.get(i + 5).getAdaptation_content());
		}

		// 리스폰스 JSON 빌드
		response_json = JSONBuilder(output);
		logger.info(response_json.toString());

		return response_json;
	}

	// 다음 다섯개 뉴스 가져옴
	@RequestMapping(value = "/p/action.playNext2", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject listAllNext2(@RequestBody JSONObject request) throws Exception {

		logger.info("S_N_NEXT2");

		JSONObject output = new JSONObject();

		// 리스트 정보 가져오기
		List<AdaptationVO> all_list = service.listAll();

		// 포문 돌아가면서 리스폰스 결과 추가
		for (int i = 0; i < 5; i++) {
			output.put("news" + String.valueOf(i + 1), String.valueOf(i + 11) + "번. " + all_list.get(i + 10).getAdaptation_content());
		}

		// 리스폰스 JSON 빌드
		response_json = JSONBuilder(output);
		logger.info(response_json.toString());

		return response_json;
	}

	// 다음 다섯개 뉴스 가져옴
	@RequestMapping(value = "/p/action.playNext3", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject listAllNext3(@RequestBody JSONObject request) throws Exception {

		logger.info("S_N_NEXT3");

		JSONObject output = new JSONObject();

		// 리스트 정보 가져오기
		List<AdaptationVO> all_list = service.listAll();

		// 포문 돌아가면서 리스폰스 결과 추가
		for (int i = 16; i < service.listAll().size() + 1; i++) {
			output.put("news" + String.valueOf(i - 15), String.valueOf(i) + "번. " + all_list.get(i - 1).getAdaptation_content());
		}

		// 리스폰스 JSON 빌드
		response_json = JSONBuilder(output);
		logger.info(response_json.toString());

		return response_json;
	}

	// 인풋 뉴스 발화 시작
	@RequestMapping(value = "/p/action.playNum", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject readByNum(@RequestBody JSONObject request) throws Exception {

		logger.info("S_N_NUM");

		// 리스트 정보 가져오기
		List<AdaptationVO> all_list = service.listAll();

		JSONObject output = new JSONObject();

		// 리퀘스트 JSON 파싱
		JsonNode obj = mapper.readTree(request.toString());
		int index = Integer.valueOf(obj.at("/action/parameters/index/value").asText());

		// 리스폰스 JSON 빌드
		if (index <= all_list.size()) {
			output.put("flag", "True");
		} else {
			output.put("flag", "False");
		}
		response_json = JSONBuilder(output);

		logger.info(response_json.toString());

		return response_json;
	}

	// 인풋 받은 숫자를 인덱스로 사용해서 번안 가져오기
	@RequestMapping(value = "/p/action.playOne", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject readByNumSingle(@RequestBody JSONObject request) throws Exception {

		logger.info("S_N_NUMSINGLE");

		// 리스트 정보 가져오기
		List<AdaptationVO> all_list = service.listAll();
		StringBuffer news = new StringBuffer();

		JSONObject output = new JSONObject();

		// 리퀘스트 JSON 파싱
		JsonNode obj = mapper.readTree(request.toString());
		int index = Integer.valueOf(obj.at("/action/parameters/index/value").asText());

		// 리스폰스 JSON 빌드
		output.put("flag", "True");
		news.append(String.valueOf(index) + "번. " + all_list.get(index - 1).getAdaptation_content());
		output.put("news", news.toString());
		response_json = JSONBuilder(output);

		logger.info(response_json.toString());

		return response_json;
	}

	// 인풋 받은 숫자부터 다섯개 번안 가져오기
	@RequestMapping(value = "/p/action.playMany", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject readByNumFrom(@RequestBody JSONObject request) throws Exception {

		logger.info("S_N_NUMFROM");

		JSONObject output = new JSONObject();

		// 리스트 정보 가져오기
		List<AdaptationVO> all_list = service.listAll();
		StringBuffer news = new StringBuffer();

		// 리퀘스트 JSON 파싱
		JsonNode obj = mapper.readTree(request.toString());
		int index = Integer.valueOf(obj.at("/action/parameters/index/value").asText());

		// 포문 돌아가면서 리스폰스 결과 추가
		int i = 0;
		while (index <= service.listAll().size() && i < 5) {
			news.append(String.valueOf(index) + "번. " + all_list.get(index).getAdaptation_content());
			index = index + 1;
			i += 1;
		}

		// 리스폰스 JSON 빌드
		output.put("flag", "True");
		output.put("news", news.toString());
		response_json = JSONBuilder(output);

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
