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
@Table(name = "SITES")
@NamedQueries({
		@NamedQuery(name = "Sites.findByName", query = "Select s from SitesEO s "
				+ "where s.name = :name and classifiedType.id = :typeId"),

		@NamedQuery(name = "Sites.findByGeo", query = "Select s.sitesId from SitesLocationEO s "
				+ "where s.geoLocationId = :geoLocation "
				+ "and s.sitesId.classifiedType.id = :typeId")

})
public class SitesEO implements Serializable {

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASSIFIED_TYPE_ID", nullable = false)
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

	public ClassifiedTypeEO getClassifiedType() {
		return classifiedType;
	}

	public void setClassifiedType(ClassifiedTypeEO classifiedType) {
		this.classifiedType = classifiedType;
	}

}
