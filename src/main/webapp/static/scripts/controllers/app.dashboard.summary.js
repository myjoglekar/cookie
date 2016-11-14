(function () {
    'use strict';
    angular.module('app.dashboard.summary', ['nsPopover'])
            .controller('SummaryController', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.path = $stateParams.searchId;
                    $scope.totalPageVisitCharts = [];
                    $scope.totalSiteVisitCharts = [];
                    $scope.uniqueUserCountCharts = [];
                    $scope.getItems = function () {
                        if (!$stateParams.searchId) {
                            $stateParams.searchId = 0;
                        }
                        $http.get("../admin/dashboard/dashboardTickers/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
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

                        $http.get("../admin/report/byConversionFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            if (response.length == 0) {
                                $scope.conversionFrequencyEmptyMessage = true
                                $scope.conversionFrequencyErrorMessage = "No Data Found";
                            } else {
                                $scope.conversionFrequencies = response.slice(0, 5);
                            }
                        });

                        $scope.item = [];
                        $http.get("../admin/report/byFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            $scope.frequencies = response.slice(0, 5);
                            angular.forEach($scope.frequencies, function (value, key) {
                                $scope.item.push({x: value.noOfTimes, y: value.count})
                            })
                            var barChart = nv.models.discreteBarChart()
                                    .tooltips(false)
                                    .showValues(true)
                                    //.showLegend(true)
                                    .color(['#ef4c23', '#024965', '#3d464d', '#f48420', '#228995']);
                            d3.select('#chart svg').datum([
                                {
                                    //key: "User",
                                    //color: "#51A351",
                                    values: $scope.item
                                }
                            ]).transition()
                                    .duration(500)
                                    .call(barChart);
                        });


$scope.data = []
                        $http.get("../admin/dashboard/byDeviceType/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            if (response.length == 0) {
//                                $scope.deviceEmptyMessage = true
//                                $scope.deviceErrorMessage = "No Data Found";
//                            } else {
//                                $scope.devices = response.slice(0, 5);
//                            }

                            $scope.devices = response.slice(0,5)
                            angular.forEach($scope.devices, function(value, key){
                                $scope.data.push({label:value.deviceType, value: value.visitCount})
                            })
                            nv.addGraph(function () {
                                var width = 320, height = 320;
                                var chart = nv.models.pieChart()
                                        .x(function (d) {
                                            return d.label;
                                        })
                                        .y(function (d) {
                                            return d.value;
                                        })
                                        .width(width).height(height)
                                        .color(['#ef4c23', '#024965', '#3d464d', '#f48420', '#228995'])
                                        .showLabels(true)
                                        .tooltips(false)
                                        .showLegend(true);
                                        //chart.legend.margin({top: 5, right: 0, left: 10, bottom: 50})
                                chart.legend.margin({top: 5, bottom: 50})

                                d3.select("#chart1 svg")
                                        .datum($scope.data)
                                        .transition().duration(1200)
                                        .call(chart);

                                return chart;
                            });
                        });
                    };
                    $scope.getItems();

                    $('.nav-tabs li').click(function (e) {
                        $(this).siblings().removeClass('active')
                        $(this).addClass('active')
                    })


                    //Performance Chart
//                    var data = [
//                        {
//                            "label": "One",
//                            "value": 29.765957771107
//                        },
//                        {
//                            "label": "Two",
//                            "value": 10
//                        },
//                        {
//                            "label": "Three",
//                            "value": 32.807804682612
//                        },
//                        {
//                            "label": "Four",
//                            "value": 196.45946739256
//                        },
//                        {
//                            "label": "Five",
//                            "value": 10.19434030906893
//                        }
//                    ]
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