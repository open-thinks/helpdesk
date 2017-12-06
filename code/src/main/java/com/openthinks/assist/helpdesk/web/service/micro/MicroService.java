/**
 * 
 */
package com.openthinks.assist.helpdesk.web.service.micro;

/**
 * @author dailey.yet@outlook.com
 *
 */
public interface MicroService {

	default String getServiceId() {
		return getClass().getName();
	}
	
	public void server();
}
