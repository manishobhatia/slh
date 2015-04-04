/*jslint browser:true, white:true, nomen:true*/
define([ 'jquery', 'knockout', 'jquery.mobile', 'viewModels/ListViewModel', 'viewModels/CommonViewModel' ],

function($, ko, jqm, listViewModel, commonViewModel) {
	"use strict";

	function SearchViewModel() {

		var self = this;

		//var listViewModel = ListViewModel;

		self.searchTxt = ko.observable("");
		self.minPrice = ko.observable(0);
		self.maxPrice = ko.observable(0);
		//self.hasPic = ko.observable(false);
		self.hasPic = ko.observable("no");
		self.maxSlider = ko.observable();

		self.searchTitle = ko.observable();
		self.searchButtonArr = ko.observableArray();
		
		self.site;

		self.resetSearchFlag = false;

		self.activate = function() {
			$('#minPrice').on('slidestop', function(e) {
				// console.log("Inside minPrice slidestop");
				refreshSlider();
			});
			$('#maxPrice').on('slidestop', function(e) {
				// console.log("Inside maxPrice slidestop");
				refreshSlider();

			});
			resetSearch();

		};

		function resetSearch() {
			if (self.resetSearchFlag) {
				self.minPrice(0);
				self.maxPrice(self.maxSlider() - 1);
				$('#minPrice').slider('refresh');
				$('#maxPrice').slider('refresh');
				self.searchTxt("");
			//	self.hasPic(false);
			//	$("#hasPic").checkboxradio("refresh");
				self.hasPic("no");
				$("#hasPic").slider("refresh");
				try {
				$("div#searchButton").find('a').controlgroup('refresh', true);
				} catch (error) {
					
				}

				self.resetSearchFlag = false;

			}
		}
		;

		function refreshSlider() {
			var maxP = parseInt(self.maxPrice());
			var minP = parseInt(self.minPrice());
			var maxS = parseInt(self.maxSlider());
			// Increament Max Slider
			if (minP == maxS || maxP == maxS) {
				self.maxSlider(maxS + maxS / 2);
				$('#minPrice').slider('refresh');
				$('#maxPrice').slider('refresh');
			}
			// Check for Max Price
			if (maxP > 0 && maxP < minP) {
				self.maxPrice(minP);
				$('#maxPrice').slider('refresh');
			}

		}
		;

		self.getFeeds = function(buttonData) {
			$.ajax({
				url : commonViewModel.serverUrl+"services/rs/feed",
				type : "GET",
				contentType : "application/json",
				data : {
					categoryId : buttonData.id,
					searchTxt : self.searchTxt(),
					hasPic : self.hasPic() == "yes" ? true : false,
					minPrice : self.minPrice(),
					maxPrice : self.maxPrice()
				},
				dataType : "json",
				async : false,
				success : function(data) {
					var arrayData = $.makeArray(data);

					listViewModel.craigsFeed.removeAll();
					arrayData.forEach(function(item, index, array) {
						// console.log(item.title);
						listViewModel.craigsFeed.push(item);
					});
					try {
						$("#listViewContent ul").listview("refresh");
					} catch (err) {
						// console.log(err);
					}

				}
			});
		};
		self.getFeedsJS = function(buttonData) {
			listViewModel.craigsFeed.removeAll();
			var feedData;
			$.ajax({
				url : commonViewModel.serverUrl+"services/rs/feed/feedUrl",
				type : "GET",
				contentType : "application/json",
				data : {
					categoryId : buttonData.id,
				},
				dataType : "json",
				async : false,
				success : function(data) {
					feedData = data;
				}
			});

	//		console.log(feedData);
			var feedUrl;
			var data = getUrlData(self.searchTxt(), self.hasPic(), self.minPrice(), self.maxPrice());
			if (!data) {
				feedUrl = self.site.url + "/" + feedData.category +  "/index.rss";
			} else {
				feedUrl = self.site.url + "/search/" + feedData.category;
			}
			console.log(feedUrl);
			console.log(data);
			$.ajax({
				type : 'GET',
				url : feedUrl,
				data: data,
				dataType : 'html',
				success : function(data) {
					//listViewModel.processListData(data);
					listViewModel.initList(data, self.site.url, self.searchTitle());
				}
			});
			jqm.changePage("#listView");
		};

		function getUrlData(searchTxt, hasPic, minPrice, maxPrice) {

			if (isEmpty(searchTxt) && !hasPic && (isEmpty(minPrice) || minPrice === "0")
					&& (isEmpty(maxPrice) || maxPrice === "0")) {
				return null;
			} else {
				return {
					"query" : searchTxt,
					"hasPic" : (hasPic == "yes" ? "1" : "0"),
					"minAsk" : minPrice,
					"maxAsk" : maxPrice,
					"srchType" : "A",
					//"format" : "rss"
				};
			}
		}

		function isEmpty(str) {
			return (!str || !str.length);
		}

	}
	;

	return new SearchViewModel();
});