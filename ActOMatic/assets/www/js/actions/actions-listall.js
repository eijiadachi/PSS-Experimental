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
		var msg = "There are no actions to list.";
		console.log( msg );
		$("#listAllDiv").html(msg);
		return;
	}
	
	console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
	
	var size = arg.length;
	
	var str = "<div id='all_actions'>";
	
	for( var i = 0; i < size; i++ ){
		var command = jQuery.parseJSON( arg[i] );
		var obj = jQuery.parseJSON( command.argument );
		
		console.log(obj);
		
		var objType = obj.objectType;
		var id = obj.id;
		var name = obj.name;
		var description = obj.description;
		var type = obj.type;
		
		var requestParams = "id=" + id;
		requestParams += "&name=" + name;
		requestParams += "&description=" + description;
		requestParams += "&type=" + type;
		requestParams += "&objType=" + objType;
		
			str += "<div id='action" + id + "' actionId='" + id + "' class='containner-fluid'>";
				str += "<div class='row-fluid action'>";
					str += "<div class='span3 action-description'>";
				        str += "<ul>";
						str += "<li>" + name + "</li>";
						str += "<li>" + description + "</li>";
						str += "<li>" + type + "</li>";
		if( objType === 'Configuration' )
		{
						var setting = obj.setting;
						requestParams += "&setting=" + setting;
						
						str += "<li>" + setting + "</li>";
		}
		else
		{
						var sendTo = obj.sendTo;
						var message = obj.message;
						requestParams += "&sendTo=" + sendTo;
						requestParams += "&message=" + message;
						str += "<li>" + sendTo + "</li>";
						str += "<li>" + message + "</li>";
		}
		
						str += "<li><a class='btn' title='Edit' href='actions-create.html?" + requestParams + "'><i class='icon-pencil'></i></a></li>";
						str += "<li><a class='btn' title='Remove' href='actions-remove.html?" + requestParams + "'><i class='icon-trash'></i></a></li>";
						
						str += "</ul>";
					str += "</div>";
				
				str += "</div>";
			str += "</div>";
	}
	
	str += "</div>";
	
	$("#listAllDiv").html(str);
}

var errorCallback = function( arg )
{
	console.log( "Calling the method " + methodName + " returned unsuccessfully with the arguments: " + arg );
	alert( "It was not possible to list all the actions.\n\n" + arg );
}

var browser = false;

ACTIONS_LISTALL.init = function()
{ 	
if(browser)	successCallback( [
     	                  '{ "id":2,"objectType":"ConfigurationAction", "name":"Action 01", "description":"description", "type":"SCREEN", "setting":"9,3" }',
     	                 '{ "id":2,"objectType":"NotificationAction", "name":"Action 01", "description":"description", "type":"SMS", "sendTo":"Eiji", "message":"Ola mundo!" }',
     	                  '{ "id":1,"objectType":"ConfigurationAction", "name":"Action 01", "description":"description", "type":"VOLUME", "setting":"9,3" }'
     	                  ] );
else
	cordova.exec(
			successCallback, 
			errorCallback,
			ACTION_PLUGIN_NAME,
			methodName, 
            []
			);
};

//$(document).ready( ACTIONS_LISTALL.init );

document.addEventListener("deviceready", ACTIONS_LISTALL.init, false);