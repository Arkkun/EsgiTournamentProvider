var ManageMatch = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#ManageMatch" );
		this.name = "Gestion matchs";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}
	
	proto.init = function( data )
	{
		this.controllerElem.find( "#giveMatchResult-id" ).val( data );
	}

	proto.getMatchs = function()
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "ListMatch", data );
		};

		_GLOBAL.apiCaller.getMatchs( callback );
	}

	proto.getMatchById = function( id )
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "InfoMatch", data );
		};

		_GLOBAL.apiCaller.getMatchById( id, callback );
	}

	proto.giveMatchResultButton = function()
	{
		var idMatch = $( '#giveMatchResult-idMatch' ).val();
		var score = $( '#giveMatchResult-score' ).val();
		var idTeam = $( '#giveMatchResult-idTeam' ).val();
		this.giveMatchResult( idMatch, score, idTeam );
	}

	proto.giveMatchResult = function( idMatch, score, idTeam )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		}

		_GLOBAL.apiCaller.giveMatchResult( idMatch, score, idTeam, callback );
	}

	return _construct;
}();