(function () {
    'use strict';
    angular.module('app.dashboard', [])
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
                                $scope.dailyEmptyMessage = true
                                $scope.dailyErrorMessage = "No Data Found";
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
                }])
            .filter('monthName', [function () {
                    return function (monthNumber) { //1 = January
                        var monthNames = ['January', 'February', 'March', 'April', 'May', 'June',
                            'July', 'August', 'September', 'October', 'November', 'December'];
                        return monthNames[monthNumber - 1];
                    }
                }]);
})();