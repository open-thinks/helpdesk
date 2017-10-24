package com.openthinks.assist.helpdesk.web.extension;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openthinks.assist.helpdesk.web.TemplateProvider;
import com.openthinks.assist.helpdesk.web.model.base.view.ViewModel;
import com.openthinks.assist.helpdesk.web.service.TemplateMarkService;
import com.openthinks.assist.helpdesk.web.service.TemplateMarkService.TemplateLocation;
import com.openthinks.assist.helpdesk.web.service.impl.PageTemplateServiceImpl;
import com.openthinks.easyweb.WebUtils;
import com.openthinks.easyweb.annotation.process.objects.WebMethod;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebHandler;
import com.openthinks.libs.utilities.CommonUtilities;
import com.openthinks.libs.utilities.logger.ProcessLogger;

class ViewModelMappingWebHandler implements WebHandler,TemplateProvider {
		private final TemplateMarkService templateMarkService;
		
		ViewModelMappingWebHandler() {
			templateMarkService = WebContexts.get().lookup(PageTemplateServiceImpl.class);
			templateMarkService.addTemplateSource(this);
		}
		
		@Override
		public void handle(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException {
			WebMethod webMethod = WebUtils.getControllerWebMethod(arg0);
			process(arg0, arg1, null, webMethod);
		}

		@Override
		public void handle(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
				throws IOException, ServletException {
			WebMethod webMethod = WebUtils.getFilterWebMethod(arg0);
			process(arg0, arg1, arg2, webMethod);
		}

		protected void process(ServletRequest req, ServletResponse resp, FilterChain filterChain, WebMethod webMethod) {
			try {
				ViewModel responseValue = (ViewModel) webMethod.invoke((HttpServletRequest) req,
						(HttpServletResponse) resp, filterChain);
				PrintWriter writer = resp.getWriter();
				String viewContent = templateMarkService.exec(responseValue.getViewName(), responseValue);
				writer.print(viewContent);
				writer.flush();
				return;
			} catch (Exception e) {
				ProcessLogger.error(CommonUtilities.getCurrentInvokerMethod(), e);
			}
		}

		@Override
		public List<TemplateLocation> getTemplateLations() {
			return TemplateLocation.valueOfSingleList(getClass(), "/webapp/WEB-INF/page");
		}

	}