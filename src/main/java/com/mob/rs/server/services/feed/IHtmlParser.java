package com.mob.rs.server.services.feed;

import com.mob.rs.server.model.feed.Page;

public interface IHtmlParser {
	
	public Page getPage(String pageUrl) throws Exception;

}
