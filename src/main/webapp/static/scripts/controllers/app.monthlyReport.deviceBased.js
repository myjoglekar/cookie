/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular.module('app.monthlyReport.deviceBased', [])
            .controller('DeviceBasedController', ['$scope', '$http', function ($scope, $http) {
                    $http.get('datas/dailyReport.deviceBased.json').success(function(response){
                        $scope.devices = response;
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

