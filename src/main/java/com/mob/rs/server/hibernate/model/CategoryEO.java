package com.mob.rs.server.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
@NamedQuery(name="Category.findByParendId", query="Select c from CategoryEO c where c.parent = :parent"),
@NamedQuery(name="Category.findByNullParendId", query="Select c from CategoryEO c where c.parent is null")
})
@Table(name = "CATEGORY")
public class CategoryEO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "URL")
	private String url;
	
	@Column(name="MAX_PRICE")
	private Integer maxPrice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_ID", nullable=true)
	private CategoryEO parent;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLASSIFIED_TYPE_ID", nullable=false)
	private ClassifiedTypeEO classifiedType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public CategoryEO getParent() {
		return parent;
	}

	public void setParent(CategoryEO parent) {
		this.parent = parent;
	}

	public ClassifiedTypeEO getClassifiedType() {
		return classifiedType;
	}

	public void setClassifiedType(ClassifiedTypeEO classifiedType) {
		this.classifiedType = classifiedType;
	}
}
