var ListTournament = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#ListTournament" );
		this.name = "Liste tournois";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}

	proto.init = function( data )
	{
		var listElem = this.controllerElem.find( ".list" );
		var list = this.listFromData( data );

		listElem.append( list );
	}

	proto.listFromData = function( data )
	{
		list = "";

		for( var i = 0 ; i < data.length ; i++)
		{
			list += "<div class='temp row' onclick='_GLOBAL.navigation.controller[\"ManageTournament\"].getTournamentById(" + data[i].id + ")'>";
			list +=		"<div class='text cell'>" + data[i].id + "</div>";
			list +=		"<div class='text cell'>" + data[i].name + "</div>";
			list +=		"<div class='text cell'>" + data[i].description + "</div>";
			list +=		"<div class='text cell'>" + data[i].date + "</div>";
			list +=		"<div class='text cell'>" + data[i].tournamentSize + "</div>";
			list +=		"<div class='text cell'>" + data[i].teamSize + "</div>";
			list +=		"<div class='text cell'><input type='button' value='launch' class='text' onclick='_GLOBAL.navigation.controller[\"ManageTournament\"].launchTournament(" + data[i].id + ")'/></div>";
			list +=	"</div>";
		}

		return list;
	}

	return _construct;
}();