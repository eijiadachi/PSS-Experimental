/*
 * Constants
 */
var REGION_DIV_ID = '#regionDiv';
var TIMER_DIV_ID = '#timerDiv';
var EVENT_PLUGIN_NAME = "Event";
var REGION_STATUS = "REGION";
var TIMER_STATUS = "TIMER";
var methodName = "add";

var EVENTS_CREATE = function()
{
	this.STATUS = "";
};

EVENTS_CREATE.showDiv = function( divId )
{
	var $div = $(divId);
	$div.show();
}

EVENTS_CREATE.hideDiv = function( divId )
{
	var $div = $(divId);
	$div.hide();
}

EVENTS_CREATE.handleTimerButton = function( event )
{
	EVENTS_CREATE.showDiv( TIMER_DIV_ID );
	EVENTS_CREATE.hideDiv( REGION_DIV_ID );
	EVENTS_CREATE.STATUS = TIMER_STATUS;
}

EVENTS_CREATE.handleRegionButton = function( event )
{
	EVENTS_CREATE.showDiv( REGION_DIV_ID );
	EVENTS_CREATE.hideDiv( TIMER_DIV_ID );
	EVENTS_CREATE.STATUS = REGION_STATUS;
}

EVENTS_CREATE.handleSaveButton = function()
{
	var successCallback = function( arg )
	{
		console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
		alert("Operation performed successfully!");
		window.location="events.html";
	}
	
	var errorCallback = function( arg )
	{
		console.log( "Calling the method " + methodName + " returned unsuccessfully with the arguments: " + arg );
		alert( "It was not possible to create the event.\n\n" + arg );
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
		
		result.push(EVENTS_CREATE.STATUS);
		
		var id = getInputValue("input[id='inputId']");
		result.push(id);
		
		var name = getInputValue("input[id='inputName']");
		
		result.push(name);
		
		var description = getInputValue("textarea[id='inputDescription']");
		
		result.push(description);
		
		if( EVENTS_CREATE.STATUS === REGION_STATUS )
		{
			var latitude = getInputValue("input[id='inputLatitude']");
			result.push( latitude );
			
			var longitude = getInputValue("input[id='inputLongitude']");
			result.push( longitude );
			
			var radius = getInputValue("input[id='inputRadius']");
			result.push( radius );
		}
		else
		{
			var date = getInputValue("input[id='inputDate']");
			result.push(date);
			
			var time = getInputValue("input[id='inputTime']");
			result.push(time);
		}
		
		return result;
	}
	
	var parameters = buildParameters();

	console.log("Before calling cordova.exec with parameters: " + parameters );
	
	cordova.exec(
			successCallback, 
			errorCallback,
			EVENT_PLUGIN_NAME,
			methodName, 
            parameters
			);
	return;
}

EVENTS_CREATE.init = function()
{ 	
	var $saveButton = $("button#eventsCreateSaveButton");
	$saveButton.click( EVENTS_CREATE.handleSaveButton );
	
	var $regionButton = $("button[id='regionButton']");
	$regionButton.click( EVENTS_CREATE.handleRegionButton );
	$regionButton.removeAttr("disabled");
	$regionButton.click();
	
	var $timerButton = $("button[id='timerButton']");
	$timerButton.removeAttr("disabled");
	$timerButton.click( EVENTS_CREATE.handleTimerButton );
	
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
		
		var $actionLegend = $("legend[id='actionLegend']");
		$actionLegend.html("Edit Action");
		
		var $inputId = $("input[id='inputId']");
		$inputId.val(id);
		
		var name = urlParams['name'];
		var $inputName = $("input[id='inputName']");
		$inputName.val( name );
		
		var description = urlParams['description'];
		var $inputDescription = $("textarea[id='inputDescription']");
		$inputDescription.val( description );
		
		var actionType = urlParams['objType'];
		if(actionType === "ConfigurationAction")
		{
			var setting = urlParams['setting'];
			var $inputSetting = $("input[id='inputSetting']");
			$inputSetting.val( setting );
			
			var type = urlParams['type'];
			var $settingTypeInput = $("select[id='settingType']");
			$settingTypeInput.val(type);
			
			$regionButton.click();
			$timerButton.attr("disabled", "disabled");
		}
		else
		{
			var sendTo = urlParams['sendTo'];
			var $inputReceiver = $("input[id='inputReceiver']");
			$inputReceiver.val( sendTo );
			
			var type = urlParams['type'];
			var $timerTypeInput = $("select[id='timerType']");
			$timerTypeInput.val(type);
			
			var message = urlParams['message'];
			var $inputMessage = $("textarea[id='inputMessage']");
			$inputMessage.val( message );
			
			$timerButton.click();
			$regionButton.attr("disabled", "disabled");	
		}
	}
};

//$(document).ready( EVENTS_CREATE.init );
document.addEventListener("deviceready", EVENTS_CREATE.init, false);
