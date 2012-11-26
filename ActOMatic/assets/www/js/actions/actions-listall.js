/*
 * Constants
 */
var ACTION_PLUGIN_NAME = "Action";
var methodName = "listAll";

var ACTIONS_LISTALL = function(){};

var successCallback = function( arg )
{
	if(arg=='undefined' || arg.length == 0 )
	{
		console.log( "There are no actions to list.");
		return;
	}
	
	console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
	
	var size = arg.length;
	
//	var str = "<table class='table table-bordered table-striped'>";
//	str += "<thead><tr>";
//	str += "<th>#</th>";
//	str += "<th>Name</th>";
//	str += "<th>Description</th>";
//	str += "<th>Type</th>";
//	str += "<th>Setting</th>";
//	str += "<th>Edit</th>";
//	str += "<th>Delete</th>";
//	str += "</tr></thead>";
//	
//	str += "<tbody>";
//	
//	for( var i = 0; i < size; i++ ){
//		var obj = jQuery.parseJSON( arg[i] );
//		
//		console.log(obj);
//		
//		str += "<tr>";
//		
//		str += "<td>" + (i+1) + "</td>";
//		str += "<td>" + obj.name + "</td>";
//		str += "<td>" + obj.description + "</td>";
//		str += "<td>" + obj.type + "</td>";
//		str += "<td>" + obj.setting + "</td>";
//		str += "<td><a class='btn' href='actions-edit.html?id=" + obj.id + "'><i class='icon-pencil'></i></a></td>";
//		str += "<td><a class='btn' href='actions-remove.html?id=" + obj.id + "'><i class='icon-trash'></i></a></td>";
//		
//		str += "</tr>";
//	}
//	
//	str += "</tbody></table>";
	
	var str = "<div id='all_actions'>";
	
	for( var i = 0; i < size; i++ ){
		var obj = jQuery.parseJSON( arg[i] );
		
		console.log(obj);
		
		var objType = obj.objectType;
		var id = obj.id;
		
		if( objType === 'Configuration' )
		{
			str += "<div id='action" + id + "' userId='" + id + "' class='containner-fluid'>";
				str += "<div class='row-fluid action'>";
//					str += "<div class='span1 action-image'>";
//						str += "<img class='img-rounded' src='../../img/config.png' width='45' height='45'>";
//					str += "</div>";
				
					str += "<div class='span3 action-description'>";
				        str += "<ul>";
						
						str += "<li>" + obj.name + "</li>";
						str += "<li>" + obj.description + "</li>";
						str += "<li>" + obj.type + "</li>";
						str += "<li>" + obj.setting + "</li>";
			//			str += "<li><a class='btn' title='Edit' href='actions-edit.html?id=" + obj.id + "'><i class='icon-pencil'></i></a></li>";
			//			str += "<li><a class='btn' title='Remove' href='actions-remove.html?id=" + obj.id + "'><i class='icon-trash'></i></a></li>";
						
						str += "</ul>";
					str += "</div>";
				
				str += "</div>";
			str += "</div>";
		}
		else
		{
			
		}
		
		
	}
	
	str += "</div>";
	
	$("#listAllDiv").html(str);
	
	console.log(str);
}

var errorCallback = function( arg )
{
	console.log( "Calling the method " + methodName + " returned unsuccessfully with the arguments: " + arg );
	alert( "It was not possible to list all the actions.\n\n" + arg );
}

var x = !false;

ACTIONS_LISTALL.init = function()
{ 	
if(x)	successCallback( ['{ "objectType":"Configuration", "name":"Action 01", "description":"description", "type":"SCREEN", "setting":"9,3" }',
     	                 '{ "objectType":"Configuration", "name":"Action 01", "description":"description", "type":"SCREEN", "setting":"9,3" }'] );
else
	cordova.exec(
			successCallback, 
			errorCallback,
			ACTION_PLUGIN_NAME,
			methodName, 
            []
			);
};

$(document).ready( ACTIONS_LISTALL.init );

document.addEventListener("deviceready", ACTIONS_LISTALL.init, false);