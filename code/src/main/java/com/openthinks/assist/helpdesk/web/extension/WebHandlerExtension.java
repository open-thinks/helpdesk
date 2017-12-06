/**
 * 
 */
package com.openthinks.assist.helpdesk.web.extension;

import com.openthinks.assist.helpdesk.web.model.base.view.ViewModel;
import com.openthinks.easyweb.annotation.process.objects.WebMethodResponse;
import com.openthinks.easyweb.context.handler.WebHandlerFactory;

/**
 * WebHandler extension for Easyweb framework
 * @author dailey.yet@outlook.com
 *
 */
public class WebHandlerExtension implements Extension{

	public void active() {
		WebHandlerFactory.register(WebMethodResponse.build(ViewModel.class), new ViewModelMappingWebHandler());
	}
	
}
