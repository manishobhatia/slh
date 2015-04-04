package com.mob.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mob.rs.server.hibernate.model.GeoLocationEO;
import com.mob.rs.server.hibernate.model.SitesEO;
import com.mob.rs.server.services.feed.ILocationService;
import com.mob.rs.server.services.feed.LocationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "rsserver-app-context.xml" })
public class CraigsListGeoMapping {

	@Autowired
	private ILocationService locationService;

	private static int siteLocationCount = 0;

	@Test
	public void createMappings() {
		try {
			GeoLocationEO USGeo = locationService.getGeoLocationsByCode("US",
					LocationService.GEO_TYPE_COUNTRY).get(0);

			Map<String, List<String>> stateByCitiesMap = CraigsListCities
					.printSql(false);

			for (String state : stateByCitiesMap.keySet()) {

				GeoLocationEO stateGeo = locationService
						.getGeoLocationByNameAndParent(state, USGeo,
								LocationService.GEO_TYPE_STATE);
				if (stateGeo != null) {

					for (String site : stateByCitiesMap.get(state)) {
						String[] siteArr = site.split(", ");
						Integer siteId = Integer.parseInt(siteArr[1].trim());
						String siteName = siteArr[0].trim();
						printSql(siteId, stateGeo.getId());

						boolean matchFound = false;

						// City Mapping
						for (Integer cityId : getCityIds(siteName, stateGeo)) {
							printSql(siteId, cityId);
							matchFound = true;
						}

						// County Mapping
						for (Integer countyId : getCountyIds(siteName, stateGeo)) {
							printSql(siteId, countyId);
							matchFound = true;
						}

						// Manual Match
						ManualMatchSite mms = ManualMatchSite
								.getManualMatchSite(siteName);
						if (mms != null) {
							for (Integer id : getLocationIdForManualMatch(mms,
									stateGeo)) {
								printSql(siteId, id);
								matchFound = true;
							}
						}

						// Metro Mapping
						if (mms == null || mms.isMetroMatch()) {
							for (Integer metroId : getMetroId(siteName,
									stateGeo)) {
								printSql(siteId, metroId);
								matchFound = true;
							}
						}

						if (!matchFound) {
							System.out.println("--No Mapping for Site : "
									+ siteName);

						}
					}
				} else {
					// System.out.println("Not Found : " + state);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<Integer> getMetroId(String siteName, GeoLocationEO stateGeo)
			throws Exception {
		List<Integer> metroIds = new ArrayList<Integer>();
		List<GeoLocationEO> metroGeos = locationService
				.getGeoLocationsByParent(stateGeo,
						LocationService.GEO_TYPE_METRO);

		for (GeoLocationEO metroGeo : metroGeos) {
			String metroName = metroGeo.getName();
			if (metroName.toUpperCase().contains(siteName.toUpperCase())) {
				// System.out.println("Metro Match " + metroName
				// + " to SiteName : " + siteName);
				metroIds.add(metroGeo.getId());
				continue;
			}

			if (matchMetroName(siteName, metroName, "/")) {
				// System.out.println("Metro Match " + metroName
				// + " to SiteName : " + siteName);
				metroIds.add(metroGeo.getId());
				continue;
			}

			if (matchMetroName(siteName, metroName, "-")) {
				// System.out.println("Metro Match " + metroName
				// + " to SiteName : " + siteName);
				metroIds.add(metroGeo.getId());
				continue;
			}

		}
		return metroIds;
	}

	private boolean matchMetroName(String siteName, String metroName,
			String splitter) {
		String split[] = siteName.split(splitter);
		for (int i = 0; i < split.length; i++) {
			if (metroName.toUpperCase().contains(split[i].trim().toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	private void printSql(int siteId, int geoId) {
		System.out.println("(" + ++siteLocationCount + ", " + siteId + ", "
				+ geoId + "),");
	}

	private List<Integer> getCountyIds(String siteName, GeoLocationEO stateGeo)
			throws Exception {
		List<Integer> countyIdList = new ArrayList<Integer>();
		String countyName = siteName;
		if (siteName.contains("county")) {
			countyName = siteName.replaceAll("county", "").trim();
		}

		Integer id = getLocationId(countyName, stateGeo,
				LocationService.GEO_TYPE_COUNTY);
		if (id != null) {
			countyIdList.add(id);
		}

		// try with /
		if (countyIdList.isEmpty() && countyName.contains("/")) {

			countyIdList = getLocationIdBySplit(countyName, stateGeo, "/",
					LocationService.GEO_TYPE_COUNTY);
		}

		// try with -
		if (countyIdList.isEmpty() && countyName.contains("-")) {

			countyIdList = getLocationIdBySplit(countyName, stateGeo, "-",
					LocationService.GEO_TYPE_COUNTY);
		}

		if (countyIdList.isEmpty()) {
			// System.out.println("--Could not map City : " + clCityName);
		}

		return countyIdList;

	}

	private Integer getLocationId(String countyName, GeoLocationEO stateGeo,
			int geoLocationType) throws Exception {
		// System.out.println(countyName);
		GeoLocationEO locationGeo = locationService
				.getGeoLocationByNameAndParent(countyName, stateGeo,
						geoLocationType);
		if (locationGeo != null)
			return locationGeo.getId();
		else
			return null;
	}

	private List<Integer> getLocationIdBySplit(String locationName,
			GeoLocationEO stateGeo, String splitter, int geoLocationType)
			throws Exception {
		List<Integer> locationIdList = new ArrayList<Integer>();

		String location[] = locationName.split(splitter);
		for (int i = 0; i < location.length; i++) {
			Integer locationId = getLocationId(location[i].trim(), stateGeo,
					geoLocationType);
			if (locationId != null) {
				locationIdList.add(locationId);
			}
		}
		if (!locationIdList.isEmpty()
				&& location.length != locationIdList.size()) {
			// System.out.println("--One of the locations not found for : "
			// + locationName);
		}

		return locationIdList;
	}

	private List<Integer> getCityIds(String clCityName, GeoLocationEO stateGeo)
			throws Exception {
		List<Integer> cityIdList = new ArrayList<Integer>();

		Integer id = getLocationId(clCityName, stateGeo,
				LocationService.GEO_TYPE_CITY);
		if (id == null) {
			// System.out.println("Could not map City : " + cities[i]);
		} else {
			cityIdList.add(id);
		}

		// try with /
		if (cityIdList.isEmpty() && clCityName.contains("/")) {

			cityIdList = getLocationIdBySplit(clCityName, stateGeo, "/",
					LocationService.GEO_TYPE_CITY);
		}

		// try with -
		if (cityIdList.isEmpty() && clCityName.contains("-")) {

			cityIdList = getLocationIdBySplit(clCityName, stateGeo, "-",
					LocationService.GEO_TYPE_CITY);
		}

		if (cityIdList.isEmpty()) {
			// System.out.println("--Could not map City : " + clCityName);
		}

		return cityIdList;

	}

	private List<Integer> getLocationIdForManualMatch(ManualMatchSite mms,
			GeoLocationEO stateGeo) throws Exception {
		List<Integer> locationIds = new ArrayList<Integer>();
		if (mms == ManualMatchSite.SAN_FRANCISCO_BAY_AREA) {
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Alameda", stateGeo, LocationService.GEO_TYPE_COUNTY)
					.getId());

			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Contra Costa", stateGeo, LocationService.GEO_TYPE_COUNTY)
					.getId());

			locationIds
					.add(locationService.getGeoLocationByNameAndParent("Marin",
							stateGeo, LocationService.GEO_TYPE_COUNTY).getId());

			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Napa", stateGeo, LocationService.GEO_TYPE_COUNTY).getId());

			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"San Francisco", stateGeo, LocationService.GEO_TYPE_COUNTY)
					.getId());

			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"San Mateo", stateGeo, LocationService.GEO_TYPE_COUNTY)
					.getId());

			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Santa Clara", stateGeo, LocationService.GEO_TYPE_COUNTY)
					.getId());

			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Solano", stateGeo, LocationService.GEO_TYPE_COUNTY)
					.getId());

			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Sonoma", stateGeo, LocationService.GEO_TYPE_COUNTY)
					.getId());
		}

