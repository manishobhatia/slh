package com.mob.rs.server.services.feed;

import java.util.List;

import com.mob.rs.server.hibernate.model.GeoLocationEO;
import com.mob.rs.server.hibernate.model.SitesEO;
import com.mob.rs.server.model.feed.Location;
import com.mob.rs.server.model.feed.Sites;

public interface ILocationService {

	public List<GeoLocationEO> getGeoLocationsByParent(GeoLocationEO parent,
			Integer geoLocationType);

	public GeoLocationEO getGeoLocationByNameAndParent(String name,
			GeoLocationEO parent, Integer geoLocationType) throws Exception;

	public List<GeoLocationEO> getGeoLocationsByName(String name,
			Integer geoLocationType);

	public List<GeoLocationEO> getGeoLocationsByCode(String code,
			Integer geoLocationType);

	public GeoLocationEO getGeoLocationParent(GeoLocationEO geoLocation,
			Integer geoLocationType) throws Exception;

	public SitesEO getSiteByName(String name, Integer typeId) throws Exception;

	public GeoLocationEO getGeoLocationByCodeAndParent(String code,
			GeoLocationEO parent, Integer geoLocationType) throws Exception;

	public List<SitesEO> getSiteByGeo(GeoLocationEO geo);

	public List<Sites> getSites(Location location) throws Exception;
	
	public List<Location> getAllStates(Location country) throws Exception;
	
	public List<Sites> getSitesForLocation(Integer locationId);

}
