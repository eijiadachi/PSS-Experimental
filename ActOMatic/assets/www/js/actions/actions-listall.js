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
	
	var str = "<table class='table table-bordered table-striped'>";
	str += "<thead><tr>";
	str += "<th>#</th>";
	str += "<th>Name</th>";
	str += "<th>Description</th>";
	str += "<th>Type</th>";
	str += "<th>Setting</th>";
	str += "</tr></thead>";
	
	str += "<tbody>";
	
	for( var i = 0; i < size; i++ ){
		var obj = jQuery.parseJSON( arg[i] );
		
		console.log(obj);
		
		str += "<tr>";
		
		str += "<td>" + (i+1) + "</td>";
		str += "<td>" + obj.name + "</td>";
		str += "<td>" + obj.description + "</td>";
		str += "<td>" + obj.type + "</td>";
		str += "<td>" + obj.setting + "</td>";
		
		str += "</tr>";
	}
	
	str += "</tbody></table>";
	
	$("#listAllDiv").html(str);
	console.log(str);
}

var errorCallback = function( arg )
{
	console.log( "Calling the method " + methodName + " returned unsuccessfully with the arguments: " + arg );
	alert( "It was not possible to list all the actions.\n\n" + arg );
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