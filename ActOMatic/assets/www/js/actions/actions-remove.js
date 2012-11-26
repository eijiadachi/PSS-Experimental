/*
 * Constants
 */
var ACTION_PLUGIN_NAME = "Action";
var methodName = "remove";

var ACTIONS_REMOVE = function(){};

var successCallback = function( arg )
{
	console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
	alert( "Removed Successfully!");
	window.location="actions.html";
}

var errorCallback = function( arg )
{
	console.log( "Calling the method " + methodName + " returned unsuccessfully with the arguments: " + arg );
	alert( "It was not possible to remove the action.\n\n" + arg );
}

ACTIONS_REMOVE.init = function()
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
	
	console.log( "Preparing to remove Action.id=" + id);
		
	cordova.exec(
		successCallback, 
		errorCallback,
		ACTION_PLUGIN_NAME,
		methodName, 
        [id]
		);
};

document.addEventListener("deviceready", ACTIONS_REMOVE.init, false);
