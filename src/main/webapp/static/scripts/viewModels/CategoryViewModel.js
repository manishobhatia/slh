/*jslint browser:true, white:true, nomen:true*/
define([ 'jquery', 'knockout', 'jquery.mobile', 'viewModels/SearchViewModel',
		'viewModels/CommonViewModel' ],

function($, ko, jqm, searchViewModel, commonViewModel) {
	"use strict";

	function CategoryViewModel() {

		var self = this;
		// var searchViewModel = SearchViewModel;

		self.categoryValue = ko.observable();
		self.categoryArr = ko.observableArray();
		self.subCategoryValue = ko.observable();
		self.subCategoryArr = ko.observableArray();
		self.siteName = ko.observable("Select a Location ...");
		self.statesArr = ko.observableArray();
		self.siteArr = ko.observableArray();
		
		self.fillerButton = "None";

		// Category Page
		getCategorySelect();
		getCurrentSite();
		

		self.activate = function() {

			getAllStates(null, "US");
			$('.sh').collapsible();
			$(".sh").on('expand', function(event) {
				var locationId = $(this).attr('record');
				// console.log(locationId);
				self.getStateSites(locationId);
			});
		};
		
	//	testLocalStorage();
		
		function testLocalStorage() {
			console.log(commonViewModel.supports_html5_storage());
			console.log(commonViewModel.supports_json());
			
			//commonViewModel.addToStorage("test1", {name:"manish",age:24});
			console.log(commonViewModel.hasInStorage("test1"));
			console.log(commonViewModel.getStorage());
			commonViewModel.removeFromStorage("test1");
			
		};

		function getCurrentSite() {
			var lat;
			var long;
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					lat = position.coords.latitude;
					long = position.coords.longitude;
					var location = getLocationFromGoogleApi(lat, long);
					console.log(location);
					getSites(location, function(sites) {
						if (sites.length == 1) {
							self.siteName(sites[0].name);
							searchViewModel.site = sites[0];
						}
					});

				});
			} else {
				console.log("Geolocation is not supported by this browser.");
			}
		};


		function getSites(location, callback) {
			$.ajax({
				url : commonViewModel.serverUrl + "services/rs/feed/site",
				type : "GET",
				contentType : "application/json",
				data : {
					address1 : location.address1,
					address2 : location.address2,
					city : location.city,
					county : location.county,
					state : location.state,
					stateCode : location.stateCode,
					country : location.country,
					countryCode : location.countryCode,
					zip : location.zip
				},
				dataType : "json",
				async : false,
				success : function(data) {
					// console.log(data);
					callback($.makeArray(data));
				}
			});

		}

		function getAllStates(country, countryCode) {
			$.ajax({
				url : commonViewModel.serverUrl + "services/rs/feed/allStates",
				type : "GET",
				contentType : "application/json",
				data : {
					country : country,
					countryCode : countryCode
				},
				dataType : "json",
				async : false,
				success : function(data) {
					// console.log(data);
					var arr = $.makeArray(data);
					self.statesArr(arr);
				}
			});
		}

		function getLocationFromGoogleApi(lat, long) {
			var location = new Object();
			$.ajax({
				url : "http://maps.googleapis.com/maps/api/geocode/json",
				type : "GET",
				contentType : "application/json",
				data : {
					latlng : lat + "," + long,
					sensor : false
				},
				dataType : "json",
				async : false,
				success : function(data) {
					var firstObj = $.makeArray(data.results)[0];
					(firstObj.address_components).forEach(function(item, index,
							array) {
						// console.log(item.long_name);
						if ($.inArray('street_number', item.types) != -1) {
							location.address1 = item.long_name;
						}
						if ($.inArray('route', item.types) != -1) {
							location.address2 = item.long_name;
						}
						if ($.inArray('locality', item.types) != -1) {
							location.city = item.long_name;
						}
						if ($
								.inArray('administrative_area_level_2',
										item.types) != -1) {
							location.county = item.long_name;
						}
						if ($
								.inArray('administrative_area_level_1',
										item.types) != -1) {
							location.state = item.long_name;
							location.stateCode = item.short_name;
						}
						if ($.inArray('country', item.types) != -1) {
							location.country = item.long_name;
							location.countryCode = item.short_name;
						}
						if ($.inArray('postal_code', item.types) != -1) {
							location.zip = item.long_name;
						}
					});

				}

			});

			return location;
		}

		function getSiteLocation(locationId, callback) {
			$.ajax({
				url : commonViewModel.serverUrl
						+ "services/rs/feed/siteForLocation",
				type : "GET",
				contentType : "application/json",
				data : {
					locationId : locationId
				},
				dataType : "json",
				async : false,
				success : function(data) {
					// console.log(data);
					callback($.makeArray(data));
				}
			});

		}

		self.getStateSites = function(stateLocationId) {
			getSiteLocation(stateLocationId, function(sites) {
				self.siteArr(sites);

			});
		};

		self.selectSite = function(data) {
			self.siteName(data.name);
			searchViewModel.site = data;
			$("#locationPanel").panel("close");
		};

		function getCategorySelect() {
			getCategories(null, function(data) {
				// console.log(data);
				self.categoryArr($.makeArray(data));
				try {
					$('#category').selectmenu('refresh', true);
				} catch (error) {

				}
			});
		}
		;

		self.categoryValue.subscribe(function() {
			self.getSubCategorySelect();
		});
		self.getSubCategorySelect = function() {
			getCategories(self.categoryValue().id, function(data) {
				var allCat = $.extend({}, self.categoryValue());
				allCat.name = "All " + allCat.name;
				var dataArr = $.makeArray(data);
				dataArr.unshift(allCat);
				dataArr.forEach(function(item, index, array) {
					if (index % 2 == 0) {
						if (index + 1 == array.length) {
							self.subCategoryArr.push({
								a : item,
								b : {name: self.fillerButton, url: ""}
							});
						}
					} else {
						// b = item;
						self.subCategoryArr.push({
							a : array[index - 1],
							b : item
						});
					}

				});
				try {
					$('#subCategory').grid('refresh');
				} catch (error) {
					// Not sure why error is thrown
				}
			});
		};

		function getCategories(parentId, successCallback) {
			$.ajax({
				url : commonViewModel.serverUrl + "services/rs/feed/category",
				type : "GET",
				contentType : "application/json",
				data : {
					parentId : parentId
				},
				dataType : "json",
				async : false,
				success : function(data) {
					successCallback(data);
				}

			});
		}
		;

		self.searchView = function(data, event) {
			var subCategory;
			if (event.currentTarget.id == "buttA") {
				subCategory = data.a;
			} else {
				subCategory = data.b;
			}
			console.log(subCategory);
			if (subCategory.name == self.fillerButton) {
				return;
			}
			self.subCategoryValue(subCategory);
			searchViewModel.searchTitle(subCategory.name);
			searchViewModel.maxSlider(subCategory.maxPrice);

			searchViewModel.searchButtonArr.removeAll();
			searchViewModel.searchButtonArr.push({
				id : subCategory.id,
				name : 'Search'
			});
			if (subCategory.id != self.categoryValue().id) {
				getCategories(subCategory.id, function(data) {
					$.makeArray(data).forEach(
							function(item, index, array) {
								var text = item.name.replace(searchViewModel
										.searchTitle()
										+ ' By', '');
								searchViewModel.searchButtonArr.push({
									id : item.id,
									name : text
								});
							});
				});
			}

			searchViewModel.resetSearchFlag = true;
			if (searchViewModel.site != null) {
				jqm.changePage("#searchView");
			}
		};

		// Category Page End
	}
	;

	return new CategoryViewModel();

});