package com.kimreporter.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void RegisterGET() throws Exception {
		logger.info("get register");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String RegisterPOST(UserInfoVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("post register");

		String encText = PassCrypto.encode(vo.getUser_pwd());
		vo.setUser_pwd(encText);
		service.register(vo);
		rttr.addFlashAttribute("msg", "REGISTERED");
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("admin@hwadu-kimreporter.appspotmail.com", "친절한 김기자"));
		msg.addRecipient(Message.RecipientType.TO,
				new InternetAddress("redhd0410@gmail.com", "친절한 김기자"));
		msg.setSubject("[친절한 김기자] 신규 가입신청");
		msg.setText("이메일: " + vo.getUser_email() + "\n" + "아이디: " + vo.getUser_id());
		Transport.send(msg);

		return "redirect:/user/register_joining";
	}

	@RequestMapping(value = "/approve_user", method = RequestMethod.PUT)
	public ResponseEntity<String> approveUser(@RequestParam String user_id) throws Exception {
		logger.info("APPROVING");
		service.updateUserStatus(user_id);
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		ResponseEntity<String> response = new ResponseEntity<String>("OK", HttpStatus.OK);

		try {
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("admin@hwadu-kimreporter.appspotmail.com", "친절한 김기자"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(service.selectData(user_id).getUser_email(), "사용자"));
			msg.setSubject("[친절한 김기자] 가입이 승인되었습니다.");
			msg.setText(service.selectData(user_id).getUser_name() + "님의 가입이 승인되셨습니다. ");
			Transport.send(msg);
			
		} catch (AddressException e) {
			response = new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MessagingException e) {
			response = new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UnsupportedEncodingException e) {
			response = new ResponseEntity<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) throws Exception {
	}

	@RequestMapping(value = "/myadaptation", method = RequestMethod.GET)
	public String myAdaptationGET(HttpSession session, Model model) throws Exception {
		UserInfoVO vo = (UserInfoVO) session.getAttribute("login");
		List<AdaptationVO> myAdaptations = service.selectMyAdaptations(vo.getUser_id());
		int count = service.selectListCount(vo.getUser_id());
		model.addAttribute("list", myAdaptations);
		model.addAttribute("count", count);
		logger.info(String.valueOf(count));
		return "user/myadaptation";
	}

	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String myPageGET(HttpSession session, Model model) throws Exception {
		UserInfoVO vo = (UserInfoVO) session.getAttribute("login");
		model.addAttribute(service.selectData(vo.getUser_id()));
		return "user/mypage";
	}

	@RequestMapping(value = "/mypage", method = RequestMethod.POST)
	public String myPagePost(UserInfoVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("Modify POST");

		String encText = PassCrypto.encode(vo.getUser_pwd());
		vo.setUser_pwd(encText);

		service.updateUser(vo);

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/user/mypage";
	}

	@RequestMapping(value = "/login_joining", method = RequestMethod.GET)
	public String loginNotYetApproved(HttpSession session, Model model) throws Exception {
		return "user/login_joining";
	}

	@RequestMapping(value = "/register_joining", method = RequestMethod.GET)
	public String registerNotYetApproved(HttpSession session, Model model) throws Exception {
		return "user/register_joining";
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpServletResponse response, HttpSession session, Model model)
			throws Exception {
		logger.info("LOGIN POST");
		String LOGIN = "login";

		UserInfoVO vo = new UserInfoVO();

		try {
			vo = service.login(dto);
			boolean passMatch = PassCrypto.matches(dto.getUser_pwd(), vo.getUser_pwd());

			if (vo != null && passMatch && vo.getIs_active() == 1) {
				logger.info("LOGIN SUCCESS");
				session.setAttribute(LOGIN, vo);
				response.sendRedirect("/");
			}

			else if (vo != null && passMatch && vo.getIs_active() == 0) {
				logger.info("NOT YET APPROVED");
				response.sendRedirect("/user/login_joining");
			}

			else {
				logger.info("LOGIN - WRONG PASSWORD");
				response.sendRedirect("/user/login");
			}
		} catch (NullPointerException e) {
			logger.info("LOGIN - WRONG PASSWORD");
			response.sendRedirect("/user/login");
		}

		model.addAttribute("User", vo);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) throws Exception {
		logger.info("LOGOUT");
		service.logout(session);
		rttr.addFlashAttribute("msg", "logout");
		return "redirect:/";
	}

}
