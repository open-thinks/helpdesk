package com.openthinks.assist.helpdesk.db;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import com.openthinks.assist.helpdesk.web.model.persistence.Items;
import com.openthinks.libs.sql.dao.ConnectionManager;
import com.openthinks.libs.sql.dhibernate.Session;
import com.openthinks.libs.sql.dhibernate.support.SessionFactory;
import com.openthinks.libs.sql.lang.Configurator;
import com.openthinks.libs.sql.lang.ConfiguratorFactory;

public class DerbySqllibTest {
	
	public static void testConnect() {
		Configurator configurator = ConfiguratorFactory.getDefaultInstance();
		System.out.println(configurator);
		try {
			Connection connection = ConnectionManager.getConnection(configurator);
			System.out.println("Get connection:"+connection);
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testDelete() {
		System.out.println("delete item");
		Session session =  SessionFactory.getSession();
		Items item = new Items();
		item.setId(12345678L);
		session.delete(item);
		session.close();
	}
	
	public static void testSave() {
		System.out.println("save item");
		Session session =  SessionFactory.getSession();
		Items item = new Items();
		item.setId(12345678L);
		item.setName("Test");
		item.setContent("abcdefjdddddddddddddddddddddddd中文测试\r\nddddddddddddddddddddddddddddd");
		item.setCreateTime(Timestamp.from(Instant.now()));
		session.save(item);
		session.close();
	}

	
	public static void testList() {
		Session session =  SessionFactory.getSession();
		List<Items> items =session.list(Items.class);
		System.out.println("print all items:"+items.size());
		items.stream().forEach(item->{System.out.println(item);
		});
		session.close();
	}
	
	public static void testLoad() {
		Session session =  SessionFactory.getSession();
		Items item = session.load(Items.class, 12345678L);
		assert item != null;
		System.out.println(item);
		Timestamp tm = item.getCreateTime();
		assert tm != null;
		System.out.println(tm);
		session.close();
	}

	
	public static void main(String[] args) {
		DerbySqllibTest.testConnect();
		DerbySqllibTest.testList();
		DerbySqllibTest.testDelete();
		DerbySqllibTest.testList();
		DerbySqllibTest.testSave();
		DerbySqllibTest.testLoad();
		DerbySqllibTest.testList();
	}
}
