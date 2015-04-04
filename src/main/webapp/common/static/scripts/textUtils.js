/*jslint browser:true, white:true, nomen:true*/
define(
	['underscore'],

	function(_) {
		"use strict";

		/**
		 * Truncates a string so that it only contains the first <len>
		 * characters. The truncated string is then suffixed with "..." by
		 * default.
		 * @param str
		 * @param len
		 * @param suffix (optional) default is "..."
		 */
		function truncate(str, len, suffix) {
			var suff = (_.isUndefined(suffix) || _.isNull(suffix)) ? '...' : suffix;

			if (!_.isString(str)) {
				return str;
			}

			if (str.length <= len) {
				return str;
			}

			return str.slice(0, len) + suff;
		}

		/**
		 * Pad a string with leading zeros
		 *
		 * @param num the number to pad
		 * @param places length of string
		 */
		function zeroPad(number, pad) {
			var N = Math.pow(10, pad);
			return number < N ? (String("") + (N + number)).slice(1) : String("") + number;
		}


		/**
		 * Format minutes as minutes and seconds
		 */
		function formatMinutes(seconds) {
			return (seconds < 60 ? "0"  : parseInt(seconds/60, 10)) + ":" + zeroPad(seconds%60,2);
		}

		/** Properly Capitalize a string of words
		*/
		function capitalize(s) {
			return s.toLowerCase().replace( /\b./g, function(a){ return a.toUpperCase(); } );
		}


		function prettyString(string, args) {
			var i, tokens;

			if(args===undefined) {
				return string;
			}

			if (typeof args === "string") {
				args = [args];
			}

			tokens = string.match(/\{\d+\}/g);
			if(tokens!==null) {
				for (i=0;i<tokens.length;i+=1) {
					string = string.replace(tokens[i], args[tokens[i].match(/\d+/)]);
				}
			}
			return string;
		}


		return {
			truncate: truncate,
			formatMinutes: formatMinutes,
			zeroPad: zeroPad,
			capitalize: capitalize,
			prettyString: prettyString
		};
	}

);
