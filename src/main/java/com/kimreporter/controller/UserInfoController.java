package com.kimreporter.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.domain.LoginDTO;
import com.kimreporter.domain.UserInfoVO;
import com.kimreporter.service.UserInfoService;
import com.kimreporter.utils.PassCrypto;

@Controller
@RequestMapping("/user")
public class UserInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Inject
	UserInfoService service;
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public void RegisterGET() throws Exception {
		logger.info("get register");
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String RegisterPOST(UserInfoVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("post register");
		
		String	encText = PassCrypto.encode(vo.getUser_pwd());
		vo.setUser_pwd(encText);
		service.register(vo);
		rttr.addFlashAttribute("msg", "REGISTERED");
		
		return "redirect:/" ;
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) throws Exception{
	}
	
	@RequestMapping(value = "/myadaptation", method=RequestMethod.GET)
	public String myAdaptationGET(HttpSession session, Model model) throws Exception{
		UserInfoVO vo = (UserInfoVO) session.getAttribute("login");
		List<AdaptationVO> myAdaptations = service.selectMyAdaptations(vo.getUser_id());
		int count = service.selectListCount(vo.getUser_id());
		model.addAttribute("list", myAdaptations);
		model.addAttribute("count", count);
		logger.info(String.valueOf(count));
		return "/user/myadaptation";
	}
	
	@RequestMapping(value = "/mypage", method=RequestMethod.GET)
	public String myPageGET(HttpSession session, Model model) throws Exception{
		UserInfoVO vo = (UserInfoVO) session.getAttribute("login");
		model.addAttribute(service.selectData(vo.getUser_id()));
		return "/user/mypage";
	}
	
	@RequestMapping(value = "/mypage", method=RequestMethod.POST) 
	public String myPagePost(UserInfoVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("Modify POST");
		
		String	encText = PassCrypto.encode(vo.getUser_pwd());
		vo.setUser_pwd(encText);
		
		service.updateUser(vo);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/user/mypage";
	}
	
	@RequestMapping(value = "/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpServletResponse response, HttpSession session, Model model) throws Exception{
		logger.info("LOGIN POST");
		String LOGIN = "login";
		
		UserInfoVO vo = new UserInfoVO();
		
		try {
			vo = service.login(dto);
			boolean passMatch = PassCrypto.matches(dto.getUser_pwd(), vo.getUser_pwd());
			
			if (vo != null && passMatch) {
				logger.info("LOGIN SUCCESS");
				session.setAttribute(LOGIN, vo);
				response.sendRedirect("/");
			}
			
			else {
				logger.info("LOGIN - WRONG PASSWORD");
				response.sendRedirect("/user/login");
			}
		}
		catch (NullPointerException e){
			logger.info("LOGIN - WRONG PASSWORD");
			response.sendRedirect("/user/login");
		}
		
		model.addAttribute("User", vo);
	}
	
	@RequestMapping(value= "/logout", method=RequestMethod.GET) 
	public String logout(HttpSession session, RedirectAttributes rttr) throws Exception {
		logger.info("LOGOUT");
		service.logout(session);
		rttr.addFlashAttribute("msg", "logout");
		return "redirect:/";
	}
	
}
