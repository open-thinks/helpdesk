package com.openthinks.assist.helpdesk.web.model.shell;

import java.io.Serializable;

public class Category extends AbstractKey implements Serializable {
	private static final long serialVersionUID = -8076099533799657352L;
	private String name;
	private String display;
	private String description;

	public String getName() {
		return name;
	}

	public Category setName(String name) {
		this.name = name;
		return this;
	}

	public String getDisplay() {
		return display;
	}

	public Category setDisplay(String display) {
		this.display = display;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Category setDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public KeyType keyType() {
		return KeyType.BRANCH;
	}

}
