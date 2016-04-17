var ListAccount = function()
{
	var _construct = function()
	{
		this.controllerElem = $( "#ListAccount" );
		this.name = "Liste comptes";
	}

	var proto = _construct.prototype;

	proto.exit = function()
	{

	}

	proto.init = function( data )
	{
		var listElem = $( "#ListAccount .list" );
		var list = this.listFromData( data );

		listElem.append( list );
	}

	proto.listFromData = function( data )
	{
		list = "";

		for( var i = 0 ; i < data.length ; i++)
		{
			list += "<div class='temp row' onclick='_GLOBAL.navigation.controller[\"ManageAccount\"].getAccountById(" + data[i].id + ")'>";
			list +=		"<div class='text cell'>" + data[i].id + "</div>";
			list +=		"<div class='text cell'>" + data[i].login + "</div>";
			list +=		"<div class='text cell'>" + data[i].admin + "</div>";
			list +=	"</div>";
		}

		return list;
	}

	return _construct;
}();