		if (mms == ManualMatchSite.RALEIGH_DURHAM_CH) {
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Durham-Chapel Hill", stateGeo,
					LocationService.GEO_TYPE_METRO).getId());

			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Raleigh-Cary", stateGeo, LocationService.GEO_TYPE_METRO)
					.getId());
		}
		
		if (mms == ManualMatchSite.RHODE_ISLAND) {
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Providence", stateGeo,
					LocationService.GEO_TYPE_CITY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Providence", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
		}
		
		if (mms == ManualMatchSite.FLORIDA_KEYS) {
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Monroe", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Miami-Dade", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
		}
		
		if (mms == ManualMatchSite.FT_MYERS_SW_FLORIDA) {
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Fort Myers", stateGeo,
					LocationService.GEO_TYPE_CITY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Manatee", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Sarasota", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Charlotte", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Lee", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Collier", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"North Port-Bradenton-Sarasota", stateGeo,
					LocationService.GEO_TYPE_METRO).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Punta Gorda", stateGeo,
					LocationService.GEO_TYPE_METRO).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Cape Coral-Fort Myers", stateGeo,
					LocationService.GEO_TYPE_METRO).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Naples-Marco Island", stateGeo,
					LocationService.GEO_TYPE_METRO).getId());
		}
		
		if (mms == ManualMatchSite.HEARTLAND_FLORIDA) {
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"DeSoto", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Glades", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Hardee", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Hendry", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Highlands", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Okeechobee", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
		}
		
		if (mms == ManualMatchSite.NORTH_CENTRAL_FL) {
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Gainesville", stateGeo,
					LocationService.GEO_TYPE_METRO).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Alachua", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Gilchrist", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Bradford", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Columbia", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Hamilton", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Lafayette", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Madison", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Marion", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Putnam", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Suwannee", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
			locationIds.add(locationService.getGeoLocationByNameAndParent(
					"Union", stateGeo,
					LocationService.GEO_TYPE_COUNTY).getId());
		}

		return locationIds;
	}

	public enum ManualMatchSite {
		SAN_FRANCISCO_BAY_AREA("san francisco bay area", false), 
		SANTA_BARBARA("santa barbara", true), 
		SANTA_MARIA("santa maria", false), 
		AUSTIN("austin", true), 
		SAN_MARCOS("san marcos", false), 
		CHARLOTTE("charlotte", true),
		RALEIGH_DURHAM_CH("raleigh / durham / CH", false),
		RHODE_ISLAND("rhode island", false),
		FLORIDA_KEYS("florida keys", false),
		FT_MYERS_SW_FLORIDA("ft myers / SW florida", false),
		HEARTLAND_FLORIDA("heartland florida", false),
		NORTH_CENTRAL_FL("north central FL", false)
		;

		private String name;
		private boolean metroMatch;
		private static Map<String, ManualMatchSite> nameMap = new HashMap<String, CraigsListGeoMapping.ManualMatchSite>();

		ManualMatchSite(String name, boolean metroMatch) {
			this.name = name;
			this.metroMatch = metroMatch;
		}

		static {
			for (ManualMatchSite mms : ManualMatchSite.values()) {
				nameMap.put(mms.getName(), mms);
			}
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isMetroMatch() {
			return metroMatch;
		}

		public void setMetroMatch(boolean metroMatch) {
			this.metroMatch = metroMatch;
		}

		public static ManualMatchSite getManualMatchSite(String name) {
			return nameMap.get(name);
		}

	}

}
