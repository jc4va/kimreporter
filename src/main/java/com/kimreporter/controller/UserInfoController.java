package com.kimreporter.controller;

import java.io.IOException;
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
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kimreporter.domain.AdaptationVO;
import com.kimreporter.domain.LoginDTO;
import com.kimreporter.domain.UserInfoVO;
import com.kimreporter.service.UserInfoService;
import com.kimreporter.utils.PassCrypto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

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
		
		Email from = new Email("admin@hwadu-kimreporter.appspotmail.com");
		String subject = "[친절한 김기자] 신규 가입신청";
		Email to = new Email("redhd0410@gmail.com");
		Content content = new Content("text/plain",
				"이메일: " + vo.getUser_email() + "\n" + "아이디: " + vo.getUser_id());
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		Response response = sg.api(request);

		return "redirect:/user/register_joining";
	}

	@ResponseBody
	@RequestMapping(value = "/check_id", method = RequestMethod.POST)
	public int checkID(HttpServletRequest request) throws Exception {
		String user_id = request.getParameter("user_id");
		int count = service.selectListCountID(user_id);

		logger.info(user_id);
		logger.info(String.valueOf(count));

		return count;
	}

	@RequestMapping(value = "/approve_user", method = RequestMethod.PUT)
	public ResponseEntity<String> approveUser(@RequestParam String user_id) throws Exception {
		
		logger.info("APPROVING");
		ResponseEntity<String> response = new ResponseEntity<String>("OK", HttpStatus.OK);
		
		if (service.selectData(user_id).getIs_active() != 1) {
			service.updateUserStatus(user_id);

			Email from = new Email("admin@hwadu-kimreporter.appspotmail.com");
			String subject = "[친절한 김기자] 가입이 승인되었습니다.";
			Email to = new Email(service.selectData(user_id).getUser_email());
			Content content = new Content("text/plain",
					service.selectData(user_id).getUser_name() + "님의 가입이 승인되셨습니다. ");
			Mail mail = new Mail(from, subject, to, content);

			SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response r = sg.api(request);
			
		} else {
			response = new ResponseEntity<String>("ALREADY APPROVED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto, @ModelAttribute("LR") String login_result, Model model)
			throws Exception {
		logger.info(login_result);
		model.addAttribute("LR", login_result);
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
	public String loginPOST(LoginDTO dto, HttpServletResponse response, HttpSession session,
			final RedirectAttributes rttr, Model model) throws Exception {
		logger.info("LOGIN POST");
		String LOGIN = "login";

		UserInfoVO vo = new UserInfoVO();

		model.addAttribute("User", vo);

		try {
			vo = service.login(dto);
			boolean passMatch = PassCrypto.matches(dto.getUser_pwd(), vo.getUser_pwd());

			if (vo != null && passMatch && vo.getIs_active() == 1) {
				logger.info("LOGIN SUCCESS");
				rttr.addFlashAttribute("LR", "LOGINSUCCESS");
				session.setAttribute(LOGIN, vo);
				return "redirect:/";
			}

			else if (vo != null && passMatch && vo.getIs_active() == 0) {
				logger.info("NOT YET APPROVED");
				rttr.addFlashAttribute("LR", "LOGINSUCCESS");
				return "redirect:/user/login_joining";
			}

			else {
				logger.info("LOGIN - WRONG PASSWORD");
				rttr.addFlashAttribute("LR", "LOGINFAIL");
				return "redirect:/user/login";
			}
		} catch (NullPointerException e) {
			logger.info("LOGIN - WRONG PASSWORD");
			rttr.addFlashAttribute("LR", "LOGINFAIL");
			return "redirect:/user/login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) throws Exception {
		logger.info("LOGOUT");
		service.logout(session);
		rttr.addFlashAttribute("msg", "logout");
		return "redirect:/";
	}

}
