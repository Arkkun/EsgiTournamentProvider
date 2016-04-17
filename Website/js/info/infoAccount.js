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
		var dataAccount = data.accountPublic;
		infoElem.find( ".name" ).html( dataAccount.login );
		infoElem.find( ".id" ).html( dataAccount.id );
		infoElem.find( ".admin" ).html( ""+dataAccount.admin );
	}

	return _construct;
}();