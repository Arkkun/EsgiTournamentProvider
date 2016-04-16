var Session = function()
{
	var _construct = function()
	{
		this.parent = parent;

		this.connected = false;
		this.pseudo = "";
		this.token = "";

		$( "body" ).addClass( "notConnected" );
	}

	var proto = _construct.prototype;

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

	proto.disconnect = function()
	{
		this.token = "";
		this.connected = false;
		_GLOBAL.body.addClass( "notConnected" );
	}

	proto.connect = function( token )
	{
		if( _UTILS.exists( token ) === true )
		{
			this.token = token;
			this.connected = true;
			_GLOBAL.body.removeClass( "notConnected" );
		}
		else
		{
			_GLOBAL.lightBoxs.openLightBox( "error");
		}
	}

	return _construct;
}();