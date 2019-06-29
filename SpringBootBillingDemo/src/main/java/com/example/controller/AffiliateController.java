package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Product;
import com.example.service.UserService;

@Controller
@RequestMapping("/affiliate")
public class AffiliateController {
	Logger logger = LoggerFactory.getLogger(AffiliateController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("logout-success")
	public String logoutPage() {
		return "login.jsp";
	}
	@RequestMapping(value = "profile")
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView();
		try {
			logger.info("Into the Affiliate Profile Page");
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String username = userDetails.getUsername();
			mv.setViewName("/employer.jsp");
			mv.addObject("loggedInUser", username);
			return mv;

		} catch (Exception e) {
			mv.setViewName("/error.jsp");
			logger.error("Error occured", e);
			return mv;
		}
	}

	@RequestMapping("buy")
	public ModelAndView buyproduct(@ModelAttribute Product product, RedirectAttributes redir) {

		try {
			logger.info("Into Employers Buy");
			String name = product.getName();
			System.out.println(product);
			double rate = userService.findByNameAffiliate(name);
			System.out.println(rate);
			ModelAndView mv = new ModelAndView("redirect:/updatebill");
			redir.addFlashAttribute("product", product);
			redir.addFlashAttribute("rate", rate);
			return mv;
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("/error.jsp");
			logger.error("Error occured", e);
			return mv;
		}
	}
}
