/*jslint browser:true, white:true, nomen:true*/

(function(globals) {
	"use strict";
	globals.MOB.requireConfig();

	require(
			[ 'jquery', 'knockout', 'jquery.mobile',
					'viewModels/CategoryViewModel',
					'viewModels/SearchViewModel', 'viewModels/ListViewModel',
					'viewModels/PageViewModel', 'viewModels/CommonViewModel' ],

			function($, ko, jqm, CategoryViewModel, SearchViewModel,
					ListViewModel, PageViewModel, CommonViewModel) {

				$(function() {
				//	$.mobile.loading('show');
					$.mobile.autoInitializePage = false;
				//	navigator.splashscreen.show();
					var self = this;

					globals.categoryViewModel = CategoryViewModel;
					globals.searchViewModel = SearchViewModel;
					globals.listViewModel = ListViewModel;
					globals.pageViewModel = PageViewModel;
					globals.commonViewModel = CommonViewModel;

					$(window)
							.bind(
									"pagechange",
									function(e, info) {

										var page = info.toPage[0];
										// get view model name
										var viewModelName = info.toPage
												.attr("data-viewmodel");

										if (viewModelName) {

											// get view model object
											var viewModel = globals[viewModelName];

											// apply bindings if they are not
											// yet applied
											if (!ko.dataFor(page)) {
												// console.log("Applying
												// binding");
												ko.applyBindings(viewModel,
														page);
											}

											// call activate on view model if
											// implemented
											if (viewModel
													&& viewModel.activate
													&& typeof viewModel.activate === "function") {
												viewModel.activate();
											}
										}

									});

					$.mobile.initializePage();
					$.mobile.hidePageLoadingMsg();
				//	navigator.splashscreen.hide();
					
				});

			});
}(window));