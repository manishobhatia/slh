package com.mob.rs.server.web.endpoint.feed;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.support.UrlBasedRemoteAccessor;
import org.springframework.stereotype.Component;

import com.mob.rs.server.hibernate.model.CategoryEO;
import com.mob.rs.server.model.feed.Category;
import com.mob.rs.server.model.feed.Feed;
import com.mob.rs.server.model.feed.Location;
import com.mob.rs.server.model.feed.Page;
import com.mob.rs.server.model.feed.Sites;
import com.mob.rs.server.services.feed.ICategoryService;
import com.mob.rs.server.services.feed.IFeedReader;
import com.mob.rs.server.services.feed.IHtmlParser;
import com.mob.rs.server.services.feed.ILocationService;
import com.mob.rs.server.web.bind.IJsonNode;
import com.mob.rs.server.web.endpoint.support.Endpoint;

@Component("FeedEndpoint")
public class FeedEndpoint extends Endpoint implements IFeedEndpoint {

	@Autowired
	private IFeedReader feedReader;

	@Autowired
	private IHtmlParser htmlParser;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ILocationService locationService;

	@Override
	public List<Feed> getFeeds(Integer categoryId, String searchTxt,
			boolean hasPic, String minPrice, String maxPrice) {

		return feedReader.getFeeds(getURL(categoryId, searchTxt, hasPic,
				minPrice, maxPrice));

	}

	@Override
	public Response getFeedURL(Integer categoryId) {
		CategoryEO categoryEo = categoryService.getCategoryEO(categoryId);
		IJsonNode jsonObj = getJsonContext().createJsonNode();
		return Response.ok(
				jsonObj.add("url", "http://dallas.craigslist.org").add(
						"category", categoryEo.getUrl())).build();

	}

	private String getURL(Integer categoryId, String searchTxt, boolean hasPic,
			String minPrice, String maxPrice) {
		URIBuilder uriBuilder = null;
		String encodedurl = null;
		System.out.println("Category Id : " + categoryId);
		try {
			uriBuilder = new URIBuilder("http://dallas.craigslist.org");
			CategoryEO categoryEo = categoryService.getCategoryEO(categoryId);

			minPrice = resetPrice(minPrice);
			maxPrice = resetPrice(maxPrice);

			if (StringUtils.isEmpty(searchTxt) && StringUtils.isEmpty(minPrice)
					&& StringUtils.isEmpty(maxPrice) && !hasPic) {
				uriBuilder.setPath("/" + categoryEo.getUrl() + "/index.rss");

			} else {
				uriBuilder.setPath("/search/" + categoryEo.getUrl());

				uriBuilder.setParameter("query", searchTxt);
				uriBuilder.setParameter("hasPic", (hasPic ? "1" : "0"));
				uriBuilder.setParameter("minAsk", minPrice);
				uriBuilder.setParameter("maxAsk", maxPrice);
				uriBuilder.setParameter("srchType", "A");
				uriBuilder.setParameter("format", "rss");
			}
			// encodedurl = URLEncoder.encode(uriBuilder.toString(), "UTF-8");
			encodedurl = uriBuilder.toString();
			System.out.println("Encoded url : " + encodedurl);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return encodedurl;
	}

	private String resetPrice(String price) {
		if (!StringUtils.isEmpty(price) && "0".equals(price)) {
			price = "";
		}
		return price;
	}

	@Override
	public Page getPage(String pageUrl) {
		Page page = null;
		try {
			page = htmlParser.getPage(pageUrl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return page;
	}

	@Override
	public List<Category> getCategories(String parentId) {
		CategoryEO parent = null;
		if (!StringUtils.isEmpty(parentId)) {
			parent = new CategoryEO();
			parent.setId(Integer.valueOf(parentId));
		}
		List<Category> categories = new ArrayList<Category>();
		for (CategoryEO catEO : categoryService.findByParentCategory(parent)) {
			Category category = new Category();
			category.setId(catEO.getId());
			category.setMaxPrice(catEO.getMaxPrice());
			category.setName(catEO.getName());
			category.setUrl(catEO.getUrl());

			categories.add(category);
		}
		return categories;
	}

	@Override
	public List<Sites> getSites(String address1, String address2, String city,
			String county, String state, String stateCode, String country,
			String countryCode, String zip) {
		List<Sites> results = null;
		try {
			Location location = new Location(address1, address2, city, county,
					state, stateCode, country, countryCode, zip);
			results = locationService.getSites(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;

	}
	
	@Override
	public List<Sites> getSitesForLocation(Integer locationId) {
		return locationService.getSitesForLocation(locationId);
	}

	@Override
	public List<Location> getAllStates(String country, String countryCode) {
		List<Location> results = null;
		try {
			Location location = new Location(null, null, null, null, null,
					null, country, countryCode, null);
			results = locationService.getAllStates(location);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return results;
	}

	public static void main(String[] args) {
		FeedEndpoint feedEndpoint = new FeedEndpoint();
		// feedEndpoint.getFeeds("suv", true, "20000", "30000");
	}

}
