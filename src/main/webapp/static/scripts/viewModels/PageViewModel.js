/*jslint browser:true, white:true, nomen:true*/
define([ 'jquery', 'knockout', 'photoSwipe' ],

function($, ko, photoSwipe) {
	"use strict";

	function PageViewModel() {

		var self = this;
		self.images = ko.observableArray();
		self.pageTitle = ko.observable("");
		self.fullTitle = ko.observable("");
		self.pageUrl = ko.observable("");
		self.mailTo = ko.observable("");
		self.description = ko.observable("");
		
		self.pageData;

		self.activate = function() {
			// Parse Page
			//self.images.removeAll();
			//self.getPage();

		};

		self.configPage = function(obj) {
			self.images.removeAll();
			self.pageData = obj;

			self.pageTitle(obj.pageTitle);
			self.pageUrl(obj.pageUrl); 
			self.fullTitle(obj.fullTitle);
			self.mailTo(obj.mailTo);
			self.description(obj.description);
			$("#desc").val(obj.description).keyup();
			
			//self.images.removeAll();
			obj.images.forEach(function(entry) {
				self.images.push(entry);
			});
			// Photoswipe config
			var activePage = $('div.gallery-page');
			var options = {
				jQueryMobile : true
			};
			try {
				self.photoSwipeInstance = $("div.gallery a", activePage)
						.photoSwipe(options, activePage.attr('id'));
			} catch (error) {
				console.log("Error in getting photoswipe : " + error);
			}
		};
		
		self.addToFav = function() {
			commonViewModel.addToStorage(self.pageData.pageUrl, self.pageData);
		};

		$('div.gallery-page')
				.live(
						'pagehide',
						function(e) {
							// console.log("Inside page Hide");
							var currentPage = $(e.target);
							var pSwipe = window.Code.PhotoSwipe;

							if (typeof self.photoSwipeInstance != "undefined"
									&& self.photoSwipeInstance != null) {
								try {
									pSwipe.detatch(self.photoSwipeInstance);
								} catch (error) {
									console.log("Error detaching photoswipe :"
											+ error);
								}

							}

							return true;

						});
	}
	;

	return new PageViewModel();

});