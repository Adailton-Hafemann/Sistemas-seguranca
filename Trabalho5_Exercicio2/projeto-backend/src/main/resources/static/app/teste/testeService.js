'use strict';

angular.module('myApp.teste').service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl) {
       var fd = new FormData();
       fd.append('file', file);
    
       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
       })
       .then(function() {
       })
       .catch(function() {
       });
    }
 }]);
