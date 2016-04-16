var Navigation = function()
{
	var _construct = function()
	{
		this.controller = {};
		this.controller["ManageAccount"] = new ManageAccount();
		this.controller["ManageTeam"] = new ManageTeam();
		this.controller["InfoAccount"] = new InfoAccount();
		this.controller["ListAccount"] = new ListAccount();

		this.allControllerElem = $( ".content" );
		this.pageTitle = $( "#pageTitle p" );

		this.goTo("ManageAccount");
	}

	var proto = _construct.prototype;

	proto.goTo = function( name, data )
	{
		this.cleanOnExit();
		
		this.currentController = this.controller[ name ];

		this.currentController.controllerElem.addClass( "current" );
		this.pageTitle.html( this.currentController.name );

		if( _UTILS.exists( this.currentController.init ) === true )
			this.currentController.init( data );
	}

	proto.cleanOnExit = function()
	{
		if( this.currentController )
		{
			this.currentController.controllerElem.removeClass( "current" );

			if( _UTILS.exists( this.currentController.exit ) === true )
				this.currentController.exit();

			$( this.currentController.controllerElem ).find( ".temp" ).remove();
			$( this.currentController.controllerElem ).find( ".temp-clean" ).html("");
		}
	}

	return _construct;
}();