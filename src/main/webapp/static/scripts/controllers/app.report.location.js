/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    'use strict';
    angular.module('app.report.location', [])
            .controller('LocationController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {
                    /*Dir pagination*/
                    // $scope.currentPage = 1;
                    $scope.count = 50;
                    $scope.total_count = 0;
                    $scope.num = 1;
                    var data = {count: $scope.count, page: $scope.page ? $scope.page : 1};
                    $scope.pageChangeHandler = function (num) {
                        data.count = 50;
                        data.page = num;
                        $http({method: 'GET', url: '../admin/dashboard/byLocation/' + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate, params: data}).success(function (response) {
                            $scope.locations = response;
                            $scope.total_count = response.count;
                            console.log("Data : " + $scope.locations)
                            console.log("Count : " + $scope.total_count)
                        });
                        console.log('reports page changed to ' + num);
                    };
                    $scope.pageChangeHandler($scope.num);
//                    if(!$stateParams.searchId){
//                            $stateParams.searchId = 0;
//                        }
//                    


                    /*Header Sortable*/
                    $scope.sort = {
                        column: '',
                        descending: false
                    };
                    $scope.changeSorting = function (column) {
                        var sort = $scope.sort;
                        if (sort.column === column) {
                            sort.descending = !sort.descending;
                        } else {
                            sort.column = column;
                            sort.descending = false;
                        }
                    };

                }])
            .filter('setDecimal', function () {
                return function (input, places) {
                    if (isNaN(input))
                        return input;
                    // If we want 1 decimal place, we want to mult/div by 10
                    // If we want 2 decimal places, we want to mult/div by 100, etc
                    // So use the following to create that factor
                    var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
                    return Math.round(input * factor) / factor;
                };
            });
})();
