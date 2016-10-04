(function () {
    'use strict';

    angular.module('app.header', ['ngSanitize', 'selectize'])
            .controller('AppheaderCtrl', ['$scope', '$http', '$aside', '$rootScope', '$cookies', function ($scope, $http, $aside, $rootScope, $cookies) {
                    $scope.userName = $cookies.getObject("username");
                                 
                }])
          
})();
