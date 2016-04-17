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
		dataTeam = data.team;
		infoElem.find( ".name" ).html( dataTeam.name );
		infoElem.find( ".id" ).html( dataTeam.id );
		infoElem.find( ".tag" ).html( ""+dataTeam.tag );
	}

	return _construct;
}();