package com.openthinks.assist.helpdesk.controller;

import com.openthinks.assist.helpdesk.model.base.view.ViewModel;
import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.annotation.ResponseReturn.ResponseReturnType;

@Controller("/")
public class IndexController{

  @Mapping("/index")
  @ResponseReturn(contentType = ResponseReturnType.TEXT_HTML)
  public String index() {
    return "<h2>This is NB Controller home page.</h2>";
  }

  @Mapping("/test")
  public ViewModel test() {
    return new ViewModel("test.html");
  }


}
