/*jslint browser:true, white:true, nomen:true*/
define([ 'jquery', 'knockout', 'jquery.mobile', 'viewModels/PageViewModel',
		'viewModels/CommonViewModel' ],

function($, ko, jqm, pageViewModel, commonViewModel) {
	"use strict";

	function ListViewModel() {

		var self = this;
		self.craigsFeed = ko.observableArray();
		self.loadResults = ko.observable();
		self.pageTitle = ko.observable();
		self.pageNo;
		self.scroll = true;
		var siteUrl;
		var rows;

		self.initList = function(data, url, title) {
			self.pageTitle(title);
			self.pageNo = 0;
			self.scroll = true;
			self.loadResults("Load More");
			siteUrl = url;
			rows = $("#toc_rows > p", data);
			processListHtmlData();
		};

		self.getMore = function() {
			if (scroll) {
				self.pageNo += 1;
				processListHtmlData();
			}
		};

		function processListHtmlData() {
			// var pageNo = 0;
			var batchSize = 25;
			// console.log(data);
			var start = self.pageNo * batchSize;
			var end = start + batchSize;
			if (end >= rows.length) {
				end = rows.length;
				self.scroll = false;
				self.loadResults(rows.length + " Results Found");
			}
			$.map(rows.slice(start, end), function(elem) {
				// console.log(elem);
				var imageId = $("a", elem).attr("data-id");
				var url = $("a", $(".pl", elem)).attr("href");
				var title = $("a", $(".pl", elem)).text();
				var price = $(".price", elem).text();
				var location = $(".pnr > small", elem).text();
				var item = {
					title : title,
					link : siteUrl + url,
					thumb : (imageId === undefined ? "images/missing.jpg"
							: "http://images.craiglist.org/thumb/" + imageId),
					price : price,
					location : location
				};
				self.craigsFeed.push(item);
			});
		}
		;

		self.processListData = function(feedData) {
			$(feedData).find("item").each(
					function(index, elem) {

						var el = $(elem);
						var item = {
							title : el.find('title').text().replace(
									"<![CDATA[", "").replace("]]>", ""),
							link : el.find('link').text()
						};

						self.craigsFeed.push(item);
					});

			try {
				$("#listViewContent ul").listview("refresh");
			} catch (err) {
				// console.log(err);
			}
		};

		// Page View Page
		self.pageViewJS = function(feed) {

			//pageViewModel.pageTitle(self.pageTitle());
			//pageViewModel.pageUrl(feed.link);
			getPageData(feed.link, self.pageTitle());

			jqm.changePage("#pageView");

		};
		
		function getPageData(url, pageTitle) {
			$.ajax({
				type : 'GET',
				url : url,
				dataType : 'html',
				success : function(data) {
					var obj = scrapePageData(data);
					obj.pageTitle = pageTitle;
					obj.pageUrl = url;
					pageViewModel.configPage(obj);
				}
			});
		};

		function scrapePageData(data) {
			var xmlDoc;
			var htmlCode = $(data).html();
			var title = $(".postingtitle", data).text().trim();
			var mailTo = $(".dateReplyBar > a", data).attr("href");
			var imgAttr = $("img", data).attr('src');
			var desc = $("#postingbody", data).text().trim();
			console.log(desc);
			var images = new Array();
			$("#thumbs", data).children().each(function(index, elem) {

				var imageUrl = $(elem).attr("href");
				var thumbUrl = $(":first-child", elem).attr("src");
				var image = {
					"imageUrl" : imageUrl,
					"thumbUrl" : thumbUrl
				};
				images.push(image);
			});

			return {
				fullTitle: title,
				mailTo: mailTo,
				description : desc,
				images : images
			};

		};
/*
		self.pageView = function(feed) {
			$.ajax({
				url : commonViewModel.serverUrl + "services/rs/feed/page",
				type : "GET",
				contentType : "application/json",
				dataType : "json",
				data : {
					pageUrl : feed.link
				},
				async : false,
				success : function(data) {
					// console.log(data.description);
					pageViewModel.pageTitle(self.pageTitle());
					pageViewModel.pageUrl(feed.link);
					pageViewModel.description(data.description);
					var imageArr = $.makeArray(data.images);
					pageViewModel.images.removeAll();
					imageArr.forEach(function(item, index, array) {
						pageViewModel.images.push(item);
					});

				}
			});
		};
*/		
	}

	return new ListViewModel();

});