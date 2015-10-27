package org.example.action;

import org.example.stripes.BaseActionBean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@Slf4j @Getter @Setter @UrlBinding("/page/error")
public class ErrorBean extends BaseActionBean {
	
	@DefaultHandler
	public Resolution error() {
		return new ForwardResolution("/error.jsp");
	}
}
