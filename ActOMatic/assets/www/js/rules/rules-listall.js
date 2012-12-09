/*
 * Constants
 */
var RULE_PLUGIN_NAME = "Rule";
var methodName = "listAll";

var RULES_LISTALL = function(){};

var successCallback = function( arg )
{
	if(arg=='undefined' || arg.length == 0 )
	{
		var msg = "There are no rules to list.";
		console.log( msg );
		$("#listAllDiv").html(msg);
		return;
	}
	
	console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
	
	var size = arg.length;
	
	var str = "<div id='all_rules'>";
	
	for( var i = 0; i < size; i++ ){
		var obj = jQuery.parseJSON( arg[i] );
		
		var objType = obj.objectType;
		var id = obj.id;
		var name = obj.name;
		var description = obj.description;
		
		var eventCommand = jQuery.parseJSON( obj.event );
		var event = jQuery.parseJSON( eventCommand.argument );
		var eventId = event.id;
		var eventName = event.name;
		
		var actionCommand = jQuery.parseJSON( obj.action );
		var action = jQuery.parseJSON(actionCommand.argument);
		var actionId = action.id;
		var actionName = action.name;
		
		var requestParams = "id=" + id;
		requestParams += "&name=" + name;
		requestParams += "&description=" + description;
		requestParams += "&eventId=" + eventId;
		requestParams += "&actionId=" + actionId;
		
			str += "<div id='rule" + id + "' ruleId='" + id + "' class='containner-fluid'>";
				str += "<div class='row-fluid rule'>";
					str += "<div class='span3 rule-description'>";
				        str += "<ul>";
						str += "<li>" + name + "</li>";
						str += "<li>" + description + "</li>";
						str += "<li>" + eventName + "</li>";
						str += "<li>" + actionName + "</li>";
		
						str += "<li><a class='btn' title='Edit' href='rules-create.html?" + requestParams + "'><i class='icon-pencil'></i></a></li>";
						str += "<li><a class='btn' title='Remove' href='rules-remove.html?" + requestParams + "'><i class='icon-trash'></i></a></li>";
						
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
	alert( "It was not possible to list all the rules.\n\n" + arg );
}

var browser = false;

RULES_LISTALL.init = function()
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
			RULE_PLUGIN_NAME,
			methodName, 
            []
			);
};

//$(document).ready( RULES_LISTALL.init );

document.addEventListener("deviceready", RULES_LISTALL.init, false);