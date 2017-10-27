/**
 * 
 */
package com.openthinks.assist.helpdesk.web.service.micro;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.openthinks.assist.helpdesk.util.StaticUtils;
import com.openthinks.assist.helpdesk.web.service.MicroService;
import com.openthinks.libs.sql.dao.ConnectionManager;
import com.openthinks.libs.sql.lang.Configurator;
import com.openthinks.libs.sql.lang.ConfiguratorFactory;
import com.openthinks.libs.utilities.logger.ProcessLogger;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class DatabaseInitiationService implements MicroService {

	@Override
	public void server() {
		Configurator cfg = ConfiguratorFactory.getDefaultInstance();
		try {
			Connection connection = ConnectionManager.getConnection(cfg);
			ProcessLogger.debug("Connected to existed DB.");
			ConnectionManager.closeConnection(cfg, connection);
		} catch (ClassNotFoundException e) {
			throw StaticUtils.pressException(e);
		} catch (SQLException e) {
			if("XJ004".equals(e.getSQLState())) {
				try {
					createDBThenInit();
				} catch (Exception e1) {
					throw StaticUtils.pressException(e);
				}
			}else {
				throw StaticUtils.pressException(e);
			}
		}
	}


	void createDBThenInit() throws Exception {
		Configurator cfg = ConfiguratorFactory.getDefaultInstance();
		String propCreate = cfg.getProperty("create", "false");
		cfg.setProperty("create", "true");
		Connection connection = ConnectionManager.getConnection(cfg);
		//create db success;
		//do DDL create table
		Statement stats = connection.createStatement();
		
		String[] ddls = {
			"CREATE TABLE ITEMS(\r\n" + 
			"ID BIGINT PRIMARY KEY,\r\n" + 
			"NAME VARCHAR(128),\r\n" + 
			"DESCRIPTION VARCHAR(512),\r\n" + 
			"TYPE SMALLINT,\r\n" + 
			"CONTENT CLOB,\r\n" + 
			"READ_COUNT SMALLINT,\r\n" + 
			"CREATE_TIME TIMESTAMP,\r\n" + 
			"UPDATE_TIME TIMESTAMP,\r\n" + 
			"PARENT BIGINT\r\n" + 
			")",
			"CREATE TABLE TAGS(\r\n" + 
			"ID BIGINT PRIMARY KEY,\r\n" + 
			"NAME VARCHAR(128),\r\n" + 
			"CREATE_TIME TIMESTAMP\r\n" + 
			")"
		};
		for(String sql:ddls) {
			stats.addBatch(sql);
		}
		int[] rsts = stats.executeBatch();
		for(int i=0;i<rsts.length;i++) {
			ProcessLogger.debug("Executed {0} DDL result:{1}",i,rsts[i]);
		}
		cfg.setProperty("create", propCreate);
	}

	
}
