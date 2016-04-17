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
		console.log( data );
		var dataMatch = data.matchPublic;
		infoElem.find( ".team1" ).html( dataMatch.team1 );
		infoElem.find( ".team2" ).html( dataMatch.team2 );
		infoElem.find( ".score1" ).html( dataMatch.score1 );
		infoElem.find( ".score2" ).html( dataMatch.score2 );
		infoElem.find( ".id" ).html( dataMatch.id );
		infoElem.find( ".round" ).html( dataMatch.round );
		infoElem.find( ".place" ).html( dataMatch.place );
	}

	return _construct;
}();