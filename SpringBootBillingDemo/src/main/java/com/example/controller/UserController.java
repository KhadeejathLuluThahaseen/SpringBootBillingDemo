package com.example.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Billing;
import com.example.model.Product;
import com.example.model.User;
import com.example.service.UserService;

@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;



	@RequestMapping("/registration")
	public String registration() {
		return "registration.jsp";
	}

	@RequestMapping("/")
	public String home() {
		return "front.html";
	}

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String registration(User user) {
		System.out.println(user.getUsername());
		String a = user.getUsername();
		System.out.println(userService.findByUsername(a));
		if ((userService.findByUsername(a)) == null)

		// if (user.getUsername() == userService.findByUsername(user)) {
		// return "error.jsp";
		// }
		{
			userService.save(user);

			return "welcome.jsp";
		} else {
			return "error.jsp";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "login.jsp";

	}

	@RequestMapping("/hello")
	public String sayhello() {
		return "hello.jsp";
	}

	@RequestMapping("logout-success")
	public String logoutPage() {
		return "login.jsp";
	}

	@GetMapping("/profile")
	public String logins() {
		logger.info("into the Profile page");
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			// String username = userDetails.getUsername();
			// System.out.println(userDetails.getAuthorities());
			String seeker = "EMPLOYER";
			if ((userDetails.getAuthorities()).contains(new SimpleGrantedAuthority(seeker))) {
				return "redirect:/employer/profile";
			} else if ((userDetails.getAuthorities()).contains(new SimpleGrantedAuthority("AFFILIATE"))) {
				return "redirect:/affiliate/profile";
			} else if ((userDetails.getAuthorities()).contains(new SimpleGrantedAuthority("CUSTOMER"))) {
				return "redirect:/customer/profile";
			} else if ((userDetails.getAuthorities()).contains(new SimpleGrantedAuthority("ADMIN"))) {
				return "redirect:/admin/profile";
			} else {
				return "error.jsp";
			}
		} catch (Exception e) {
			logger.error("Error occured", e);
			return "error.jsp";
		}

	}

	@RequestMapping("buy")
	public ModelAndView buyproduct(@ModelAttribute Product product, RedirectAttributes redir) {
		logger.info("into the Buy Product Page");

		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			// String username = userDetails.getUsername();
			// System.out.println(userDetails.getAuthorities());
			System.out.println(product);
			String seeker = "EMPLOYER";
			if ((userDetails.getAuthorities()).contains(new SimpleGrantedAuthority(seeker))) {
				// System.out.println(product);
				ModelAndView mv = new ModelAndView("redirect:/employer/buy");
				redir.addFlashAttribute("product", product);
				return mv;
			} else if ((userDetails.getAuthorities()).contains(new SimpleGrantedAuthority("AFFILIATE"))) {
				ModelAndView mv = new ModelAndView("redirect:/affiliate/buy");
				redir.addFlashAttribute("product", product);
				return mv;
			} else if ((userDetails.getAuthorities()).contains(new SimpleGrantedAuthority("CUSTOMER"))) {
				ModelAndView mv = new ModelAndView("redirect:/customer/buy");
				redir.addFlashAttribute("product", product);
				return mv;
			} else {
				ModelAndView mv = new ModelAndView();
				mv.setViewName("/error.jsp");
				return mv;
			}
		} catch (Exception e) {
			logger.error("Error occured", e);
			ModelAndView mv = new ModelAndView();
			mv.setViewName("/error.jsp");
			return mv;

		}
	}

	@RequestMapping("mycart")
	public ModelAndView mycart(Principal principal) {
		logger.info("into the My Cart Page");
		ModelAndView mv = new ModelAndView();
		try {
			String username = principal.getName();
			long id = userService.findId(username);
			System.out.println(id);
			Double rate = userService.findrate(id);
			int items = userService.finditems(id);
			mv.setViewName("/totalrate.jsp");
			mv.addObject("rate", rate);
			mv.addObject("items", items);
			return mv;
		} catch (Exception e) {
			logger.error("Error occured", e);
			mv.setViewName("/error.jsp");
			return mv;

		}
	}

	@RequestMapping("updatebill")
	public ModelAndView updatebill(@ModelAttribute Product product, RedirectAttributes redir, Model model,
			Principal principal, @ModelAttribute Billing billing, @ModelAttribute User user) {

		try {
			logger.info("Into Employers Update Bill");
			String username = principal.getName();
			long id = userService.findId(username);
			System.out.println(id);
			user.setId(id);
			Double rate = (Double) model.asMap().get("rate");
			System.out.println(rate);
			// billing.setUser(user);
			Double prevrate = userService.findrate(id);
			System.out.println(prevrate);
			Double totalcost = prevrate + rate;
			// billing.setTotalCost(totalcost);
			int items = userService.finditems(id);
			if (rate > 0) {
			items = items + 1;
			int i = userService.update(totalcost, items, id);
			System.out.println(i);
			ModelAndView mv = new ModelAndView();
			if (i == 1) {
				mv.setViewName("/success.jsp");
				return mv;
			} else {
					mv.setViewName("/error.jsp");
					return mv;
				}
			} else {
				Product prod = (Product) model.asMap().get("product");
				System.out.println(prod);
				String name = prod.getName();
				Double rates = userService.findproductrate(name);
				ModelAndView mv = new ModelAndView();
				mv.setViewName("/success2.jsp");
				mv.addObject("rates", rates);
				return mv;
			}
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView();

			mv.setViewName("/error.jsp");
			logger.error("Error occured", e);
			return mv;

		}
	}

}
