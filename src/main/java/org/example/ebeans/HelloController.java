package org.example.ebeans;

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

	@RequestMapping("/hello")
	public @ResponseBody String hello() {
		return helloService.hello();
	}

}