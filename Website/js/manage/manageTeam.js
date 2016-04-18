var ManageTeam = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#ManageTeam" );
		this.name = "Gestion equipes";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}
	
	proto.init = function()
	{

	}

	proto.createTeamButton = function()
	{
		var name = $( '#createTeam-name' ).val();
		var tag = $( '#createTeam-tag' ).val();
		this.createTeam( name, tag );
	}

	proto.createTeam = function( name, tag )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		};

		_GLOBAL.apiCaller.createTeam(  name, tag , callback );
	}

	proto.getTeams = function()
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "ListTeam", data );
		};

		_GLOBAL.apiCaller.getTeams( callback );
	}

	proto.getTeamById = function( id )
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "InfoTeam", data );
		};

		_GLOBAL.apiCaller.getTeamById( id, callback );
	}

	proto.deleteTeam = function( id )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		}

		_GLOBAL.apiCaller.deleteTeam( id, callback );
	}

	proto.joinTeam = function( id )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		}

		_GLOBAL.apiCaller.joinTeam( id, callback );
	}

	proto.getMembershipsByTeam = function( id )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		}

		_GLOBAL.apiCaller.getMembershipsByTeam( id, callback );
	}

	proto.getMembershipsByTeamButton = function()
	{
		var id = $( '#getMembershipsByTeam-id' ).val();
		this.getMembershipsByTeam( id );
	}

	proto.getMembershipsByTeam = function( id )
	{
		var callback = function( data )
		{
			_GLOBAL.navigation.goTo( "ListMembership", data );
		};

		_GLOBAL.apiCaller.getMembershipsByTeam( id, callback );
	}

	proto.replyMembership = function( id, status )
	{
		var callback = function( data )
		{
			_GLOBAL.lightBoxs.openLightBox( "success");
		}

		_GLOBAL.apiCaller.replyMembership( id, status, callback );
	}

	return _construct;
}();