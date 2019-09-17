'use strict';

angular.module('myApp', [
  'ngRoute',
  'ngCookies',
  'myApp.teste',
  'myApp.login'
]).config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider.otherwise({redirectTo: '/login'});
}]);