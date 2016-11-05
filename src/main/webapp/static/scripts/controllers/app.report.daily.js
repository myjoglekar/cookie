/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    'use strict';
    angular.module('app.report.daily', ['angularUtils.directives.dirPagination'])
            .controller('DailyController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {
                    /*Dir pagination*/
                    // $scope.currentPage = 1;
                    $scope.count = 50;
                    $scope.total_count = 0;
                    $scope.num = 1;
                    var data = {count: $scope.count, page: $scope.page ? $scope.page : 1};
                    $scope.pageChangeHandler = function (num) {
                        data.count = 50;
                        data.page = num;
                        $http({method: 'GET', url: '../admin/dashboard/byDailyForOneMonths/' + $stateParams.searchId+ $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate, params: data}).success(function (response) {
                            $scope.dailys = response;
                            $scope.total_count = response.count;
                            console.log("Data : "+$scope.dailys)
                            console.log("Count : "+$scope.total_count)
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
})();



