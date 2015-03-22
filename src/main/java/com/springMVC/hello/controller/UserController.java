package com.springMVC.hello.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springMVC.hello.model.User;
import com.springMVC.hello.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public User add(@RequestBody User user) {
		userService.saveUser(user);
		return user;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public User update(@RequestBody User user) {
		userService.updateUser(user);
		return user;
		
	}

	@RequestMapping(value = "signUp", method = RequestMethod.GET)
	public String signUpForm() {
		return "registerform";
	}

	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String loginForm() {
		return "loginform";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam String userId) {
		userService.deleteUser(userId);
		return homePage();
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logoutForm(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "loginform";
	}

	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	public ModelAndView checkLogin(@RequestParam String username,
			@RequestParam String password, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		
		if (username != null && password != null) {
			User user = userService.checkLogin(username, password);
			if (user != null) {
				// Create Session
				httpSession.setAttribute("success", "success");
				
				ModelAndView mv = new ModelAndView("home");
				mv.addObject("name", user.getName());
				mv.addObject("allUser", getAllUsers());
				return mv;
			}
		}
		ModelAndView mv = new ModelAndView("loginform");
		httpSession.setAttribute("failure", "Username and Password dsn't match!!");
		return mv;
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editForm(@RequestParam String userId) {
		User user = userService.EditUser(userId);
		ModelAndView mv = new ModelAndView("registerform");
		mv.addObject("user", user);
		return mv;
	}

	private List<User> getAllUsers() {
		return userService.searchAllUser();
	}
	
	@RequestMapping(value = "homePage", method = RequestMethod.GET)
	private ModelAndView homePage(){
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("allUser", getAllUsers());
		return mv;
	}
}