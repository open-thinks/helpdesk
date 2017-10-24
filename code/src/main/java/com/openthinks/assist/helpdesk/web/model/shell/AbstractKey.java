package com.openthinks.assist.helpdesk.web.model.shell;

public abstract class AbstractKey implements Keyable {
	protected String id;
	protected AbstractKey parent;

	@Override
	public String id() {
		return id;
	}

	public AbstractKey setId(String id) {
		this.id = id;
		return this;
	}

	public AbstractKey autoAssignKey() {
		setId(keyType().gen());
		return this;
	}

	public AbstractKey getParent() {
		return parent;
	}

	public AbstractKey setParent(AbstractKey parent) {
		if (parent.keyType() == KeyType.BRANCH) {
			this.parent = parent;
		}
		return this;
	}

	public abstract KeyType keyType();

}
