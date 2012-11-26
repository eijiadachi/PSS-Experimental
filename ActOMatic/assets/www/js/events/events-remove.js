/*
 * Constants
 */
var EVENT_PLUGIN_NAME = "Event";
var methodName = "remove";

var EVENTS_REMOVE = function(){};

var successCallback = function( arg )
{
	console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
	alert( "Removed Successfully!");
	window.location="events.html";
}

var errorCallback = function( arg )
{
	console.log( "Calling the method " + methodName + " returned unsuccessfully with the arguments: " + arg );
	alert( "It was not possible to remove the event.\n\n" + arg );
}

EVENTS_REMOVE.init = function()
{ 	
	var urlParams = {};
	(function () {
		
		var pl     = /\+/g;
		var search = /([^&=]+)=?([^&]*)/g;
		var decode = function (s) { 
			return decodeURIComponent(s.replace(pl, " ")); 
		};
		var query  = window.location.search.substring(1);
		
		var match;
		while (match = search.exec(query))
		{
			urlParams[decode(match[1])] = decode(match[2]);
		}
	})();
		
	var id = urlParams['id'] ;
	
	console.log( "Preparing to remove Event.id=" + id);
	
	cordova.exec(
		successCallback, 
		errorCallback,
		EVENT_PLUGIN_NAME,
		methodName, 
        [id]
		);
};

document.addEventListener("deviceready", EVENTS_REMOVE.init, false);
