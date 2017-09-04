package com.openthinks.assist.helpdesk.model.base.view;

import java.util.HashMap;

public class ViewModel extends HashMap<String, Object> {

	private static final long serialVersionUID = -7798620086545912283L;
	private String viewName;

	public ViewModel(String viewName) {
		super();
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

}
