(function () {
    'use strict';
    angular.module('app.report.report', ['nsPopover'])
            .controller('ReportCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
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
                                $scope.firstReferrerEmptyMessage = true
                                $scope.firstReferrerErrorMessage = "No Data Found";
                            } else {
                                $scope.firstReferrers = response.slice(0, 5);
                            }
//                            $scope.dealers = response.slice(0, 5);
                        });
                        $http.get("../admin/dashboard/byLocation/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.locations = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.geoReportEmptyMessage = true
                                $scope.geoReportErrorMessage = "No Data Found";
                            } else {
                                $scope.geoReports = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byOs/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.byOs = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.lastReferrerEmptyMessage = true
                                $scope.lastReferrerErrorMessage = "No Data Found";
                            } else {
                                $scope.lastReferrers = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byReferrer/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.referrers = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.assistsEmptyMessage = true
                                $scope.assistsErrorMessage = "No Data Found";
                            } else {
                                $scope.assists = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byMonthlyForSixMonths/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.months = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.referrerSiteEmptyMessage = true
                                $scope.referrerSiteErrorMessage = "No Data Found";
                            } else {
                                $scope.referrerSites = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byDailyForOneMonths/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.dailyForMonths = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.referrerPageEmptyMessage = true
                                $scope.referrerPageErrorMessage = "No Data Found";
                            } else {
                                $scope.referrerPages = response.slice(0, 5);
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

                    };
                    $scope.getItems();
                }])
})();
