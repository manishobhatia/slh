package com.mob.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mob.rs.server.model.feed.Location;
import com.mob.rs.server.model.feed.Sites;
import com.mob.rs.server.services.feed.ILocationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "rsserver-app-context.xml" })
public class LocationServiceTest {

	@Autowired
	private ILocationService locationService;
//	{"address1":"1","address2":"Stockton Street","city":"San Francisco","county":"San Francisco","state":"California","stateCode":"CA","country":"United States","countryCode":"US","zip":"94108"}
	@Test
	public void testGetSites() {
		Location location = new Location();
		location.setCity("werew");
		location.setCounty("ppp");
		location.setStateCode("RI");
		location.setCountryCode("US");
		try {
			List<Sites> sites = locationService.getSites(location);

			for (Sites site : sites) {
				System.out.println("Site Name : " + site.getName());
				System.out.println("Site Url : " + site.getUrl());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
