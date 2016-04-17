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