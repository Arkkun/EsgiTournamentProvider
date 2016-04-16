var ApiCaller = function()
{
	var _construct = function()
	{
		this.baseURL = "http://localhost:8080/";
		this.curl = "http://localhost:8888/php/curl.php";
	}

	var proto = _construct.prototype;

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
				if( _UTILS.exists( data["exception"] ) === true ) 
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

	proto.authenticate = function( login, password, callback )
	{
		var url = this.baseURL + "accounts/authenticate";

		var method = "POST";

		var data = 
		{
			"login":login
			,"password":password
		};

		this.myAjax( url, method, data, callback );		
	}


	proto.register = function( login, password, callback )
	{
		var url = this.baseURL + "accounts/";

		var method = "POST";

		var data = 
		{
			"login":login
			,"password":password
		};

		this.myAjax( url, method, data, callback );
	}

	proto.changePassword = function( password, callback )
	{
		var url = this.baseURL + "accounts/password";

		var method = "POST";

		var data = 
		{
			"body": password
			,"token": _GLOBAL.session.token
		};

		this.myAjax( url, method, data, callback );
	}

	proto.getAccounts = function( callback )
	{
		var url = this.baseURL + "accounts/";

		var method = "GET";

		var data = null ;

		this.myAjax( url, method, data, callback );
	}

	proto.getAccountById = function( id, callback )
	{
		var url = this.baseURL + "accounts/" + id;

		var method = "GET";

		var data = null ;

		this.myAjax( url, method, data, callback );
	}

	return _construct;
}();