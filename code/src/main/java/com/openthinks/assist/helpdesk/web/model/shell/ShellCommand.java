package com.openthinks.assist.helpdesk.web.model.shell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ShellCommand extends AbstractKey implements Serializable {
	private static final long serialVersionUID = -7659471229009632739L;
	private String command;
	private String dir;
	private String[] args;
	
	public String getCommand() {
		return command;
	}

	public List<String> getCommandList() {
		List<String> cmmdlist = new ArrayList<>();
		cmmdlist.add(command);
		if (this.args != null && this.args.length > 0) {
			for (String arg : this.args) {
				cmmdlist.add(arg);
			}
		}
		return cmmdlist;
	}

	public ShellCommand setCommand(String command) {
		this.command = command;
		return this;
	}

	public String getDir() {
		return dir;
	}

	public ShellCommand setDir(String dir) {
		this.dir = dir;
		return this;
	}

	public String[] getArgs() {
		return args;
	}

	public ShellCommand setArgs(String[] args) {
		this.args = args;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShellCommand other = (ShellCommand) obj;
		if (!Arrays.equals(args, other.args))
			return false;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		if (dir == null) {
			if (other.dir != null)
				return false;
		} else if (!dir.equals(other.dir))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShellCommand [id="+id+"command=" + command + ", dir=" + dir + ", args=" + Arrays.toString(args) + "]";
	}

	@Override
	public KeyType keyType() {
		return KeyType.LEAF;
	}

}