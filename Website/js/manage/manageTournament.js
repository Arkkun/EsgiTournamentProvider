var ManageTournament = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#ManageTournament" );
		this.name = "Gestion tournois";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}
	
	proto.init = function()
	{

	}

	proto.getTournaments = function()
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "ListTournament", data );
		};

		_GLOBAL.apiCaller.getTournaments( callback );
	}

	proto.getTournamentById = function( id )
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "InfoTournament", data );
		};

		_GLOBAL.apiCaller.getTournamentById( id, callback );
	}

	proto.createTournamentButton = function()
	{
		var name = $( '#createTournament-name' ).val();
		var description = $( '#createTournament-description' ).val();
		var date = $( '#createTournament-date' ).val();
		var tournamentSize = parseInt( $( '#createTournament-tournamentSize' ).val() );
		var teamSize = parseInt( $( '#createTournament-teamSize' ).val() );
		this.createTournament( name, description, date, tournamentSize, teamSize );
	}

	proto.createTournament = function( name, description, date, tournamentSize, teamSize )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		};

		_GLOBAL.apiCaller.createTournament(  name, description, date, tournamentSize, teamSize , callback );
	}

	proto.joinTournamentButton = function()
	{
		var idTournament = $( '#joinTournament-idTournament' ).val();
		var idTeam = $( '#joinTournament-idTeam' ).val();
		this.joinTournament( idTournament, idTeam );
	}

	proto.joinTournament = function( idTournament, idTeam )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		};

		_GLOBAL.apiCaller.joinTournament( idTournament, idTeam, callback );
	}

	proto.launchTournament = function( idTournament )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		};

		_GLOBAL.apiCaller.launchTournament( idTournament, callback );
	}

	/*
	proto.createTeamButton = function()
	{
		var name = $( '#createTournament-name' ).val();
		var tag = $( '#createTeam-tag' ).val();
		this.createTeam( name, tag );
	}

	


	

	proto.joinTeam = function( id )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		}

		_GLOBAL.apiCaller.joinTeam( id, callback );
	}
	*/

	return _construct;
}();