package com.openthinks.assist.helpdesk.web.controller;

import com.openthinks.assist.helpdesk.web.model.base.view.ViewModel;
import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.annotation.ResponseReturn.ResponseReturnType;

@Controller("/")
public class IndexController {

	@Mapping("/index")
	@ResponseReturn(contentType = ResponseReturnType.TEXT_HTML)
	public ViewModel index() {
		return new ViewModel("index.html");
	}
	
	@Mapping("/item/new")
	@ResponseReturn(contentType = ResponseReturnType.TEXT_HTML)
	public ViewModel item_new() {
		return new ViewModel("item_new.html");
	}
}
