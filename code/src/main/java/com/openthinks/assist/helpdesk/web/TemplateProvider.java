package com.openthinks.assist.helpdesk.web;

import java.util.List;

import com.openthinks.assist.helpdesk.web.service.TemplateMarkService.TemplateLocation;

/**
 * ClassName: TemplateProvider <br/>
 * Function: TODO FUNCTION description of this class. <br/>
 * Reason: TODO why you add this class?(Optional). <br/>
 * date: Aug 21, 2017 2:02:28 PM <br/>
 * 
 * @author dailey.dai@cn.bosch.com DAD2SZH
 * @version
 * @since JDK 1.8
 */
public interface TemplateProvider {
  public List<TemplateLocation> getTemplateLations();
}
