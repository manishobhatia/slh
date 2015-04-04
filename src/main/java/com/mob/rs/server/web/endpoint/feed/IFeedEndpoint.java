package com.mob.rs.server.web.endpoint.feed;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.mob.rs.server.core.MediaTypeExtended;
import com.mob.rs.server.model.feed.Category;
import com.mob.rs.server.model.feed.Feed;
import com.mob.rs.server.model.feed.Location;
import com.mob.rs.server.model.feed.Page;
import com.mob.rs.server.model.feed.Sites;
import com.mob.rs.server.web.common.cache.NoCache;
import com.mob.rs.server.web.support.metadata.Resource;

@Produces({ MediaTypeExtended.APPLICATION_JSON_UTF_8,
		MediaTypeExtended.TEXT_PLAIN_UTF_8 })
@Consumes({ MediaTypeExtended.APPLICATION_JSON_UTF_8,
		MediaTypeExtended.TEXT_PLAIN_UTF_8 })
@Path(Resource.FEED)
public interface IFeedEndpoint {

	@GET
	@Path(Resource.ROOT)
	@NoCache
	public List<Feed> getFeeds(@QueryParam("categoryId") Integer categoryId,
			@QueryParam("searchTxt") String searchTxt,
			@QueryParam("hasPic") boolean hasPic,
			@QueryParam("minPrice") String minPrice,
			@QueryParam("maxPrice") String maxPrice);

	@GET
	@Path(Resource.FEED_URL)
	@NoCache
	public Response getFeedURL(@QueryParam("categoryId") Integer categoryId);

	@GET
	@Path(Resource.PAGE)
	@NoCache
	public Page getPage(@QueryParam("pageUrl") String pageUrl);

	@GET
	@Path(Resource.CATEGORY)
	@NoCache
	public List<Category> getCategories(@QueryParam("parentId") String parentId);

	@GET
	@Path(Resource.SITE)
	@NoCache
	public List<Sites> getSites(@QueryParam("address1") String address1,
			@QueryParam("address2") String address2,
			@QueryParam("city") String city,
			@QueryParam("county") String county,
			@QueryParam("state") String state,
			@QueryParam("stateCode") String stateCode,
			@QueryParam("country") String country,
			@QueryParam("countryCode") String countryCode,
			@QueryParam("zip") String zip);

	@GET
	@Path(Resource.ALL_STATES)
	@NoCache
	public List<Location> getAllStates(@QueryParam("country") String country,
			@QueryParam("countryCode") String countryCode);
	
	@GET
	@Path(Resource.SITE_FOR_LOCATION)
	@NoCache
	public List<Sites> getSitesForLocation(@QueryParam("locationId") Integer locationId);

}
