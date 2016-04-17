var ListTeam = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#ListTeam" );
		this.name = "Liste teams";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}

	proto.init = function( data )
	{
		var listElem = $( "#ListTeam .list" );
		var list = this.listFromData( data );

		listElem.append( list );
	}

	proto.listFromData = function( data )
	{
		list = "";

		for( var i = 0 ; i < data.length ; i++)
		{
			list += "<div class='temp row' onclick='_GLOBAL.navigation.controller[\"ManageTeam\"].getTeamById(" + data[i].id + ")'>";
			list +=		"<div class='text cell'>" + data[i].id + "</div>";
			list +=		"<div class='text cell'>" + data[i].name + "</div>";
			list +=		"<div class='text cell'>" + data[i].tag + "</div>";
			list +=		"<div class='text cell'><input type='button' value='join' class='text' onclick='_GLOBAL.navigation.controller[\"ManageTeam\"].joinTeam(" + data[i].id + ")'/></div>";
			list +=		"<div class='text cell'><input type='button' value='del' class='text' onclick='_GLOBAL.navigation.controller[\"ManageTeam\"].deleteTeam(" + data[i].id + ")'/></div>";
			list +=	"</div>";
		}

		return list;
	}

	return _construct;
}();