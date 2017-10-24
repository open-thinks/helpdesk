/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
* @Title: StaticDict.java 
* @Package com.openthinks.home.nbctrl.util 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Sep 30, 2016
* @version V1.0   
*/
package com.openthinks.assist.helpdesk.util;

import java.io.File;

/**
 * @author dailey.yet@outlook.com
 *
 */
public interface StaticDict {

	int DEFAULT_PORT_NUM = 7799;
	String PORT_ARGUMENT_PREFIX = "-port:";
	String HELP_ARGUMENT = "--help";
	String DELIMITER = ",";
	String NEW_LINE = "\r\n";
	String DEFAULT_CONTEXT_PATH = "/";
	String CONTEXT_ARGUMENT_PREFIX = "-ctx:";
	String DB_ARGUMENT_PREFIX = "-db:";

	String PARAM_CMD_TYPE_CALL = "call";
	String PARAM_CMD_TYPE_PURE = "pure";
	String PARAM_CMD_ARG_PREFIX = "cmd_arg";
	String PARAM_CMD_ID = "cmd_id";
	String PARAM_CMD_TAG = "cmd_tag";
	String PARAM_CMD_DIR = "cmd_dir";
	String PARAM_CMD_SHELL = "cmd_shell";
	String PARAM_CMD_TYPE = "cmd_type";
	String PARAM_CMD_GROUP = "cmd_group";
	String COMMAND_ARGS_PATTERN = "cmd_arg[1-9]\\d*";

	String DEFAULT_DATE_FORMAT_STYLE = "yyyy-MM-dd HH:mm";
	String DEFAULT_DB_CFG = "dbconfig.properties";
	String DISK_STORE_CMD_GROUP = "cmd_grp_disk";
	String DISK_STORE_CMD_COMMAND = "cmd_disk";
	String DISK_STORE_RELATIONSHIP = "ship_disk";
	String DEFAULT_COMMAND_GROUP_NAME = "%&^*((*)()(%$#@OJ)_SD)A_";

	String DEFAULT_DB_CFG_FILE_PATH = new File(System.getProperty("user.dir"), StaticDict.DEFAULT_DB_CFG).getAbsolutePath();
	String PARAM_GRP_NAME = "grp_name";
	String PARAM_GRP_DESC = "grp_desc";
	String ATTRIBUTE_APP_PORT = "app_port";
	String ATTRIBUTE_APP_ROOT = "app_context";
	String ATTRIBUTE_APP_DB = "app_db";

}
