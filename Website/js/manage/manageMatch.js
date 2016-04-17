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
		var id = $( '#giveMatchResult-id' ).val();
		var score = $( '#giveMatchResult-score' ).val();
		this.giveMatchResult( id, score);
	}

	proto.giveMatchResult = function( id, score )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		}

		_GLOBAL.apiCaller.giveMatchResult( id, score, callback );
	}

	return _construct;
}();