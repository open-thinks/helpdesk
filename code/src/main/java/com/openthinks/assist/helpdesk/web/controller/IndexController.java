package com.openthinks.assist.helpdesk.web.controller;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;

import com.openthinks.assist.helpdesk.web.model.base.view.ViewModel;
import com.openthinks.assist.helpdesk.web.model.persistence.Item;
import com.openthinks.assist.helpdesk.web.service.data.ItemService;
import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.annotation.ResponseReturn.ResponseReturnType;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;

@Controller("/")
public class IndexController {

	@Mapping("/index")
	@ResponseReturn(contentType = ResponseReturnType.TEXT_HTML)
	public ViewModel index() {
		return new ViewModel("index.html");
	}

	@Mapping("/item/new")
	@ResponseReturn(contentType = ResponseReturnType.TEXT_HTML)
	public ViewModel item_new() {
		return new ViewModel("item_new.html");
	}
	
	@Mapping("/test/del")
	@ResponseReturn(contentType = ResponseReturnType.TEXT_PLAN)
	public String testdel(WebAttributers was) {
		StringBuffer sb = new StringBuffer();
		WebContexts.get().lookupIf(ItemService.class).ifPresent((service) -> {
			
		});
		return sb.toString();
	}
	
	@Mapping("/test")
	@ResponseReturn(contentType = ResponseReturnType.TEXT_PLAN)
	public String test(WebAttributers was) {
		StringBuffer sb = new StringBuffer();
		WebContexts.get().lookupIf(ItemService.class).ifPresent((service) -> {
			List<Item> items = service.listAll();
			if (items.isEmpty() || items.size()<100) {
				createTestData(service);
			}
			items = service.listAll();
			Iterator<Item> itr = items.iterator();
			while (itr.hasNext()) {
				Item item = itr.next();
				sb.append(item.toString()+"\n");
			}
		});
		return sb.toString();
	}

	private void createTestData(ItemService service) {
		Item[] items = new Item[100];
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item();
			Item tmp = items[i];
			tmp.setId(Long.valueOf(i));
			tmp.setName("test " + (i % 5 == 0 ? "category" : "item") + " - " + (i+1));
			tmp.setType(0);
			tmp.setParent((i % 5 == 0?-1L:(i/5)*5 ));
			tmp.setCreateTime(new Timestamp(Clock.systemUTC().millis()));
			tmp.setContent("Test");
			service.create(tmp);
		}

	}
	
}
