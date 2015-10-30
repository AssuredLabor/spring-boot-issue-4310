package org.example.action;

import org.example.stripes.BaseActionBean;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@Slf4j
@UrlBinding("/page/welcome")
public class WelcomeBean extends BaseActionBean {

	private String message = "This is from the ActionBean!";
	
	@DefaultHandler
	public Resolution welcome() {
		return new ForwardResolution("/WEB-INF/jsp/welcome.jsp");
	}
	
	public String getMessage() {
		return message;
	}
}
