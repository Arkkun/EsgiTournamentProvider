var InfoAccount = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#InfoAccount" );
		this.name = "Info compte";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}

	proto.init = function( data )
	{
		var infoElem = $( "#InfoAccount" );
		infoElem.find( ".name" ).html( data.login );
		infoElem.find( ".id" ).html( data.id );
		infoElem.find( ".admin" ).html( ""+data.admin );
	}

	return _construct;
}();