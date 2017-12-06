/**
 * 
 */
package com.openthinks.assist.helpdesk.web.model.persistence;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dailey.yet@outlook.com
 *
 */
@Entity
@Table(name = "ITEMS")
public class Item {
	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "TYPE")
	private Integer type;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "READ_COUNT")
	private Integer readCount;
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;
	@Column(name = "PARENT")
	private Long parent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", content="
				+ content + ", readCount=" + readCount + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", parent=" + parent + "]";
	}

}
