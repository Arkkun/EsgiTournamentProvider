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
		for( var i = 0 ; i < data.length ; i++)
		{
			var row = 	"<div class='temp row' onclick='_GLOBAL.navigation.controller[\"ManageAccount\"].getAccountById(" + data[i].id + ")'>";
			row += 			"<div class='text cell'>" + data[i].id + "</div>";
			row += 			"<div class='text cell'>" + data[i].login + "</div>";
			row += 			"<div class='text cell'>" + data[i].admin + "</div>";
			row += 		"</div>";

			listElem.append( row );
		}
	}

	return _construct;
}();