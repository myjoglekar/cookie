(function () {
    'use strict';
    angular.module('app.dashboard', [])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    console.log($stateParams.searchId)
                    $scope.totalPageVisitCharts = [];
                    $scope.totalSiteVisitCharts = [];
                    $scope.uniqueUserCountCharts = [];
                    $scope.getItems = function () {
                        if(!$stateParams.searchId){
                            $stateParams.searchId = 0;
                        }
                        $http.get("../admin/dashboard/dashboardTickers/" + $stateParams.searchId).success(function (response) {
                            //$scope.dashboardTickers = response;
                            angular.forEach(response, function (value, key) {
                                $scope.totalVisits = value.totalVisits;
                                $scope.totalSiteVisit = value.totalSiteVisit;
                                $scope.uniqueSiteVisit = value.uniqueSiteVisit;
                                $scope.visitedDomains = value.visitedDomains;
                                $scope.uniqueUserCount = value.uniqueUserCount;
                                $scope.formFilled = value.formFilled;
                            });
                        })
                        $http.get("../admin/dashboard/dashboardTickersYesterday/" + $stateParams.searchId).success(function (response) {
                            angular.forEach(response, function (object, key) {
                                $scope.yesterdayFormFilled = object.formFilled;
                                $scope.yesterdaySiteVisit = object.totalSiteVisit;
                                $scope.yesterdayVisits = object.totalVisits;
                                $scope.yesterdayUniqueSiteVisit = object.uniqueSiteVisit;
                                $scope.yesterdayUniqueUserCount = object.uniqueUserCount;
                                $scope.yesterdayVisitedDomains = object.visitedDomains;
                            });
                        })
                        $http.get("../admin/dashboard/byDeviceType/"+ $stateParams.searchId).success(function (response) {
                            $scope.devices = response.slice(0, 5);
                        });
                        $http.get("../admin/dashboard/topDealersByVisit/"+ $stateParams.searchId).success(function (response) {
                            $scope.dealers = response.slice(0, 5);
                        });
                        $http.get("../admin/dashboard/byLocation/"+ $stateParams.searchId).success(function (response) {
                            $scope.locations = response.slice(0, 5);
                        });
                        $http.get("datas/domain.json").success(function (response) {
                            $scope.domains = response.slice(0, 5);
                        });
                        $http.get("datas/referrer.json").success(function (response) {
                            $scope.referrers = response.slice(0, 5);
                        });
                        $http.get("datas/monthlyChange.json").success(function (response) {
                            $scope.monthlyChanges = response.slice(0, 5);
                        });
                        $http.get("datas/uniqueVisitors.json").success(function (response) {
                            $scope.uniqueVisitors = response.slice(0, 5);
                        });
                        $http.get("../admin/dashboard/byBrowser/"+ $stateParams.searchId).success(function (response) {
                            $scope.browsers = response.slice(0, 5);
                        });

                        $http.get("../admin/dashboard/hourlyVisitChart/" + $stateParams.searchId).success(function (response) {
                            angular.forEach(response, function (value, key) {
                                $scope.totalPageVisitCharts.push([value.hour, value.totalPageVisit]);
                                $scope.totalSiteVisitCharts.push([value.hour, value.totalSiteVisit]);
                                $scope.uniqueUserCountCharts.push([value.hour, value.uniqueUserCount]);
                            })
                            $scope.flotData = {
                                options: {
                                    grid: {
                                        borderColor: '#eee',
                                        borderWidth: 1,
                                        //color: '#ccc'
                                    },
                                    series: {
                                        lines: {
                                            show: true,
                                            color: 'grey'
                                        }
                                    },
                                    legend: {
                                        show: true
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
                                        label: "Page Visit",
                                        legendlabel: {
                                            color: "red"
                                        },
                                        data: $scope.totalPageVisitCharts
                                    },
                                    {
                                        shadowSize: 0,
                                        color: "#C4D186",
                                        label: "Site Visit",
                                        data: $scope.totalSiteVisitCharts
                                    },
                                    {
                                        shadowSize: 0,
                                        color: "#F0C0C0",
                                        label: "User Count",
                                        data: $scope.uniqueUserCountCharts
                                    }]
                            };
                        });

                    };
                    $scope.getItems();
                }]);
})();