var InfoTournament = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#InfoTournament" );
		this.name = "Info tournois";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}

	proto.init = function( data )
	{
		var infoElem = this.controllerElem;
		var dataTournament = data.tournament;
		infoElem.find( ".id" ).html( dataTournament.id );
		infoElem.find( ".name" ).html( dataTournament.name );
		infoElem.find( ".description" ).html( ""+dataTournament.description );
		infoElem.find( ".date" ).html( dataTournament.date );
		infoElem.find( ".tournamentSize" ).html( ""+dataTournament.tournamentSize );
		infoElem.find( ".teamSize" ).html( ""+dataTournament.teamSize );
	}

	return _construct;
}();