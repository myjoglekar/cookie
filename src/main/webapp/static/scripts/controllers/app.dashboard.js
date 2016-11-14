(function () {
    'use strict';
    angular.module('app.dashboard', ['nsPopover'])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.path = $stateParams.searchId;
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
                        $http.get("../admin/dashboard/byGeoReport/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            if (response.length == 0) {
                                $scope.geoReportEmptyMessage = true
                                $scope.geoReportErrorMessage = "No Data Found";
                            } else {
                                $scope.geoCities = response.slice(0, 5);
                                $scope.geoStates = response.slice(0, 5);
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
                    };
                    $scope.getItems();


                    //Performance Chart
                    var data = [
                        {
                            "label": "One",
                            "value": 29.765957771107
                        },
                        {
                            "label": "Two",
                            "value": 0
                        },
                        {
                            "label": "Three",
                            "value": 32.807804682612
                        },
                        {
                            "label": "Four",
                            "value": 196.45946739256
                        },
                        {
                            "label": "Five",
                            "value": 0.19434030906893
                        },
                        {
                            "label": "Six",
                            "value": 98.079782601442
                        },
                        {
                            "label": "Seven",
                            "value": 13.925743130903
                        },
                        {
                            "label": "Eight",
                            "value": 5.1387322875705
                        }
                    ]

                    nv.addGraph(function () {
                        var chart = nv.models.pieChart()
                                .x(function (d) {
                                    return d.label
                                })
                                .y(function (d) {
                                    return d.value
                                })
                                .showLabels(true)
                                .showLegend(false);

                        d3.select("#chart1 svg")
                                .datum(data)
                                .transition().duration(1200)
                                .call(chart);

                        return chart;
                    });
                    
                    //Percentage Of Referrers 
                    nv.addGraph(function () {
                        var chart = nv.models.pieChart()
                                .x(function (d) {
                                    return d.label
                                })
                                .y(function (d) {
                                    return d.value
                                })
                                .showLabels(true)
                                .showLegend(false);

                        d3.select("#chart2 svg")
                                .datum(data)
                                //.transition().duration(1200)
                                .call(chart);

                        return chart;
                    });
                    nv.addGraph(function () {
                        var chart = nv.models.pieChart()
                                .x(function (d) {
                                    return d.label
                                })
                                .y(function (d) {
                                    return d.value
                                })
                                .showLabels(true)
                                .showLegend(false);

                        d3.select("#chart3 svg")
                                .datum(data)
                                //.transition().duration(1200)
                                .call(chart);

                        return chart;
                    });
                }])
            .directive('barChartDirective', function () {
                return{
                    restrict: 'A',
                    scope: {
                        setBarChartFn: '&',
                        barChartId: '@',
                        barChartUrl: '@'
                    },
                    link: function (scope, element, attr) {

                    }
                };
            })
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