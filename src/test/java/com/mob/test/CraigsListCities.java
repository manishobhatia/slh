package com.mob.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CraigsListCities {

	private static int siteLocationCount = 0;

	public static void main(String[] args) throws Exception {
		
		printSql(true);

	}

	public static Map<String, List<String>> printSql(boolean printToConsole)
			throws Exception {
		//Document doc = Jsoup.connect("http://www.craigslist.org/about/sites")
		//		.get();
		Document doc = Jsoup.connect("http://localhost/Craigslist_Cities.html")
				.get();

		
		Elements continentElems = doc.getElementsByAttributeValue("class",
				"continent_header");
		// System.out.println(continentElems.size());

		int sitesCount = 0;
		Map<String, List<String>> stateByCitiesMap = new HashMap<String, List<String>>();

		// Continent Loop
		for (Element continentElem : continentElems) {
			String continent = continentElem.childNode(1).toString();

			if (continent.equals("US")) {
				Elements stateElems = continentElem
						.nextElementSibling()
						.getElementsByAttributeValue("class", "state_delimiter");

				// State Loop
				for (Element stateElem : stateElems) {
					String state = stateElem.childNode(0).toString().trim();
//					System.out.println(state);
					List<String> cities = stateByCitiesMap.get(state);
					if (cities == null) {
						cities = new ArrayList<String>();
					}

					Elements cityElems = stateElem.nextElementSibling()
							.children();
					// System.out.println(cityElems.size());
					// City Loop
					for (Element cityElem : cityElems) {
						String cityHref = cityElem.children().get(0)
								.attr("href");

						String cityName = cityElem.children().get(0).ownText()
								.trim();

						int siteId = ++sitesCount;
						String city = cityName.replace("'", "''");

						if (printToConsole)
							System.out.println("(" + siteId + ", '" + city
									+ "', '" + cityHref + "', " + 1 + "),");

						cities.add(city + ", " + siteId);

					}
					stateByCitiesMap.put(state, cities);
				}
				break;

			}
		}

		return stateByCitiesMap;
	}

}
