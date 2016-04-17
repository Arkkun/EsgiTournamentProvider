var ApiCaller = function()
{
	var _construct = function()
	{
		this.baseURL = "http://localhost:8080/";
		this.curl = "http://localhost:8888/php/curl.php";
	}

	var proto = _construct.prototype;

	/*===== Account =====*/
	proto.authenticate = function( login, password, callback )
	{
		var url = this.baseURL + "account/authenticate";
		var method = "POST";
		var data = {
			"login":login
			,"password":password
		};
		this.myAjax( url, method, data, callback );		
	}


	proto.register = function( login, password, callback )
	{
		var url = this.baseURL + "account/";
		var method = "POST";
		var data = {
			"login":login
			,"password":password
		};
		this.myAjax( url, method, data, callback );
	}

	proto.changePassword = function( password, callback )
	{
		var url = this.baseURL + "account/password";
		var method = "POST";
		var data = {
			"body": password
			,"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	proto.getAccounts = function( callback )
	{
		var url = this.baseURL + "account/";
		var method = "GET";
		var data = null ;
		this.myAjax( url, method, data, callback );
	}

	proto.getAccountById = function( id, callback )
	{
		var url = this.baseURL + "account/" + id;
		var method = "GET";
		var data = null ;
		this.myAjax( url, method, data, callback );
	}

	proto.getMyAccount = function( callback )
	{
		var url = this.baseURL + "account/my-account";
		var method = "POST";
		var data = {
			"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	/*===== Team =====*/

	proto.createTeam = function( name, tag, callback )
	{
		var url = this.baseURL + "team/";
		var method = "POST";
		var data = {
			"body":{
			"name":name
			,"tag":tag
			}
			,"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	proto.deleteTeam = function( id, callback )
	{
		var url = this.baseURL + "team/" + id;
		var method = "DELETE";
		var data = {
			"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	proto.getTeams = function( callback )
	{
		var url = this.baseURL + "team/";
		var method = "GET";
		var data = null ;
		this.myAjax( url, method, data, callback );
	}


	proto.getTeamById = function( id, callback )
	{
		var url = this.baseURL + "team/" + id;
		var method = "GET";
		var data = null ;
		this.myAjax( url, method, data, callback );
	}

	proto.joinTeam = function( id, callback )
	{
		var url = this.baseURL + "team/" + "membership/" + id;
		var method = "POST";
		var data = {
			"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	proto.getMembershipsByTeam = function( id, callback )
	{
		var url = this.baseURL + "team/" + "membership/list" + id;
		var method = "PUT";
		var data = {
			"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	proto.replyMembership = function( id, status, callback )
	{
		var url = this.baseURL + "team/" + "membership/" + id;
		var method = "PUT";
		var data = {
			"body": status
			,"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	/*===== Tournament =====*/

	proto.createTournament = function( name, description, date, tournamentSize, teamSize, callback )
	{
		var url = this.baseURL + "tournament/";
		var method = "POST";
		var data = {
			"body":{
			"name":name
			,"description":description
			,"date":date
			,"tournamentSize":tournamentSize
			,"teamSize":teamSize
			}
			,"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	proto.getTournamentById = function( id, callback )
	{
		var url = this.baseURL + "tournament/" + id;
		var method = "GET";
		var data = null ;
		this.myAjax( url, method, data, callback );
	}

	proto.launchTournament = function( id, callback )
	{
		var url = this.baseURL + "team/" + id;
		var method = "PUT";
		var data = {
			"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	proto.joinTournament = function( idTournament, idTeam, callback )
	{
		var url = this.baseURL + "tournament/" + idTournament + "/join/" + idTeam;
		var method = "POST";
		var data = {
			"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	/*===== Match =====*/

	proto.getMatchById = function( id, callback )
	{
		var url = this.baseURL + "match/" + id;
		var method = "GET";
		var data = null ;
		this.myAjax( url, method, data, callback );
	}

	proto.giveMatchResult = function( id, score, callback )
	{
		var url = this.baseURL + "match/" + id;
		var method = "PUT";
		var data = {
			"body": score
			,"token": _GLOBAL.session.token
		};
		this.myAjax( url, method, data, callback );
	}

	/*===== Ajax =====*/

	proto.myAjax = function( url, method, data, callback )
	{
		var requestUrl = this.curl + "?url=" + url + "&method=" + method;
		if( _UTILS.exists( data ) === true )
		{
			requestUrl += "&data=" + JSON.stringify( data );
		}
		requestUrl = encodeURI( requestUrl  );

		$.ajax(
		{
			url:requestUrl
			,data:""
			,type :"GET"
			,async: true
			,dataType:"json"
			,success : function( data )
			{
				if( _UTILS.exists( data["error"] ) === true ) 
				{
					this.error( data );
				}
				else
				{
					if( _UTILS.exists( callback ) === true )
					{
						callback( data );
					}
					console.log("success", data);
				}
			}
			,error: function( data )
			{
				_GLOBAL.lightBoxs.openLightBox( "error" );
				console.log("error", data);
			}
		});
	}

	return _construct;
}();