'use strict';

angular.module('myApp.teste', ['ngRoute'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/teste', {
      templateUrl: 'app/teste/teste.html',
      controller: 'TesteController'
    });
  }])
  .controller('TesteController', function($scope, $http) {
	  $scope.testeServico = "Não Foi Respondido....";
    $http.get('products').then(function(response) {
    	$scope.testeServico = response;
    });
    }

  });
