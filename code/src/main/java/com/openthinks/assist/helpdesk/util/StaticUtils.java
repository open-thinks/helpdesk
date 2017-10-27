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
* @Title: StaticUtils.java 
* @Package com.openthinks.webscheduler.help 
* @Description: TODO
* @author dailey.yet@outlook.com  
* @date Jul 21, 2016
* @version V1.0   
*/
package com.openthinks.assist.helpdesk.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.openthinks.libs.utilities.logger.ProcessLogger;

/**
 * @author dailey.yet@outlook.com
 *
 */
public final class StaticUtils {
	private static Lock lock = new ReentrantLock();
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(StaticDict.DEFAULT_DATE_FORMAT_STYLE);
	private static final Map<String, DateFormat> cache = new ConcurrentHashMap<String, DateFormat>() {
		private static final long serialVersionUID = -2718845512195138169L;
		{
			this.put(StaticDict.DEFAULT_DATE_FORMAT_STYLE, DATE_FORMAT);
		}
	};

	public static String formatDate(Date date) {
		lock.lock();
		try {
			return DATE_FORMAT.format(date);
		}finally {
			lock.unlock();
		}
	}

	public static String formatNow() {
		return formatDate(new Date());
	}

	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, StaticDict.DEFAULT_DATE_FORMAT_STYLE);
	}

	public static Date parseDate(String dateStr, String formatStly) {
		DateFormat df = cache.get(formatStly);
		if (df == null) {
			try {
				df = new SimpleDateFormat(formatStly);
				cache.put(formatStly, df);
			} catch (Exception e) {
				ProcessLogger.error(e);
				return null;
			}
		}
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			ProcessLogger.warn(e);
		}
		return null;
	}

	public static String UUID() {
		return java.util.UUID.randomUUID().toString();
	}
	
	public static RuntimeException pressException(Exception e) {
		return new RuntimeException(e);
	}
}
