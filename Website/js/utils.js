var Utils = function()
{
	var _construct = function()
	{
		
	}

	var proto = _construct.prototype;

	proto.exists = function( variable )
	{
		if( variable !== null && variable !== undefined )
			return true;
		return false;
	}

	return _construct;
}();