'use strict';

angular.module('myApp.login', ['ngRoute']).config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/login', {
      templateUrl: 'app/login/login.html',
      controller: 'LoginController'
    });
  }]).controller('LoginController', function($scope, $http, $cookies, $location) {	  	  
	  $scope.token = "";
	$scope.logar = function() {
		var logar = {usuario: $scope.usuario, senha: $scope.senha};			
		 $http.post('http://localhost:8080/public/logar', logar)
	       .then(function(response) {
	    	   console.log(response);
	    	   $scope.token = response.data.token;
	    	   $cookies.put("token", $scope.token);	
	    	   $location.path('/home')
	       })
	       .catch(function() {
	       });
	}	

  });
