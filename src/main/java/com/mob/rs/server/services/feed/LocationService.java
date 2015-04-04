package com.mob.rs.server.services.feed;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mob.rs.server.hibernate.model.GeoLocationEO;
import com.mob.rs.server.hibernate.model.SitesEO;
import com.mob.rs.server.model.feed.Location;
import com.mob.rs.server.model.feed.Sites;

@Component("locationService")
@Transactional(readOnly = true)
public class LocationService implements ILocationService {

	public static final int GEO_TYPE_COUNTRY = 1;
	public static final int GEO_TYPE_STATE = 2;
	public static final int GEO_TYPE_CITY = 3;
	public static final int GEO_TYPE_COUNTY = 4;
	public static final int GEO_TYPE_METRO = 5;

	public static final int CLASSIFIED_TYPE_CRAIGS = 1;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<GeoLocationEO> getGeoLocationsByParent(GeoLocationEO parent,
			Integer geoLocationType) {

		TypedQuery<GeoLocationEO> query = em.createNamedQuery(
				"GeoLocation.findByParent", GeoLocationEO.class);
		query.setParameter("parentLocation", parent);
		query.setParameter("geoLocationType", geoLocationType);
		return query.getResultList();
	}

	@Override
	public GeoLocationEO getGeoLocationByNameAndParent(String name,
			GeoLocationEO parent, Integer geoLocationType) throws Exception {

		TypedQuery<GeoLocationEO> query = em.createNamedQuery(
				"GeoLocation.findByNameAndParent", GeoLocationEO.class);
		query.setParameter("parentLocation", parent);
		query.setParameter("geoLocationType", geoLocationType);
		query.setParameter("name", name);
		List<GeoLocationEO> result = query.getResultList();
		if (result == null || result.isEmpty()) {
			return null;
		}
		if (result.size() > 1) {
			throw new Exception("Multiple results found for the same name");
		}
		return result.get(0);
	}

	@Override
	public GeoLocationEO getGeoLocationByCodeAndParent(String code,
			GeoLocationEO parent, Integer geoLocationType) throws Exception {

		TypedQuery<GeoLocationEO> query = em.createNamedQuery(
				"GeoLocation.findByCodeAndParent", GeoLocationEO.class);
		query.setParameter("parentLocation", parent);
		query.setParameter("geoLocationType", geoLocationType);
		query.setParameter("code", code);
		List<GeoLocationEO> result = query.getResultList();
		if (result == null || result.isEmpty()) {
			return null;
		}
		if (result.size() > 1) {
			throw new Exception("Multiple results found for the same code");
		}
		return result.get(0);
	}

