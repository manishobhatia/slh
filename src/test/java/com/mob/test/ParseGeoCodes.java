package com.mob.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class ParseGeoCodes {

	public static Map<String, List<Metro>> stateByMetroMap;
	public static Map<String, Integer> metroById = new HashMap<String, Integer>();

	public static Map<Integer, List<Integer>> parentByGeoIdMap = new HashMap<Integer, List<Integer>>();

	public static void main(String[] args) throws Exception {

		printSQL(true);

	}

	public static void printSQL(boolean printToConsole) throws Exception {
		int GEO_TYPE_COUNTRY = 1;
		int GEO_TYPE_STATE = 2;
		int GEO_TYPE_CITY = 3;
		int GEO_TYPE_COUNTY = 4;
		int GEO_TYPE_METRO = 5;

		// Country Insert
		int count = 0;
		int usId = ++count;
		if (printToConsole)
			System.out.println("(" + usId + ", 'United States', 'US', "
					+ GEO_TYPE_COUNTRY + "),");

		Map<String, List<String>> stateByCityMap = getStateByCityMap();
		Map<String, List<String>> stateCodeByCountyMap = getStateCodeByCountyMap();
		stateByMetroMap = getStateByMetroMap();

		// Read State File
		FileInputStream stateFstream = new FileInputStream(
				"/Users/manishbhatia/git/slh/src/test/java/US_States.csv");

		DataInputStream stateIn = new DataInputStream(stateFstream);
		BufferedReader stateBr = new BufferedReader(new InputStreamReader(
				stateIn));
		String strLine;
		// Read File Line By Line
		while ((strLine = stateBr.readLine()) != null) {

			String[] elements = strLine.split(",");

			String name = elements[0].trim();
			String code = elements[1].trim();

			int stateId = ++count;

			if (printToConsole)
				System.out.println("(" + stateId + ", '" + name + "', '" + code
						+ "', " + GEO_TYPE_STATE + "),");

			addToParent(stateId, usId);

			String stateKey = name.toUpperCase();
			String stateCodeKey = code.toUpperCase();

			List<String> cityList = stateByCityMap.get(stateKey);
			if (cityList != null) {
				for (String city : cityList) {
					city = city.replace("'", "''");
					int cityId = ++count;
					if (printToConsole)
						System.out.println("(" + cityId + ", '" + city
								+ "', null, " + GEO_TYPE_CITY + "),");

					addToParent(cityId, usId);
					addToParent(cityId, stateId);

				}
			}

			Map<String, Integer> countyByMetroId = new HashMap<String, Integer>();
			List<Metro> metroList = stateByMetroMap.get(stateCodeKey);
			if (metroList != null) {
				for (Metro metro : metroList) {
					String metroName = metro.getName().replace("'", "''");
					Integer metroId = metroById.get(metroName + ","
							+ metro.getStates());
					if (metroId == null) {
						metroId = ++count;
						if (printToConsole)
							System.out.println("(" + metroId + ", '"
									+ metroName + "', null, " + GEO_TYPE_METRO
									+ "),");

						addToParent(metroId, usId);
						addToParent(metroId, stateId);

						metroById.put(metroName + "," + metro.getStates(),
								metroId);
					}

					List<String> counties = metro.getStateBycountyMap().get(
							stateCodeKey);
					for (String county : counties) {
						countyByMetroId.put(county, metroId);
					}
				}
			}

			List<String> countyList = stateCodeByCountyMap.get(stateCodeKey);
			if (countyList != null) {
				for (String county : countyList) {
					Integer metroId = countyByMetroId.get(county);

					county = county.replace("'", "''");
					int countyId = ++count;
					if (printToConsole)
						System.out.println("(" + countyId + ", '" + county
								+ "', null, " + GEO_TYPE_COUNTY + "),");

					addToParent(countyId, usId);
					addToParent(countyId, stateId);
					if (metroId != null) {
						addToParent(countyId, metroId);
					}

				}
			}
			// break;
		}

		stateBr.close();
		stateIn.close();
		stateFstream.close();

		printParentSql(printToConsole);
	}

	private static void addToParent(int geoId, int parentId) {
		List<Integer> geoIds = parentByGeoIdMap.get(parentId);
		if (geoIds == null) {
			geoIds = new ArrayList<Integer>();
		}
		geoIds.add(geoId);
		parentByGeoIdMap.put(parentId, geoIds);
	}

	private static void printParentSql(boolean printToConsole) {
		int count = 1;
		Set<Integer> keys = parentByGeoIdMap.keySet();
		for (Integer parentId : keys) {
			List<Integer> geoIds = parentByGeoIdMap.get(parentId);

			for (Integer geoId : geoIds) {
				int cnt = count++;
				if (printToConsole)
					System.out.println("(" + cnt + ", " + geoId + ", "
							+ parentId + "),");
			}
		}
	}

	private static Map<String, List<String>> getStateByCityMap()
			throws Exception {
		// Read City File
		FileInputStream cityFstream = new FileInputStream(
				"/Users/manishbhatia/git/slh/src/test/java/US_Cities.csv");

		DataInputStream cityIn = new DataInputStream(cityFstream);
		BufferedReader cityBr = new BufferedReader(
				new InputStreamReader(cityIn));
		String cStrLine;
		Map<String, List<String>> stateByCityMap = new HashMap<String, List<String>>();
		// Read File Line By Line
		while ((cStrLine = cityBr.readLine()) != null) {

			String[] elements = cStrLine.split(",");
			if (!elements[elements.length - 2].equals("City")) {
				continue;
			}

			String city = elements[1].trim();
			String state = elements[3].trim().toUpperCase();

			List<String> cityList = stateByCityMap.get(state);
			if (cityList == null) {
				cityList = new ArrayList<String>();
			}
			if (!cityList.contains(city)) {
				cityList.add(city);
				stateByCityMap.put(state, cityList);
			}

		}

		cityBr.close();
		cityIn.close();
		cityFstream.close();

		return stateByCityMap;
	}

	private static Map<String, List<String>> getStateCodeByCountyMap()
			throws Exception {
		// Read City File
		FileInputStream countyFstream = new FileInputStream(
				"/Users/manishbhatia/git/slh/src/test/java/US_Counties.csv");

		DataInputStream countyIn = new DataInputStream(countyFstream);
		BufferedReader countyBr = new BufferedReader(new InputStreamReader(
				countyIn));
		String cStrLine;
		Map<String, List<String>> stateByCountyMap = new HashMap<String, List<String>>();
		// Read File Line By Line
		while ((cStrLine = countyBr.readLine()) != null) {

			String[] elements = cStrLine.split(",");

			String county = elements[0].trim();
			String stateCode = elements[2].trim().toUpperCase();

			List<String> countyList = stateByCountyMap.get(stateCode);
			if (countyList == null) {
				countyList = new ArrayList<String>();
			}
			if (!countyList.contains(county)) {
				countyList.add(county);
				stateByCountyMap.put(stateCode, countyList);
			}

		}

		countyBr.close();
		countyIn.close();
		countyFstream.close();

		return stateByCountyMap;
	}

	private static Map<String, List<Metro>> getStateByMetroMap()
			throws Exception {

		// Read City File
		FileInputStream metroFstream = new FileInputStream(
				"/Users/manishbhatia/git/slh/src/test/java/US_Metro_County.txt");

		DataInputStream metroIn = new DataInputStream(metroFstream);
		BufferedReader metroBr = new BufferedReader(new InputStreamReader(
				metroIn));
		String cStrLine;
		Map<String, List<Metro>> stateBymetroList = new HashMap<String, List<Metro>>();
		// Read File Line By Line
		int lineCount = 0;
		String cbsaCode = null;
		Metro metro = null;
		int metroCount = 0;
		while ((cStrLine = metroBr.readLine()) != null) {
			lineCount++;

			if (lineCount >= 12 && lineCount <= 3812) {

				if (cStrLine.length() > 0) {
					String code = cStrLine.substring(0, 8).trim();

					if (!code.equals(cbsaCode)) {
						metro = new Metro();
						metroCount++;
						// Get Code and Names
						cbsaCode = code;
						String[] metroAndState = cStrLine
								.substring(24)
								.replaceAll(
										"M[a-z]*ropolitan Statistical Area", "")
								.trim().split(",");
						metro.setName(metroAndState[0].trim());
						metro.setStates(metroAndState[1].trim().split("-"));

					} else {
						String divCode = cStrLine.substring(8, 16).trim();
						String fipsCode = cStrLine.substring(16, 24).trim();

						if (StringUtils.isEmpty(fipsCode)) {
							continue;
						}
						Map<String, List<String>> stateBycountyMap = metro
								.getStateBycountyMap();
						if (stateBycountyMap == null) {
							stateBycountyMap = new HashMap<String, List<String>>();
						}
						String[] countyAndState = cStrLine.substring(24).trim()
								.split(",");

						List<String> counties = stateBycountyMap
								.get(countyAndState[1].trim());
						if (counties == null) {
							counties = new ArrayList<String>();
						}
						counties.add(countyAndState[0].replace("County", "")
								.replace("Parish", "").replace("Municipio", "")
								.trim());

						stateBycountyMap
								.put(countyAndState[1].trim(), counties);

						metro.setStateBycountyMap(stateBycountyMap);
					}

				} else {
					String[] states = metro.getStates();
					for (String state : states) {
						List<Metro> metros = stateBymetroList.get(state);
						if (metros == null) {
							metros = new ArrayList<ParseGeoCodes.Metro>();
						}
						metros.add(metro);
						stateBymetroList.put(state, metros);
					}
				}

			}

		}

		metroBr.close();
		metroIn.close();
		metroFstream.close();

		return stateBymetroList;

	}

	public static class Metro {

		private String[] states;

		private Map<String, List<String>> stateBycountyMap;

		private String name;

		public String[] getStates() {
			return states;
		}

		public void setStates(String[] states) {
			this.states = states;
		}

		public Map<String, List<String>> getStateBycountyMap() {
			return stateBycountyMap;
		}

		public void setStateBycountyMap(
				Map<String, List<String>> stateBycountyMap) {
			this.stateBycountyMap = stateBycountyMap;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Metro [states=" + Arrays.toString(states)
					+ ", stateBycountyMap=" + stateBycountyMap + ", name="
					+ name + "]";
		}

	}
}
