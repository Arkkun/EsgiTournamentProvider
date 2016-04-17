var ListMatch = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#ListMatch" );
		this.name = "Liste matchs";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}

	proto.init = function( data )
	{
		var listElem = $( "#ListMatch .list" );
		var list = this.listFromData( data );

		listElem.append( list );
	}

	proto.listFromData = function( data )
	{
		list = "";

		for( var i = 0 ; i < data.length ; i++)
		{
			list += "<div class='temp row' onclick='_GLOBAL.navigation.controller[\"ManageMatch\"].getMatchById(" + data[i].id + ")'>";
			list +=		"<div class='text cell'>" + data[i].id + "</div>";
			list +=		"<div class='text cell'>" + data[i].round + "</div>";
			list +=		"<div class='text cell'>" + data[i].place + "</div>";
			list +=		"<div class='text cell'>" + data[i].team1 + "</div>";
			list +=		"<div class='text cell'>" + data[i].score1 + "</div>";
			list +=		"<div class='text cell'>" + data[i].team2 + "</div>";
			list +=		"<div class='text cell'>" + data[i].score2 + "</div>";
			list +=		"<div class='text cell'><input type='button' value='score' class='text' onclick='_GLOBAL.navigation.goTo(\"ManageMatch\", " + data[i].id + ")'/></div>";
			list +=	"</div>";
		}

		return list;
	}

	return _construct;
}();