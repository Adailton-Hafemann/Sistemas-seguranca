'use strict';

angular.module('myApp.teste', ['ngRoute']).config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/teste', {
      templateUrl: 'app/teste/teste.html',
      controller: 'TesteController'
    });
  }]).controller('TesteController', function($scope, $http, fileUpload) {	  
	  $scope.testeServico = "Não Foi Respondido....";
//	  $scope.imagens = [
//		  	{
//		  		nome: "Imagem 1",
//		  		caminho: "http://localhost:8080/download/service-record?param=teste2.jpg"
//		  	},
//		  	{
//		  		nome: "Imagem 2",
//		  		caminho: "http://localhost:8080/download/service-record?param=teste3.jpg"
//		  	}
//		  ]
	  function load() {
		  $http.get('download/list').then(function(response) {
		    	$scope.imagens = [];
		    	var lista = response.data;
		    	if (lista) {
		    		debugger;
		    		for (var i = 0; i < lista.length; i++) {
		    			var cami = "http://localhost:8080/download/service-record?param=" + lista[i].id;
		    			$scope.imagens.push({nome: lista[i].nome, caminho:cami})
		    			console.log($scope.imagens)
		    		}
		    		console.log($scope.imagens)
		    	}
		    	console.log($scope.imagens)
		    	$scope.testeServico = response;    	
		    });
	  } 
	  load();
	  
	$scope.criaCaminho = function() {
		//$http.get('upload/service-record?param=teste2.jpg').then(function(response) {
	    //	$scope.testeServico = response;
//	    	window.open('http://localhost:8080/download/service-record?param=..\\privado\\teste.jpg');
	    //});	
	}
	  
	  $scope.uploadFile = function() {
          
          var file = $scope.myFile;
          console.log('file is ' );
          console.dir(file);4
          var uploadUrl = "upload/service-record";
          fileUpload.uploadFileToUrl(file, uploadUrl);
          setTimeout(function(){
        	  load();
          }, 200);
       };

  });
