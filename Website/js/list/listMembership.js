var ListMembership = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#ListMembership" );
		this.name = "Liste membership";
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
			list += "<div class='temp row'>";
			list +=		"<div class='text cell'>" + data[i].id + "</div>";
			list +=		"<div class='text cell'>" + data[i].team.name + "</div>";
			list +=		"<div class='text cell'>" + data[i].account.login + "</div>";
			list +=		"<div class='text cell'>" + data[i].status + "</div>";
			list +=		"<div class='text cell'><input type='button' value='accept' class='text' onclick='_GLOBAL.navigation.controller[\"ManageTeam\"].replyMembership(" + data[i].id + ", \"accepted\")'/></div>";
			list +=	"</div>";
		}

		return list;
	}

	return _construct;
}();