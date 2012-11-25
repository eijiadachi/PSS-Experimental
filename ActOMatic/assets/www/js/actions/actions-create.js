/*
 * Constants
 */
var SETTING_DIV_ID = '#settingDiv';
var NOTIFICATION_DIV_ID = '#notificationDiv';
var ACTION_PLUGIN_NAME = "Action";
var NOTIFICATION_STATUS = "NOTIFICATION";
var CONFIGURATION_STATUS = "CONFIGURATION";


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

ACTIONS_CREATE.handleSaveButton = function()
{
	var methodName = "add";
	
	var successCallback = function( arg )
	{
		console.log( "Calling the method " + methodName + " returned successfully with the arguments: " + arg );
		alert("The action was successfully created!" + arg);
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
			var val = $input.val();
			
			return val;
		}
		
		var result = new Array();
		
		result.push(ACTIONS_CREATE.STATUS);
		
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
			var receiver = getInputValue("input[id='inputReceive']");
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
	
	var $configurationButton = $("button[id='configurationButton']");
	$configurationButton.click( ACTIONS_CREATE.handleConfigurationButton );
	$configurationButton.click();
	
	var $notificationButton = $("button[id='notificationButton']");
	$notificationButton.click( ACTIONS_CREATE.handleNotificationButton );
};


document.addEventListener("deviceready", ACTIONS_CREATE.init, false);
