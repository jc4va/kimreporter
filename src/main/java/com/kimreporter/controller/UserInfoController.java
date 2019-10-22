package com.kimreporter.controller;

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

import com.kimreporter.domain.LoginDTO;
import com.kimreporter.domain.UserInfoVO;
import com.kimreporter.service.UserInfoService;
import com.kimreporter.utils.PassCrypto;

@Controller
@RequestMapping("/user/*")
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
	
	@RequestMapping(value = "/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpServletResponse response, HttpSession session, Model model) throws Exception{
		logger.info("LOGIN POST");
		String LOGIN = "login";
		
		UserInfoVO vo = service.login(dto);
		
		boolean passMatch = PassCrypto.matches(dto.getUser_pwd(), vo.getUser_pwd());
		
		if (vo != null && passMatch) {
			logger.info("LOGIN SUCCESS");
			session.setAttribute(LOGIN, vo);
			response.sendRedirect("/adaptation/listAll");
		}
		
		else if (vo == null || !passMatch) {
			logger.info("LOGIN - WRONG PASSWORD");
			response.sendRedirect("/user/login");
		}
		
		model.addAttribute("User", vo);
	}

}
