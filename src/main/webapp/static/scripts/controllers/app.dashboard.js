(function () {
    'use strict';
    angular.module('app.dashboard', [])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http',
                function ($scope, $location, toaster, $http) {

                    $scope.getItems = function () {
                        $http.get("../admin/dashboard/dashboardTickers").success(function(response){
                            $scope.dashboardTickers = response;
                        })
                        $http.get("../admin/dashboard/byDeviceType").success(function (response) {
                            $scope.devices = response.slice(0,5);
                        });
                        $http.get("../admin/dashboard/topDealersByVisit").success(function (response) {
                            $scope.dealers = response.slice(0,5);
                        });
                        $http.get("../admin/dashboard/byLocation").success(function (response) {
                            $scope.locations = response.slice(0,5);
                        });
                        $http.get("datas/domain.json").success(function (response) {
                            $scope.domains = response.slice(0,5);
                        });
                        $http.get("datas/referrer.json").success(function (response) {
                            $scope.referrers = response.slice(0,5);
                        });
                        $http.get("datas/monthlyChange.json").success(function (response) {
                            $scope.monthlyChanges = response.slice(0,5);
                        });
                        $http.get("datas/uniqueVisitors.json").success(function (response) {
                            $scope.uniqueVisitors = response.slice(0,5);
                        });
                        
                    };
                    $scope.getItems();
                    
                    $scope.flotData = {
                        options: {
                            grid: {
                                borderColor: '#eee',
                                borderWidth: 1,
                                color: '#ccc'
                            },
                            series: {
                                lines: {
                                    show: true,
                                    color: 'grey'
                                }
                            },
                            legend: {
                                show: false
                            },
                            yaxis: {
                                //show: false
                            },
                            xaxis: {
                                //show: false
                            }
                        },
                        data: [{
                                shadowSize: 0,
                                color: "#b6f0ea",
                                data: [[1, 5], [2, 20], [3, 30], [4, 23], [5, 18], [6, 6], [7, 0], [8, 10], [9, 10], [10, 15], [11, 10], [12, 20], [13, 10], [14, 40], [15, 10], [16, 15], [17, 10], [18, 2]]
                            },
                            {
                                shadowSize: 0,
                                color: "#e2f2fd",
                                data: [[1, 1], [2, 2], [3, 5], [4, 7], [5, 4], [6, 13], [7, 15], [8, 6], [9, 5], [10, 7], [11, 5], [12, 10], [13, 5], [14, 20], [15, 5], [16, 7], [17, 5], [18, 1]]
                            }]
                    };

                }]);
})();