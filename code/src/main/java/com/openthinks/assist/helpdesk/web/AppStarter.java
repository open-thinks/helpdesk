package com.openthinks.assist.helpdesk.web;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.openthinks.assist.helpdesk.util.StaticDict;
import com.openthinks.libs.sql.lang.Configurator;
import com.openthinks.libs.sql.lang.ConfiguratorFactory;
import com.openthinks.libs.utilities.logger.ProcessLogger;

/**
 * Hello world!
 *
 */
public final class AppStarter {
	public static final AppInfo APPINFO = new AppInfo();

	public static final class AppInfo {
		private String context = StaticDict.DEFAULT_CONTEXT_PATH;
		private int port = StaticDict.DEFAULT_PORT_NUM;
		private String dbCfgPath = StaticDict.DEFAULT_DB_CFG_FILE_PATH;

		private AppInfo() {
		}

		public String getContext() {
			return context;
		}

		public int getPort() {
			return port;
		}

		public String getDbCfgPath() {
			return dbCfgPath;
		}

		public File getDbCfgFile() {
			return new File(getDbCfgPath());
		}

		private void setContext(String context) {
			this.context = context;
		}

		private void setPort(int port) {
			this.port = port;
		}

		private void setDbCfgPath(String dbPath) {
			this.dbCfgPath = dbPath;
		}

	}

	public static void main(String[] args) throws Exception {
		ProcessLogger.debug("App start...");
		if (!checkArgs(args))
			return;
		int port = getProtArg(args);
		String rootPath = getRootContext(args);
		String dbCfgPath = getDBCfgPath(args);
		APPINFO.setPort(port);
		APPINFO.setContext(rootPath);
		APPINFO.setDbCfgPath(dbCfgPath);
		startServer(port, rootPath, dbCfgPath);
	}

	private static String getDBCfgPath(String[] args) {
		String sDbCfgPath = StaticDict.DEFAULT_DB_CFG_FILE_PATH;
		try {
			String argstr = String.join(StaticDict.DELIMITER, args);
			int start = argstr.indexOf(StaticDict.DB_ARGUMENT_PREFIX);
			int end = argstr.indexOf(StaticDict.DELIMITER, start);
			if (end == -1) {
				end = argstr.length();
			}
			sDbCfgPath = argstr.substring(start, end).replace(StaticDict.DB_ARGUMENT_PREFIX, "");
			return sDbCfgPath;
		} catch (Exception e) {
		}
		return sDbCfgPath;
	}

	protected static void startServer(int port, String rootPath, String dbCfgPath)
			throws Exception, InterruptedException {
		Server server = new Server(port);
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath(rootPath);
		if (dbCfgPath != null) {
			Configurator cfg = null;
			try {
				cfg = ConfiguratorFactory.getInstance(APPINFO.getDbCfgFile());
			} catch (Exception e) {
				cfg = ConfiguratorFactory.getDefaultInstance();
			}
			if(cfg!=null) {ProcessLogger.debug(cfg.toString());}
			else {
				ProcessLogger.warn("Could not find db config file.");
			}
		}
		/* Important: Use getResource */
		String webxmlLocation = AppStarter.class.getResource("/webapp/WEB-INF/web.xml").toString();
		webAppContext.setDescriptor(webxmlLocation);

		/* Important: Use getResource */
		String resLocation = AppStarter.class.getResource("/webapp").toString();
		webAppContext.setResourceBase(resLocation);

		webAppContext.setParentLoaderPriority(true);

		server.setHandler(webAppContext);
		server.start();
		server.join();
	}

	private static String getRootContext(String[] args) {
		String sContextPath = StaticDict.DEFAULT_CONTEXT_PATH;
		try {
			String argstr = String.join(StaticDict.DELIMITER, args);
			int start = argstr.indexOf(StaticDict.CONTEXT_ARGUMENT_PREFIX);
			int end = argstr.indexOf(StaticDict.DELIMITER, start);
			if (end == -1) {
				end = argstr.length();
			}
			sContextPath = argstr.substring(start, end).replace(StaticDict.CONTEXT_ARGUMENT_PREFIX, "");
		} catch (Exception e) {
			ProcessLogger.warn("Use default context path[" + StaticDict.DEFAULT_CONTEXT_PATH + "] for " + e);
		}
		return sContextPath;
	}

	private static int getProtArg(String[] args) {
		try {
			String argstr = String.join(StaticDict.DELIMITER, args);
			int start = argstr.indexOf(StaticDict.PORT_ARGUMENT_PREFIX);
			int end = argstr.indexOf(StaticDict.DELIMITER, start);
			if (end == -1) {
				end = argstr.length();
			}
			String sPort = argstr.substring(start, end).replace(StaticDict.PORT_ARGUMENT_PREFIX, "");
			return Integer.parseInt(sPort);
		} catch (Exception e) {
			ProcessLogger.warn("Use default port[" + StaticDict.DEFAULT_PORT_NUM + "] for " + e);
		}
		return StaticDict.DEFAULT_PORT_NUM;
	}

	private static boolean checkArgs(String[] args) throws InterruptedException {
		String argstr = String.join(StaticDict.DELIMITER, args);
		if (argstr.indexOf(StaticDict.HELP_ARGUMENT) != -1) {
			showHelp();
			return false;
		}
		return true;
	}

	private static void showHelp() {
		System.out.println("------------HelpDesk App Help------------");
		System.out.println("COMMAND:");
		System.out.println("java -jar nbctrl.jar [arg]...");
		System.out.println();
		System.out.println();
		System.out.println("USAGE:");
		System.out.println("1.Help");
		System.out.println("java -jar nbctrl.jar --help");
		System.out.println();
		System.out.println("2.Default with port[" + StaticDict.DEFAULT_PORT_NUM + "], and contxt path["
				+ StaticDict.DEFAULT_CONTEXT_PATH + "]");
		System.out.println("java -jar nbctrl.jar");
		System.out.println();
		System.out.println("3.Assign port and root path");
		System.out.println("java -jar nbctrl.jar -port:8080 -ctx:/");
		System.out.println();
		System.out.println("4.Assign port,root path and db config path");
		System.out.println("java -jar nbctrl.jar -port:8080 -ctx:/ -db:/dbcfgpath/dbconfig.properties");
	}
}
