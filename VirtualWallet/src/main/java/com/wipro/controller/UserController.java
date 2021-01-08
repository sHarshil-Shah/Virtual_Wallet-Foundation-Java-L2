package com.wipro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wipro.bean.User;
import com.wipro.dao.UserDAO;

@Controller
public class UserController {

	private UserDAO userDAO;

	@Autowired(required = true)
	@Qualifier(value = "userDAO")
	public void setUserDAO(UserDAO ps) {
		this.userDAO = ps;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("status") String status) {
		User user = new User();
		ModelAndView mv = new ModelAndView("login", "user", user);
		mv.addObject("status", status);
		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("register", "user", new User());
	}

	@RequestMapping(value = "/ensureUser", method = RequestMethod.GET)
	public String checkUser(@ModelAttribute("user") User user, RedirectAttributes ra, HttpServletRequest request) {
		user = userDAO.getUserByUNamePass(user);
		request.getSession().setAttribute("user", user);
		if (user != null) {
			return "redirect:/dashboard";
		} else {
			ra.addFlashAttribute("status", "Invalid Credentials");
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView gotoDashboard(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("dashboard", "user", request.getSession().getAttribute("user"));
		return mv;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userDAO.listUsers());
		return "user";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User p) {

		if (p.getUserid() == 0) {
			this.userDAO.addUser(p);
		} else {
			// existing user, call update
			this.userDAO.updateUser(p);
		}

		return "redirect:/login";

	}

	@RequestMapping("/removeUser/{id}")
	public String removeUser(@PathVariable("id") int id) {

		this.userDAO.removeUser(id);
		return "redirect:/users";
	}

	@RequestMapping("/editUser/{id}")
	public String editUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", this.userDAO.getUserById(id));
		model.addAttribute("listUsers", this.userDAO.listUsers());
		return "user";
	}

}