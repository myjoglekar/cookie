(function () {
    'use strict';
    angular.module('app.dashboard', ['nsPopover'])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
//                    console.log($stateParams.searchId, $stateParams.startDate + " " + $stateParams.endDate)
                    $scope.totalPageVisitCharts = [];
                    $scope.totalSiteVisitCharts = [];
                    $scope.uniqueUserCountCharts = [];
                    $scope.getItems = function () {
                        if (!$stateParams.searchId) {
                            $stateParams.searchId = 0;
                        }
                        $http.get("../admin/dashboard/dashboardTickers/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            //$scope.dashboardTickers = response;
                            angular.forEach(response, function (value, key) {
                                $scope.totalVisits = value.totalVisits;
                                $scope.totalSiteVisit = value.totalSiteVisit;
                                $scope.uniqueSiteVisit = value.uniqueSiteVisit;
                                $scope.referrerDomains = value.referrerDomains;
                                $scope.uniqueUserCount = value.uniqueUserCount;
                                $scope.formFilled = value.formFilled;
                            });
                        });
                        $http.get("../admin/dashboard/dashboardTickersYesterday/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            angular.forEach(response, function (object, key) {
                                $scope.yesterdayFormFilled = object.formFilled;
                                $scope.yesterdaySiteVisit = object.totalSiteVisit;
                                $scope.yesterdayVisits = object.totalVisits;
                                $scope.yesterdayUniqueSiteVisit = object.uniqueSiteVisit;
                                $scope.yesterdayUniqueUserCount = object.uniqueUserCount;
                                $scope.yesterdayReferrerDomains = object.referrerDomains;
                            });
                        });
                        $http.get("../admin/dashboard/byDeviceType/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            if (response.length == 0) {
                                $scope.deviceEmptyMessage = true
                                $scope.deviceErrorMessage = "No Data Found";
                            } else {
                                $scope.devices = response.slice(0, 5);
                            }
                            console.log($scope.devices)
                        });
                        $http.get("../admin/dashboard/topDealersByVisit/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            if (response.length == 0) {
                                $scope.dealerEmptyMessage = true
                                $scope.dealerErrorMessage = "No Data Found";
                            } else {
                                $scope.dealers = response.slice(0, 5);
                            }
//                            $scope.dealers = response.slice(0, 5);
                        });
                        $http.get("../admin/dashboard/byLocation/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.locations = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.locationEmptyMessage = true
                                $scope.locationErrorMessage = "No Data Found";
                            } else {
                                $scope.locations = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byOs/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.byOs = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.osEmptyMessage = true
                                $scope.osErrorMessage = "No Data Found";
                            } else {
                                $scope.byOs = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byReferrer/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.referrers = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.referrerEmptyMessage = true
                                $scope.referrerErrorMessage = "No Data Found";
                            } else {
                                $scope.referrers = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byMonthlyForSixMonths/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.months = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.monthEmptyMessage = true
                                $scope.monthErrorMessage = "No Data Found";
                            } else {
                                $scope.months = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byDailyForOneMonths/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.dailyForMonths = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.dailyForMonthEmptyMessage = true
                                $scope.dailyForMonthErrorMessage = "No Data Found";
                            } else {
                                $scope.dailyForMonths = response.slice(0, 5);
                            }
                        });

                        $http.get("../admin/dashboard/byBrowser/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.browsers = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.browserEmptyMessage = true
                                $scope.browserErrorMessage = "No Data Found";
                            } else {
                                $scope.browsers = response.slice(0, 5);
                            }
                        });

                        $http.get("../admin/report/byFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.frequencies = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.frequencyEmptyMessage = true
                                $scope.frequencyErrorMessage = "No Data Found";
                            } else {
                                $scope.frequencies = response.slice(0, 5);
                            }
                        });

                        $http.get("../admin/dashboard/hourlyVisitChart/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            angular.forEach(response, function (value, key) {
                                $scope.totalPageVisitCharts.push([value.hour, value.totalPageVisit]);
                                $scope.totalSiteVisitCharts.push([value.hour, value.totalSiteVisit]);
                                $scope.uniqueUserCountCharts.push([value.hour, value.uniqueUserCount]);
                            });
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
                                        show: true
                                    },
                                    xaxis: {
                                        show: true
                                    }
                                },
                                data: [{
                                        shadowSize: 0,
                                        color: "#86c0ba",
                                        label: "Page",
                                        legendlabel: {
                                            color: "red"
                                        },
                                        data: $scope.totalPageVisitCharts
                                    },
                                    {
                                        shadowSize: 0,
                                        color: "#ADBD60",
                                        label: "Site",
                                        data: $scope.totalSiteVisitCharts
                                    },
                                    {
                                        shadowSize: 0,
                                        color: "#DB9090",
                                        label: "User",
                                        data: $scope.uniqueUserCountCharts
                                    }]
                            };
                        });

                    };
                    $scope.getItems();
                }])
            .filter('monthName', [function () {
                    return function (monthNumber) { //1 = January
                        var monthNames = ['January', 'February', 'March', 'April', 'May', 'June',
                            'July', 'August', 'September', 'October', 'November', 'December'];
                        return monthNames[monthNumber - 1];
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