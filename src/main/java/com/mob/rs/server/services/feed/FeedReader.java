package com.mob.rs.server.services.feed;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.mob.rs.server.model.feed.Feed;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Reads and prints any RSS/Atom feed type. Adopted from the example by the same
 * name at http://wiki.java.net/bin/view/Javawsxml/Rome05TutorialFeedReader
 * 
 */

@Component("feedReader")
public class FeedReader implements IFeedReader {

	public List<Feed> getFeeds(String url) {
		
		List<Feed> feeds = new ArrayList<Feed>();
		try {
			URL feedUrl = new URL(url);
			final SyndFeedInput input = new SyndFeedInput();
			final SyndFeed syndfeed = input.build(new XmlReader(feedUrl));
			
			List<SyndEntry> entriesIT = syndfeed.getEntries();
			for (SyndEntry entry : entriesIT) {
				Feed feed = new Feed();
				feed.setTitle(entry.getTitle());
				feed.setLink(entry.getLink());
				feed.setPublishedDate(entry.getPublishedDate());
				
				feeds.add(feed);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return feeds;
	}

	public static void main(final String[] args) {
		FeedReader feedReader = new FeedReader();
		List<Feed> feeds =  feedReader.getFeeds("http://dallas.craigslist.org/search/cto?hasPic=1&maxAsk=30000&minAsk=20000&query=suv&srchType=T&format=rss");
		
		for (Feed feed : feeds) {
			System.out.println (feed.getTitle());
		}
	}

}