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
@Table(name = "GEO_LOCATION")
@NamedQueries({
		@NamedQuery(name = "GeoLocation.findByParent", query = "Select g.geoLocation from GeoLocationParentEO g "
				+ "where g.geoLocationParent = :parentLocation "
				+ "and g.geoLocation.locationType.id = :geoLocationType "
				+ " order by g.geoLocation.name"),

		@NamedQuery(name = "GeoLocation.findParent", query = "Select g.geoLocationParent from GeoLocationParentEO g "
				+ "where g.geoLocation = :geoLocation "
				+ "and g.geoLocationParent.locationType.id = :geoLocationType"),

		@NamedQuery(name = "GeoLocation.findByNameAndParent", query = "Select g.geoLocation from GeoLocationParentEO g "
				+ "where g.geoLocationParent = :parentLocation "
				+ "and g.geoLocation.locationType.id = :geoLocationType "
				+ "and upper(g.geoLocation.name) = upper(:name)"
				+ " order by g.geoLocation.name"),

		@NamedQuery(name = "GeoLocation.findByCodeAndParent", query = "Select g.geoLocation from GeoLocationParentEO g "
				+ "where g.geoLocationParent = :parentLocation "
				+ "and g.geoLocation.locationType.id = :geoLocationType "
				+ "and upper(g.geoLocation.code) = upper(:code)"
				+ " order by g.geoLocation.name"),

		@NamedQuery(name = "GeoLocation.findByName", query = "Select g from GeoLocationEO g "
				+ "where upper(g.name) = upper(:name) "
				+ "and g.locationType.id = :geoLocationType"),

		@NamedQuery(name = "GeoLocation.findByCode", query = "Select g from GeoLocationEO g "
				+ "where g.code = :code "
				+ "and g.locationType.id = :geoLocationType") })
public class GeoLocationEO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CODE")
	private String code;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GEO_LOCATION_TYPE_ID", nullable = false)
	private GeoLocationTypeEO locationType;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public GeoLocationTypeEO getLocationType() {
		return locationType;
	}

	public void setLocationType(GeoLocationTypeEO locationType) {
		this.locationType = locationType;
	}

}
