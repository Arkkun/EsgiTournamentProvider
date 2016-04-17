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

	proto.authenticateButton = function()
	{
		var login = $( '#header-login' ).val();
		var password = $( '#header-password' ).val();
		this.authenticate( login, password);
	}

	proto.authenticate = function( login, password )
	{
		var callback = function( data )
		{
			_GLOBAL.session.connect( data.token );
		};

		_GLOBAL.apiCaller.authenticate( login, password,  callback );
	}

	proto.registerButton = function()
	{
		var login = $( '#header-login' ).val();
		var password = $( '#header-password' ).val();
		this.register( login, password);
	}
	
	proto.register = function( login, password )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		};

		_GLOBAL.apiCaller.register( login, password, callback );
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

	proto.getMyAccount = function(  )
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "InfoAccount", data );
		};

		_GLOBAL.apiCaller.getMyAccount( callback );
	}


	return _construct;
}();