'use strict';

angular.module('myApp.login', ['ngRoute']).config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/login', {
      templateUrl: 'app/login/teste.html',
      controller: 'LoginController'
    });
  }]).controller('LoginController', function($scope, $http, fileUpload) {	  
	  $scope.testeServico = "Não Foi Respondido....";
 
	$scope.logar = function() {
		var logar = {usuario: "ada", senha: "ada"};
		var param = {login: logar}		
		debugger;
		 $http.post('http://localhost:8080/logar/usuario', logar)
	       .then(function(response) {
	    	   console.log(response);
	       })
	       .catch(function() {
	       });
	}	

  });
