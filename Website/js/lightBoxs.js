var LightBoxs = function()
{
	var _construct = function()
	{
		this.currentLightBox = "";
	}

	var proto = _construct.prototype;

	proto.openLightBox = function( name )
	{
		var selector = "#lightBox-cont-" + name;
		$( selector ).css( "display", "block" );
		this.currentLightBox = name;
	}

	proto.initLightBoxs = function()
	{
		var selector = ".lightBox-cont";
		$( selector ).bind( "click", this.closeThisBox );
	}

	proto.closeThisBox = function( e )
	{
		$( e.target ).css( "display", "none" );
		this.currentLightBox = "";
	}

	return _construct;
}();