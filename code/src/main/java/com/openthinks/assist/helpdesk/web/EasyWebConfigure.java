/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * @Title: EasyWebConfigure.java
 * @Package com.openthinks.home.nbctrl
 * @Description: TODO
 * @author dailey.yet@outlook.com
 * @date Sep 29, 2016
 * @version V1.0
 */
package com.openthinks.assist.helpdesk.web;

import com.openthinks.assist.helpdesk.web.extension.WebHandlerExtension;
import com.openthinks.assist.helpdesk.web.service.micro.DatabaseInitiationService;
import com.openthinks.easyweb.annotation.configure.EasyConfigure;
import com.openthinks.easyweb.annotation.configure.RequestSuffixs;
import com.openthinks.easyweb.annotation.configure.ScanPackages;
import com.openthinks.easyweb.annotation.configure.ScanWay;
import com.openthinks.easyweb.annotation.configure.ScanWay.ScanWayEnum;
import com.openthinks.easyweb.context.Bootstrap;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.libs.sql.dao.ConnectionManager;
import com.openthinks.libs.utilities.logger.ProcessLogger;

/**
 * @author dailey.yet@outlook.com
 *
 */
@EasyConfigure
@ScanPackages({"com.openthinks.assist.helpdesk.web"})
@ScanWay(ScanWayEnum.ADVANCE)
@RequestSuffixs(".do,.htm")
public class EasyWebConfigure implements Bootstrap {

  @Override
  public void initial() {
    ProcessLogger.debug(getClass() + " initial...");
    WebContexts.get().lookupIf(WebHandlerExtension.class).ifPresent(ext->ext.active());
    WebContexts.get().lookupIf(DatabaseInitiationService.class).ifPresent(ser->ser.server());
  }

  @Override
  public void cleanUp() {
    ProcessLogger.debug(getClass() + " clean up...");
    //TODO clean other things
    ConnectionManager.destroyIfUsePool();
  }

}
