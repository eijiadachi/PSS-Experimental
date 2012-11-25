/*
 * Constants
 */
var ACTION_PLUGIN_NAME = "Action";
var methodName = "listAll";

var ACTIONS_LISTALL = function(){};

var successCallback = function( arg )
{
	console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
	
	var size = arg.length;
	
	var str = "";
	for( var i = 0; i < size; i++ ){
		str += arg[i] + "\n";
	}
	
	$("#listAllDiv").html(str);
	console.log(str);
}

var errorCallback = function( arg )
{
	console.log( "Calling the method " + methodName + " returned unsuccessfully with the arguments: " + arg );
	alert( "It was not possible to create the action.\n\n" + arg );
}

ACTIONS_LISTALL.init = function()
{ 	
	cordova.exec(
			successCallback, 
			errorCallback,
			ACTION_PLUGIN_NAME,
			"listAll", 
            []
			);
};


document.addEventListener("deviceready", ACTIONS_LISTALL.init, false);