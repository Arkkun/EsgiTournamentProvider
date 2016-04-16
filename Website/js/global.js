var Global = function()
{
	var _construct = function()
	{
		this.body = $( "body" );

		this.navigation = new Navigation( this );
		this.session = new Session( this );
		this.apiCaller = new ApiCaller( this );
		this.lightBoxs = new LightBoxs( this );
		this.lightBoxs.initLightBoxs();
	}

	var proto = _construct.prototype;

	proto.log = function()
	{
		console.log('log');
	}

	return _construct;
}();