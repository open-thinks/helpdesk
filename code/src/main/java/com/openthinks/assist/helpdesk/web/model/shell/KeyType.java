package com.openthinks.assist.helpdesk.web.model.shell;

import com.openthinks.assist.helpdesk.util.StaticUtils;

public enum KeyType implements KeyGenerator{
	LEAF("L"),BRANCH("B");
	
	private String code;
	
	private KeyType(String code) {
		this.code=code;
	}
	
	public String getCode() {
		return code;
	}

	@Override
	public String gen() {
		return getCode()+"-"+StaticUtils.UUID();
	}
}
