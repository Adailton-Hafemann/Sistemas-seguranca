'use strict';

angular.module('myApp', [
  'ngRoute',
  'myApp.teste'
]).config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider.otherwise({redirectTo: '/teste'});
}]);