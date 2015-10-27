package org.example.action;

import org.example.stripes.BaseActionBean;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.stripes.action.*;

@Slf4j
@UrlBinding("/page/welcome")
public class WelcomeBean extends BaseActionBean {

	@DefaultHandler
	public Resolution welcome() {
		return new ForwardResolution("/welcome.jsp");
	}
}
