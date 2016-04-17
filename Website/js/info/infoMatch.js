var InfoMatch = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#InfoMatch" );
		this.name = "Info match";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}

	proto.init = function( data )
	{
		var infoElem = $( "#InfoMatch" );
		infoElem.find( ".team1" ).html( data.team1 );
		infoElem.find( ".team2" ).html( data.team2 );
		infoElem.find( ".score1" ).html( data.score1 );
		infoElem.find( ".score2" ).html( data.score2 );
		infoElem.find( ".id" ).html( data.id );
		infoElem.find( ".round" ).html( data.round );
		infoElem.find( ".place" ).html( data.place );
	}

	return _construct;
}();