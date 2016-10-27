/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular.module('app.dailyReport.timeOnSiteReport', [])
            .controller('TimeOnSiteReportController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {
                    
                    if(!$stateParams.searchId){
                            $stateParams.searchId = 0;
                        }
                    $http.get('../admin/report/timeOnSiteReports/' + $stateParams.searchId).success(function (response) {
                        $scope.timeOnSiteReports = response;
                    });

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


