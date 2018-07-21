var app = angular.module('app' , ['ngRoute']);
  app.config(function ($routeProvider,$locationProvider) {
	console.log("ctrl app");
	
	.when('/chat', {
		templateUrl : 'chat.html',
		controller: 'pageCtrl'	       	
	})
	.when('/login', {
		templateUrl : 'login.html',
        controller: 'loginCtrl'
       	
	})
	.otherwise({
	redirectTo : '/login'		
	});
  
  });
  
  //--------------------------login controller
  app.controller('loginCtrl', function($scope,$window, $http) {
	 	console.log("ctrl login");
	 	 $scope.authentifier = function() {
				$http.get("/authentifier?login="+$scope.login+"&psw="+$scope.psw)
				.success(function(data) {
					if(data!="")
					{	
						localStorage.setItem("user",JSON.stringify( data));

						var url = "http://" + $window.location.host + "/chat";
				        $window.location.href = url;
					}
						else 
						{ bootbox.alert("utilisateur not found"); }
			    
				})
				.error(function(error)
				{ bootbox.alert("utilisateur not found"); }		
				)
				;
			}; 	
	});
 
  //----------------------------page controller
  
  app.controller('pageCtrl', function($scope,$window, $http) {
		$scope.user = JSON.parse(localStorage.getItem('user'));
		
		$scope.logout = function(){
	 		$http.get("/logout")
	 	    .success(function(data) {
	 	    console.log("logout success");
	 	    
	 	    var url = "http://" + $window.location.host + "/login";
	 	    $window.location.href = url;
	 	    
	 	    });
	 	}
		
	
	 	
	});
  