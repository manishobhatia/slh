/*jslint browser:true, white:true, nomen:true*/
define([ 'jquery', 'knockout', 'jquery.mobile', 'viewModels/FeedViewModel' ],

function($, ko, jqm, FeedViewModel) {
	"use strict";

	return function MainViewModel() {

		var self = this;

		self.feedViewModel = new FeedViewModel();
		

	};
});
