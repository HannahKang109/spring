package kr.green.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeGet(Model model) {
		return "home";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(Model model, String id, String pw) {
		System.out.println("id : "+id+", pw : "+pw);
		return "redirect:/";
	}
}
