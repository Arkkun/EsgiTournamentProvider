var Navigation = function()
{
	var _construct = function()
	{
		this.controller = {};
		this.controller["ManageAccount"] = new ManageAccount();
		this.controller["ManageTeam"] = new ManageTeam();
		this.controller["ManageMatch"] = new ManageMatch();
		this.controller["ManageTournament"] = new ManageTournament();
		this.controller["InfoAccount"] = new InfoAccount();
		this.controller["InfoTeam"] = new InfoTeam();
		this.controller["InfoMatch"] = new InfoMatch();
		this.controller["InfoTournament"] = new InfoTournament();
		this.controller["ListAccount"] = new ListAccount();
		this.controller["ListTeam"] = new ListTeam();
		this.controller["ListMatch"] = new ListMatch();
		this.controller["ListTournament"] = new ListTournament();

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
			$( this.currentController.controllerElem ).find( ".temp-html" ).html("");
			$( this.currentController.controllerElem ).find( ".temp-val" ).val("");
		}
	}

	return _construct;
}();