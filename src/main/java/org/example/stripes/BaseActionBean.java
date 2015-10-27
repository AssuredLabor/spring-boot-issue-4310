package org.example.stripes;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

@Slf4j @Getter @Setter
public abstract class BaseActionBean implements ActionBean {
	
	protected ActionBeanContext context;

}
