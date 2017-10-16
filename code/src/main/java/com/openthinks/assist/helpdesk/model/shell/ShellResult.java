package com.openthinks.assist.helpdesk.model.shell;

import java.io.Serializable;
import java.util.Date;

import com.openthinks.assist.helpdesk.util.StaticUtils;

public class ShellResult implements Serializable {
	private static final long serialVersionUID = 6467967464818341836L;
	private Boolean success;
	private Date startTime;
	private Date endTime;
	protected StringBuffer logContent = new StringBuffer();

	public ShellResult() {
		super();
	}

	public ShellResult start() {
		setStartTime(new Date());
		return this;
	}

	public ShellResult end() {
		setEndTime(new Date());
		return this;
	}

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean isSuccess) {
		this.success = isSuccess;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date executeTime) {
		this.startTime = executeTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLogContent() {
		return logContent.toString();
	}

	protected void setLogContent(String logContent) {
		this.logContent = new StringBuffer(logContent);
	}

	public void track(String log) {
		if (log != null) {
			if (this.logContent == null) {
				setLogContent(log);
			} else {
				this.logContent.append("\r\n" + log);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.isSuccess() != null) {
			sb.append("Execution: ");
			sb.append(this.success ? "Success" : "Failed");
			sb.append("\r\n");
		}
		if (this.getStartTime() != null) {
			sb.append("Start Time: ");
			sb.append(StaticUtils.formatDate(getStartTime()));
			sb.append("\r\n");
		}
		if (this.getEndTime() != null) {
			sb.append("End Time: ");
			sb.append(StaticUtils.formatDate(getEndTime()));
			sb.append("\r\n");
		}
		if (this.getLogContent() != null && this.getLogContent().length() > 0) {
			sb.append("Log Track: ");
			sb.append(this.getLogContent());
			sb.append("\r\n");
		}
		return sb.toString();
	}

	public void clear() {
		this.logContent = null;
		this.success = null;
		this.startTime = null;
		this.endTime = null;
		this.logContent = new StringBuffer();
	}
}