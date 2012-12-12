/*
 * Constants
 */
var SETTING_DIV_ID = '#settingDiv';
var NOTIFICATION_DIV_ID = '#notificationDiv';
var ACTION_PLUGIN_NAME = "Action";
var NOTIFICATION_STATUS = "NOTIFICATION";
var CONFIGURATION_STATUS = "CONFIGURATION";
var methodName = "add";

var ACTIONS_CREATE = function()
{
	this.STATUS = "";
};

ACTIONS_CREATE.showDiv = function( divId )
{
	var $div = $(divId);
	$div.show();
}

ACTIONS_CREATE.hideDiv = function( divId )
{
	var $div = $(divId);
	$div.hide();
}

ACTIONS_CREATE.handleNotificationButton = function( event )
{
	ACTIONS_CREATE.showDiv( NOTIFICATION_DIV_ID );
	ACTIONS_CREATE.hideDiv( SETTING_DIV_ID );
	ACTIONS_CREATE.STATUS = NOTIFICATION_STATUS;
}

ACTIONS_CREATE.handleConfigurationButton = function( event )
{
	ACTIONS_CREATE.showDiv( SETTING_DIV_ID );
	ACTIONS_CREATE.hideDiv( NOTIFICATION_DIV_ID );
	ACTIONS_CREATE.STATUS = CONFIGURATION_STATUS;
}

ACTIONS_CREATE.handleCancelButton = function( event )
{

}

ACTIONS_CREATE.handleSaveButton = function()
{
	var successCallback = function( arg )
	{
		console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
		alert("Operation performed successfully!");
		window.location="actions.html";
	}
	
	var errorCallback = function( arg )
	{
		console.log( "Calling the method " + methodName + " returned unsuccessfully with the arguments: " + arg );
		alert( "It was not possible to create the action.\n\n" + arg );
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
		
		result.push(ACTIONS_CREATE.STATUS);
		
		var id = getInputValue("input[id='inputId']");
		result.push(id);
		
		var name = getInputValue("input[id='inputName']");
		
		result.push(name);
		
		var description = getInputValue("textarea[id='inputDescription']");
		
		result.push(description);
		
		if( ACTIONS_CREATE.STATUS === CONFIGURATION_STATUS )
		{
			var setting = getInputValue("input[id='inputSetting']");
			result.push( setting );
			
			var settingType = getInputValue("select[id='settingType']");
			result.push( settingType );
		}
		else
		{
			var receiver = getInputValue("input[id='inputReceiver']");
			result.push(receiver);
			
			var notificationType = getInputValue("select[id='notificationType']");
			result.push(notificationType);
			
			var message = getInputValue("textarea[id='inputMessage']");
			result.push(message);
		}
		
		return result;
	}
	
	var parameters = buildParameters();

	console.log("Before calling cordova.exec with parameters: " + parameters );
	
	cordova.exec(
			successCallback, 
			errorCallback,
			ACTION_PLUGIN_NAME,
			methodName, 
            parameters
			);
	return;
}

ACTIONS_CREATE.init = function()
{ 	
	var $saveButton = $("button#actionsCreateSaveButton");
	$saveButton.click( ACTIONS_CREATE.handleSaveButton );
	
	var $cancelButton = $("button#actionsCreateCancelButton");
	$cancelButton.click( ACTIONS_CREATE.handleCancelButton );
	
	var $configurationButton = $("button[id='configurationButton']");
	$configurationButton.click( ACTIONS_CREATE.handleConfigurationButton );
	$configurationButton.removeAttr("disabled");
	$configurationButton.click();
	
	var $notificationButton = $("button[id='notificationButton']");
	$notificationButton.removeAttr("disabled");
	$notificationButton.click( ACTIONS_CREATE.handleNotificationButton );
	
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
		if(actionType === "Configuration")
		{
			var setting = urlParams['setting'];
			var $inputSetting = $("input[id='inputSetting']");
			$inputSetting.val( setting );
			
			var type = urlParams['type'];
			var $settingTypeInput = $("select[id='settingType']");
			$settingTypeInput.val(type);
			
			$configurationButton.click();
			$notificationButton.attr("disabled", "disabled");
		}
		else
		{
			var sendTo = urlParams['sendTo'];
			var $inputReceiver = $("input[id='inputReceiver']");
			$inputReceiver.val( sendTo );
			
			var type = urlParams['type'];
			var $notificationTypeInput = $("select[id='notificationType']");
			$notificationTypeInput.val(type);
			
			var message = urlParams['message'];
			var $inputMessage = $("textarea[id='inputMessage']");
			$inputMessage.val( message );
			
			$notificationButton.click();
			$configurationButton.attr("disabled", "disabled");	
		}
	}
};

//$(document).ready( ACTIONS_CREATE.init );
document.addEventListener("deviceready", ACTIONS_CREATE.init, false);
