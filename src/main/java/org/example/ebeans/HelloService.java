package org.example.ebeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaje.ebean.EbeanServer;

@Service
public class HelloService {

	@Autowired
	EbeanServer server;

	public String hello() {
		return "Hello Service";
	}
}