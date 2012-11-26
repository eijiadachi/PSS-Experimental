/*
 * Constants
 */
var EVENT_PLUGIN_NAME = "Event";
var methodName = "listAll";

var EVENTS_LISTALL = function(){};

var successCallback = function( arg )
{
	if(arg=='undefined' || arg.length == 0 )
	{
		var msg = "There are no events to list.";
		console.log( msg );
		$("#listAllDiv").html(msg);
		return;
	}
	
	console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
	
	var size = arg.length;
	
	var str = "<div id='all_events'>";
	
	for( var i = 0; i < size; i++ ){
		var obj = jQuery.parseJSON( arg[i] );
		
		console.log(obj);
		
		var objType = obj.objectType;
		var id = obj.id;
		var name = obj.name;
		var description = obj.description;
		
		var requestParams = "id=" + id;
		requestParams += "&name=" + name;
		requestParams += "&description=" + description;
		requestParams += "&objType=" + objType;
		
			str += "<div id='event" + id + "' eventId='" + id + "' class='containner-fluid'>";
				str += "<div class='row-fluid event'>";
					str += "<div class='span3 event-description'>";
				        str += "<ul>";
						str += "<li>" + name + "</li>";
						str += "<li>" + description + "</li>";
		if( objType === 'RegionEvent' )
		{
						var latitude = obj.latitude;
						var longitude = obj.longitude;
						var radius = obj.radius;
						
						requestParams += "&latitude=" + latitude;
						requestParams += "&longitude=" + longitude;
						requestParams += "&radius=" + radius;
						
						str += "<li>" + latitude + "</li>";
						str += "<li>" + longitude + "</li>";
						str += "<li>" + radius + "</li>";
		}
		else
		{
						var date = obj.date;
						var time = obj.time;
						requestParams += "&date=" + date;
						requestParams += "&time=" + time;
						str += "<li>" + date + "</li>";
						str += "<li>" + time + "</li>";
		}
		
						str += "<li><a class='btn' title='Edit' href='events-create.html?" + requestParams + "'><i class='icon-pencil'></i></a></li>";
						str += "<li><a class='btn' title='Remove' href='events-remove.html?" + requestParams + "'><i class='icon-trash'></i></a></li>";
						
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
	alert( "It was not possible to list all the events.\n\n" + arg );
}

var x = false;

EVENTS_LISTALL.init = function()
{ 	
if(x)	successCallback( [
     	                  '{ "id":2,"objectType":"ConfigurationAction", "name":"Action 01", "description":"description", "type":"SCREEN", "setting":"9,3" }',
     	                 '{ "id":2,"objectType":"NotificationAction", "name":"Action 01", "description":"description", "type":"SMS", "sendTo":"Eiji", "message":"Ola mundo!" }',
     	                  '{ "id":1,"objectType":"ConfigurationAction", "name":"Action 01", "description":"description", "type":"VOLUME", "setting":"9,3" }'
     	                  ] );
else
	cordova.exec(
			successCallback, 
			errorCallback,
			EVENT_PLUGIN_NAME,
			methodName, 
            []
			);
};

//$(document).ready( EVENTS_LISTALL.init );

document.addEventListener("deviceready", EVENTS_LISTALL.init, false);