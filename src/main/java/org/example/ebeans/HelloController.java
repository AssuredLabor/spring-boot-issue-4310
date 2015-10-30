package org.example.ebeans;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@Autowired
	HelloService helloService;

	public HelloController() {
	}

	@RequestMapping("/mvc/hello")
	public @ResponseBody String hello() {
		return helloService.hello();
	}

	@RequestMapping("/mvc/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", "test");
		return "welcome";
	}
}