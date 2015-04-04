package com.mob.rs.server.services.feed;

import java.util.List;

import com.mob.rs.server.model.feed.Feed;

public interface IFeedReader {
	
	public List<Feed> getFeeds(String url);

}
