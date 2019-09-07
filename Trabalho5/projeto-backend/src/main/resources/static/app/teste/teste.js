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
	  $scope.imagens = [
		  	{
		  		nome: "Imagem 1",
		  		caminho: "http://localhost:8080/download/service-record?param=teste2.jpg"
		  	},
		  	{
		  		nome: "Imagem 2",
		  		caminho: "http://localhost:8080/download/service-record?param=teste3.jpg"
		  	}
		  ]
    $http.get('download/service-record?param=teste2.jpg').then(function(response) {
    	$scope.testeServico = response;
//    	window.open('http://localhost:8080/download/service-record?param=..\\privado\\teste.jpg');
    });   

  });
