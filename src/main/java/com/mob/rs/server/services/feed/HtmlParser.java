package com.mob.rs.server.services.feed;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.mob.rs.server.model.feed.Image;
import com.mob.rs.server.model.feed.Page;

@Component("htmlParser")
public class HtmlParser implements IHtmlParser {

	public static void main(String[] args) throws Exception {
		HtmlParser htmlParser = new HtmlParser();
		htmlParser
				.getPage("http://dallas.craigslist.org/dal/cto/3462027802.html");

	}

	@Override
	public Page getPage(String pageUrl) throws Exception {
		Page page = new Page();
		page.setPageUrl(pageUrl);
		Document doc = Jsoup.connect(pageUrl).get();

		// Logic to get user description
		Element descElement = doc.getElementById("userbody");
		System.out.println(descElement.ownText());
		page.setDescription(descElement.ownText());

		// Logic to get image url's
		Elements elements = doc.select("a[href*=.jpg]");
		System.out.println(elements.size() + " Image urls ...");
		List<Image> images = new ArrayList<Image>();
		for (Element e : elements) {
			Image image = new Image();
			image.setImageUrl(e.attr("abs:href"));
			if (!e.children().isEmpty()) {
				Element thumbImg = e.child(0);
				// System.out.println(thumbImg.attr("abs:src"));
				image.setThumbUrl(thumbImg.attr("abs:src"));
			}
			images.add(image);

		}
		page.setImages(images);
		return page;
	}

}
