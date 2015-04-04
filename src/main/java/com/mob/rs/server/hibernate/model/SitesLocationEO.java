package com.mob.rs.server.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SITES_LOCATION")
public class SitesLocationEO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SITES_ID", nullable=false)
	private SitesEO sitesId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GEO_LOCATION_ID", nullable=false)
	private GeoLocationEO geoLocationId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SitesEO getSitesId() {
		return sitesId;
	}

	public void setSitesId(SitesEO sitesId) {
		this.sitesId = sitesId;
	}

	public GeoLocationEO getGeoLocationId() {
		return geoLocationId;
	}

	public void setGeoLocationId(GeoLocationEO geoLocationId) {
		this.geoLocationId = geoLocationId;
	}

}
