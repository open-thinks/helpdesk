package com.openthinks.assist.helpdesk.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.openthinks.assist.helpdesk.TemplateProvider;

import freemarker.template.Template;

public interface TemplateMarkService {

  /**
   * 查找指定名称的模板
   * 
   * @param name 模板名称
   * @return {@link Template}
   */
  public Template findTemplate(String name);

  /**
   * 合并模板和数据模型
   * 
   * @param name 模板名称
   * @param model 数据
   * @return 最终的内容
   */
  public String exec(String name, Object model);



  /**
   * 添加新的模板路径
   * 
   * @param pluginEntryClazz
   * @param basePackage
   */
  public void addTemplateSource(TemplateLocation templateLocation);


  public void addTemplateSource(TemplateProvider provider);

  /**
   * 视图模板地址类
   * 
   * @author minjdai
   *
   */
  public final class TemplateLocation implements Serializable {
    private static final long serialVersionUID = 509217625128602593L;
    private Class<? extends TemplateProvider> loadClazz;
    private String basePackage;

    public TemplateLocation() {}

    public TemplateLocation(Class<? extends TemplateProvider> loadClazz, String basePackage) {
      super();
      this.loadClazz = loadClazz;
      this.basePackage = basePackage;
    }

    public Class<?> getLoadClazz() {
      return loadClazz;
    }

    public TemplateLocation setLoadClazz(Class<? extends TemplateProvider> loadClazz) {
      this.loadClazz = loadClazz;
      return this;
    }

    public String getBasePackage() {
      return basePackage;
    }

    public TemplateLocation setBasePackage(String basePackage) {
      this.basePackage = basePackage;
      return this;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((basePackage == null) ? 0 : basePackage.hashCode());
      result = prime * result + ((loadClazz == null) ? 0 : loadClazz.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      TemplateLocation other = (TemplateLocation) obj;
      if (basePackage == null) {
        if (other.basePackage != null)
          return false;
      } else if (!basePackage.equals(other.basePackage))
        return false;
      if (loadClazz == null) {
        if (other.loadClazz != null)
          return false;
      } else if (!loadClazz.equals(other.loadClazz))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "TemplateLocation [pluginUIClazz=" + loadClazz + ", basePackage=" + basePackage + "]";
    }

    public static final List<TemplateLocation> valueOfList(TemplateLocation... locations) {
      if (locations != null) {
        return Arrays.asList(locations);
      }
      return Collections.emptyList();
    }

    public static final List<TemplateLocation> valueOfSingleList(
        Class<? extends TemplateProvider> pluginUIClazz, String basePackage) {
      return Collections.singletonList(valueOf(pluginUIClazz, basePackage));
    }

    public static final TemplateLocation valueOf(Class<? extends TemplateProvider> pluginUIClazz,
        String basePackage) {
      return new TemplateLocation(pluginUIClazz, basePackage);
    }

  }

}
