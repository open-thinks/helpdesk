package com.openthinks.assist.helpdesk.service.impl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.openthinks.assist.helpdesk.TemplateProvider;
import com.openthinks.assist.helpdesk.service.TemplateMarkService;
import com.openthinks.libs.utilities.logger.ProcessLogger;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author minjdai
 *
 */
public class PageTemplateServiceImpl implements TemplateMarkService {
	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
	private Set<TemplateLocation> templateLocations = new CopyOnWriteArraySet<>();
	private MultiTemplateLoader multiTemplateLoader;
	private ReadWriteLock lock = new ReentrantReadWriteLock(true);

	public PageTemplateServiceImpl() {
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.CHINESE);
		cfg.setNumberFormat("0.######"); // now it will print 1000000
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		cfg.setLogTemplateExceptions(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.devhub.kaggle.service.thirdparty.TemplateMarkService#findTemplate(
	 * java.lang.String)
	 */
	@Override
	public Template findTemplate(String name) {
		lock.readLock().lock();
		try {
			return cfg.getTemplate(name);
		} catch (Exception e) {
			ProcessLogger.warn("Find template error:", e);
		} finally {
			lock.readLock().unlock();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.devhub.kaggle.service.thirdparty.TemplateMarkService#exec(java.lang.
	 * String, java.lang.Object)
	 */
	@Override
	public String exec(String name, Object model) {
		StringWriter out = new StringWriter();
		try {
			findTemplate(name).process(model, out);
			return out.getBuffer().toString();
		} catch (Exception e) {
			ProcessLogger.warn("Exec template error:", e);
		}
		return null;
	}

	@Override
	public void addTemplateSource(TemplateLocation templateLocation) {
		if (templateLocations.contains(templateLocation)) {
			return;
		}
		lock.writeLock().lock();
		try {
			this.templateLocations.add(templateLocation);
			this.multiTemplateLoader = new MultiTemplateLoader(reBuildTemplateLoader());
			this.cfg.setTemplateLoader(multiTemplateLoader);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void addTemplateSource(TemplateProvider provider) {
		List<TemplateLocation> locations = provider.getTemplateLations();

		if (locations == null || locations.size() == 0) {
			return;
		}
		List<TemplateLocation> unlocations = new ArrayList<>();
		for (TemplateLocation tl : locations) {
			if (!templateLocations.contains(tl)) {
				unlocations.add(tl);
			}
		}
		if (unlocations.isEmpty()) {
			return;
		}

		lock.writeLock().lock();
		try {
			this.templateLocations.addAll(unlocations);
			this.multiTemplateLoader = new MultiTemplateLoader(reBuildTemplateLoader());
			this.cfg.setTemplateLoader(multiTemplateLoader);
		} finally {
			lock.writeLock().unlock();
		}
	}

	private TemplateLoader[] reBuildTemplateLoader() {
		List<TemplateLoader> templateLoaders = new ArrayList<>();
		for (TemplateLocation templateLocation : templateLocations) {
			templateLoaders
					.add(new ClassTemplateLoader(templateLocation.getLoadClazz(), templateLocation.getBasePackage()));
		}
		return templateLoaders.toArray(new TemplateLoader[0]);
	}

}
