/*
 * Constants
 */
var RULE_PLUGIN_NAME = "Rule";
var methodName = "add";

var RULES_CREATE = function(){};

RULES_CREATE.handleSaveButton = function()
{
	var successCallback = function( arg )
	{
		console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
		alert("Operation performed successfully!");
		window.location="rules.html";
	}
	
	var errorCallback = function( arg )
	{
		console.log( "Calling the method " + methodName + " returned unsuccessfully with the arguments: " + arg );
		alert( "It was not possible to create the rule.\n\n" + arg );
	}
	
	var buildParameters = function()
	{
		var getInputValue = function( inputId )
		{
			var $input = $( inputId );
			if( $input.length == 0 ){
				throw "Could not get input with id :" + inputId;
			}
			var val = $input.val();
			
			return val;
		}
		
		var result = new Array();
		
		var id = getInputValue("input[id='inputId']");
		result.push(id);
		
		var name = getInputValue("input[id='inputName']");
		
		result.push(name);
		
		var description = getInputValue("textarea[id='inputDescription']");
		
		result.push(description);
		
		var eventId = getInputValue("select[id='eventId']");
		result.push(eventId);
		
		var actionId = getInputValue("select[id='actionId']");
		result.push(actionId);
		
		return result;
	}
	
	var parameters = buildParameters();

	console.log("Before calling cordova.exec with parameters: " + parameters );
	
	cordova.exec(
			successCallback, 
			errorCallback,
			RULE_PLUGIN_NAME,
			methodName, 
            parameters
			);
	return;
}

RULES_CREATE.init = function()
{ 	
	var $saveButton = $("button#rulesCreateSaveButton");
	$saveButton.click( RULES_CREATE.handleSaveButton );
	
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
		
	console.log(urlParams);

	var id = urlParams['id'] ;
	
	//Se tem parametros na request, entao eh update
	if( id != undefined )
	{
		methodName = "update";
		
		var $ruleLegend = $("legend[id='ruleLegend']");
		$ruleLegend.html("Edit Rule");
		
		var $inputId = $("input[id='inputId']");
		$inputId.val(id);
		
		var name = urlParams['name'];
		var $inputName = $("input[id='inputName']");
		$inputName.val( name );
		
		var description = urlParams['description'];
		var $inputDescription = $("textarea[id='inputDescription']");
		$inputDescription.val( description );
		
		var eventId = urlParams['eventId'];
		var $eventSelect = $("select[id='eventId']");
		$eventSelect.val( eventId );
		
		var actionId = urlParams['actionId'];
		var $actionSelect = $("select[id='actionId']");
		$actionSelect.val( actionId );
	}
	
	var appendOptions = function( selectId, optionValue, optionContent )
	{
		var $select = $( selectId );
		
		var optionStr = "<option value='" + optionValue + "'>" + optionContent + "</option>"; 
		
		console.log(optionStr);
		
		$select.append(optionStr);
	}
	
	cordova.exec(
			function( listAll ){
				
				var size = listAll.length;
				if(  size === 0 ){
					return;
				}
				
				for( var i = 0; i < size; i++ )
				{
					var event = jQuery.parseJSON( listAll[i] );
					var id = event.id;
					var name = event.name;
					
					appendOptions( "select[id='eventId']", id, name );
				}
				
			}, 
			function(){
				alert('error');
			},
			"Event",
			"listAll", 
            []
			);
	
	cordova.exec(
			function( listAll ){
				
				var size = listAll.length;
				if(  size === 0 ){
					return;
				}
				
				for( var i = 0; i < size; i++ )
				{
					var event = jQuery.parseJSON( listAll[i] );
					var id = event.id;
					var name = event.name;
					
					appendOptions( "select[id='actionId']", id, name );
				}
				
			}, 
			function(){
				alert('error');
			},
			"Action",
			"listAll", 
            []
			);
};

//$(document).ready( RULES_CREATE.init );
document.addEventListener("deviceready", RULES_CREATE.init, false);
