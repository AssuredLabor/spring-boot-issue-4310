package org.example.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * This just proves Spring MVC is working and it redirects to the Stripes ActionBean
 * @author jpollak
 *
 */
@Controller
@RequestMapping("/")
@Slf4j
public class MainPageController {

	@RequestMapping(method=RequestMethod.GET)
	public String mainPage(HttpSession session) {
		
		return "redirect:/page/welcome";
	}
	
}
