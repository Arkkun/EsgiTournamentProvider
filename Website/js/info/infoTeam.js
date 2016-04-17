var InfoTeam = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#InfoTeam" );
		this.name = "Info team";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}

	proto.init = function( data )
	{
		var infoElem = $( "#InfoTeam" );
		infoElem.find( ".name" ).html( data.name );
		infoElem.find( ".id" ).html( data.id );
		infoElem.find( ".tag" ).html( ""+data.tag );
	}

	return _construct;
}();