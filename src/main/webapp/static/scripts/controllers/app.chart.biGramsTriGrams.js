(function () {
    'use strict';

    angular.module('app.biTriGrams', ['angularUtils.directives.dirPagination'])
            .controller('BiTriGramsController', function ($scope, $filter, $http, $templateCache, $timeout, SearchKeywords) {

                /*Bi-Grams*/
                $http.get('../admin/dashboard/biGrams/' + SearchKeywords.keyword).success(function (response) {
                    $scope.biGrams = response;
                    console.log($scope.biGrams);
                });
                
                $http.get('../admin/dashboard/triGrams/' + SearchKeywords.keyword).success(function (response) {
                    $scope.triGrams = response;
                    console.log($scope.triGrams);
                });
            });
            
})();
