'use strict';

angular.module('myApp.teste', ['ngRoute']).config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/home', {
      templateUrl: 'app/home/home.html',
      controller: 'TesteController'
    });
  }]).controller('TesteController', function($scope, $http, $cookies, $location) {	  	  
	  function load() {		  
		  $http.get('http://localhost:8080/rest/pessoas', {
				 headers: {
					 'authorization': 'Bearer ' + $cookies.get('token')
				 }			 
			 })
		       .then(function(response) {
		    	   $scope.pessoas = response.data;
		       })
		       .catch(function() {
		    	   $location.path('/login')
		       });
	  } 
	  load();	  
  });