	@Override
	public List<GeoLocationEO> getGeoLocationsByName(String name,
			Integer geoLocationType) {
		TypedQuery<GeoLocationEO> query = em.createNamedQuery(
				"GeoLocation.findByName", GeoLocationEO.class);
		query.setParameter("geoLocationType", geoLocationType);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public GeoLocationEO getGeoLocationParent(GeoLocationEO geoLocation,
			Integer geoLocationType) throws Exception {

		TypedQuery<GeoLocationEO> query = em.createNamedQuery(
				"GeoLocation.findParent", GeoLocationEO.class);
		query.setParameter("geoLocation", geoLocation);
		query.setParameter("geoLocationType", geoLocationType);
		List<GeoLocationEO> result = query.getResultList();
		if (result == null || result.isEmpty()) {
			return null;
		}
		if (result.size() > 1) {
			throw new Exception(
					"Multiple results found for the same geoLocation : "
							+ geoLocation.getName());
		}
		return result.get(0);
	}

	@Override
	public List<GeoLocationEO> getGeoLocationsByCode(String code,
			Integer geoLocationType) {
		TypedQuery<GeoLocationEO> query = em.createNamedQuery(
				"GeoLocation.findByCode", GeoLocationEO.class);
		query.setParameter("geoLocationType", geoLocationType);
		query.setParameter("code", code);
		return query.getResultList();
	}

	@Override
	public SitesEO getSiteByName(String name, Integer typeId) throws Exception {
		TypedQuery<SitesEO> query = em.createNamedQuery("Sites.findByName",
				SitesEO.class);
		query.setParameter("name", name);
		query.setParameter("typeId", typeId);
		List<SitesEO> result = query.getResultList();
		if (result == null || result.isEmpty()) {
			return null;
		}
		if (result.size() > 1) {
			throw new Exception("Multiple results found for the same name : "
					+ name);
		}
		return result.get(0);
	}

	@Override
	public List<SitesEO> getSiteByGeo(GeoLocationEO geo) {
		TypedQuery<SitesEO> query = em.createNamedQuery("Sites.findByGeo",
				SitesEO.class);
		query.setParameter("geoLocation", geo);
		query.setParameter("typeId", CLASSIFIED_TYPE_CRAIGS);
		return query.getResultList();
	}

	@Override
	public List<Sites> getSites(Location location) throws Exception {

		List<Sites> sites = new ArrayList<Sites>();

		GeoLocationEO countryGeo = getCountry(location);

		GeoLocationEO stateGeo = getState(location, countryGeo);

		// Match with City
		GeoLocationEO cityGeo = getGeoLocationByNameAndParent(
				location.getCity(), stateGeo, GEO_TYPE_CITY);
		List<SitesEO> sitesEO = getSiteByGeo(cityGeo);

		// Match with County
		GeoLocationEO countyGeo = null;
		if (sitesEO != null && sitesEO.isEmpty()
				&& location.getCounty() != null) {
			countyGeo = getGeoLocationByNameAndParent(location.getCounty(),
					stateGeo, GEO_TYPE_COUNTY);
			sitesEO = getSiteByGeo(countyGeo);
		}

		// Match with Metro
		if (sitesEO != null && sitesEO.isEmpty() && countryGeo != null) {
			GeoLocationEO metroGeo = getGeoLocationParent(countyGeo,
					GEO_TYPE_METRO);
			sitesEO = getSiteByGeo(metroGeo);
		}

		// Match with State
		if (sitesEO != null && sitesEO.isEmpty() && stateGeo != null) {
			sitesEO = getSiteByGeo(stateGeo);
		}

		// Match with Country
		if (sitesEO != null && sitesEO.isEmpty() && countryGeo != null) {
			sitesEO = getSiteByGeo(countryGeo);
		}

		return copyFromSitesEO(sitesEO);
	}

	@Override
	public List<Location> getAllStates(Location country) throws Exception {
		GeoLocationEO countryGeo = getCountry(country);
		List<GeoLocationEO> states = getGeoLocationsByParent(countryGeo,
				GEO_TYPE_STATE);
		List<Location> result = new ArrayList<Location>();
		for (GeoLocationEO state : states) {
			Location location = new Location();
			location.setGeoLocationId(state.getId());
			location.setState(state.getName());
			location.setStateCode(state.getCode());
			location.setCountry(country.getCounty());
			location.setCountryCode(country.getCountryCode());

			result.add(location);
		}
		return result;
	}
	
	@Override
	public List<Sites> getSitesForLocation(Integer locationId) {
		GeoLocationEO location = em.find(GeoLocationEO.class, locationId);
		return copyFromSitesEO(getSiteByGeo(location));
	}

	private List<Sites> copyFromSitesEO(List<SitesEO> sitesEO) {
		List<Sites> sites = null;
		if (sitesEO != null) {
			sites = new ArrayList<Sites>();
			for (SitesEO seo : sitesEO) {
				Sites site = new Sites();
				site.setId(seo.getId());
				site.setName(seo.getName());
				site.setUrl(seo.getUrl());

				sites.add(site);
			}
		}
		return sites;
	}

	public GeoLocationEO getCountry(Location location) throws Exception {
		List<GeoLocationEO> geoLocations = null;
		if (!StringUtils.isEmpty(location.getCountryCode())) {
			geoLocations = getGeoLocationsByCode(location.getCountryCode(),
					GEO_TYPE_COUNTRY);
		} else if (!StringUtils.isEmpty(location.getCountry())) {
			geoLocations = getGeoLocationsByName(location.getCountry(),
					GEO_TYPE_COUNTRY);
		}

		if (geoLocations.size() > 1) {
			throw new Exception("Multiple results found : " + location);
		}

		if (geoLocations != null)
			return geoLocations.iterator().next();

		return null;
	}

	public GeoLocationEO getState(Location location, GeoLocationEO countryGeo)
			throws Exception {
		List<GeoLocationEO> geoLocations = null;
		if (!StringUtils.isEmpty(location.getStateCode())) {
			return getGeoLocationByCodeAndParent(location.getStateCode(),
					countryGeo, GEO_TYPE_STATE);
		} else if (!StringUtils.isEmpty(location.getState())) {
			return getGeoLocationByNameAndParent(location.getState(),
					countryGeo, GEO_TYPE_STATE);
		}

		return null;
	}

}
