var ManageAccount = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#ManageAccount" );
		this.name = "Gestion comptes";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}

	proto.init = function()
	{

	}

	proto.changePasswordButton = function()
	{
		var password = $( '#changePassword-password' ).val();
		this.changePassword( password);
	}
	
	proto.changePassword = function(  password )
	{
		var callback = function( data )
		{
			_GLOBAL.session.connect( data.token );
		};

		_GLOBAL.apiCaller.changePassword( password, callback );
	}

	proto.getAccounts = function()
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "ListAccount", data );
		};

		_GLOBAL.apiCaller.getAccounts( callback );
	}

	proto.getAccountById = function( id )
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "InfoAccount", data );
		};

		_GLOBAL.apiCaller.getAccountById( id, callback );
	}

	return _construct;
}();