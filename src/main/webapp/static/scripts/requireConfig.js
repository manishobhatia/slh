(function(globals) {
	"use strict";

	/**
	 * Configures requirejs for use by portlets.
	 */
	function requireConfig() {
		require
				.config({
					paths : {
						'jquery' : '../../common/static/scripts/jquery-1.8.3.min',
						'jquery.mobile' : '../../common/static/scripts/lib/jquery.mobile-1.3.1/jquery.mobile-1.3.1',
						'json2' : '../../common/static/scripts/lib/json2',
						'knockout' : '../../common/static/scripts/lib/knockout-2.2.0',
						'textUtils' : '../../common/static/scripts/textUtils',
						'klass' : '../../common/static/scripts/lib/photoswipe-3.0.5/lib/klass.min',
						'photoSwipe' : '../../common/static/scripts/lib/photoswipe-3.0.5/code.photoswipe.jquery-3.0.5'
					},
					shim : {
						'photoSwipe': {
							deps: ['klass','jquery'],
							exports: 'photoSwipe'
						}
					}
				});
	}

	globals.MOB = globals.MOB || {};
	globals.MOB.requireConfig = requireConfig;
}(window));
