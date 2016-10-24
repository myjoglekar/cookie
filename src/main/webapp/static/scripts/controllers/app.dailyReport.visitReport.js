/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    'use strict';
    angular.module('app.dailyReport.visitReport', ['nsPopover'])
            .controller('VisitReportController', ['$scope', '$http', function ($scope, $http) {
                    $http.get('../admin/report/visitDetails').success(function(response){
                        $scope.visitReports = response;
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


