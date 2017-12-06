/**
 * 
 */
package com.openthinks.assist.helpdesk.web.service.data;

import java.util.List;

import com.openthinks.assist.helpdesk.web.model.persistence.Item;
import com.openthinks.libs.sql.dhibernate.Session;
import com.openthinks.libs.sql.dhibernate.support.SessionFactory;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class ItemService {

	public void create(Item item) {
		Session session = SessionFactory.getSession();
		session.save(item);
		session.close();
	}
	
	public void update(Item item) {
		Session session = SessionFactory.getSession();
		session.update(item);
		session.close();
	}
	
	public List<Item> listAll(){
		Session session = SessionFactory.getSession();
		return session.list(Item.class);
	}
	
	public void deleteAll() {
		Session session = SessionFactory.getSession();
		session.deleteAll(Item.class);
		session.close();
	}
	
}
