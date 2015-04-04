/*jslint browser:true, white:true, nomen:true*/
define([ 'jquery', 'knockout', 'jquery.mobile' ],

function($, ko, jqm) {
	"use strict";

	function CommonViewModel() {

		var self = this;

		//self.serverUrl = "http://localhost:9080/slh/";
		 self.serverUrl = "http://slh-m0b.rhcloud.com/slh/";

		self.supports_html5_storage = function() {
			try {
				return 'localStorage' in window
						&& window['localStorage'] !== null;
			} catch (e) {
				return false;
			}
		};

		self.supports_json = function() {
			try {
				return 'JSON' in window && window['JSON'] !== null;
			} catch (e) {
				return false;
			}
		};

		// Adding to storage
		self.addToStorage = function(id, label) {
			if (!self.hasInStorage(id)) {
				var data = self.getStorage();
				data[id] = label;
				self.saveStorage(data);
			}
		};

		// loading from storage
		self.getStorage = function() {
			var current = localStorage["favorites"];
			var data = {};
			if (typeof current != "undefined")
				data = window.JSON.parse(current);
			return data;
		};

		// Checking storage
		self.hasInStorage = function(id) {
			return (id in self.getStorage());
		};

		// Adding to storage
		self.removeFromStorage = function(id, label) {
			if (self.hasInStorage(id)) {
				var data = self.getStorage();
				delete data[id];
				console.log('removed ' + id);
				self.saveStorage(data);
			}
		};

		// save storage
		self.saveStorage = function(data) {
			console.log("To store...");
			console.dir(data);
			localStorage["favorites"] = window.JSON.stringify(data);
		};

	}
	;

	return new CommonViewModel();
});
