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
@Table(name = "GEO_LOCATION_PARENT")
public class GeoLocationParentEO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GEO_LOCATION_ID", nullable=false)
	private GeoLocationEO geoLocation;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GEO_LOCATION_PARENT_ID", nullable=false)
	private GeoLocationEO geoLocationParent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GeoLocationEO getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocationEO geoLocation) {
		this.geoLocation = geoLocation;
	}

	public GeoLocationEO getGeoLocationParent() {
		return geoLocationParent;
	}

	public void setGetLocationParent(GeoLocationEO geoLocationParent) {
		this.geoLocationParent = geoLocationParent;
	}
}
