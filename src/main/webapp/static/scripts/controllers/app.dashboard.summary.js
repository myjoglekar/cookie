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
//                            $scope.item = [{x: 1, y: 28}, {x: 2, y: 12}, {x: 3, y: 20}, {x: 4, y: 45}, {x: 5, y: 32}]
                            var maxWidth = 400, rightPadding = 70;
                            

                            var barChart = nv.models.discreteBarChart()
                                    .tooltips(false)
                                    .showValues(true)
                                    //.showLegend(true)
                                    .color(['#ef4c23', '#024965', '#3d464d', '#f48420', '#228995']);

                            //.width(width).height(height);
                            barChart.yAxis.tickFormat(d3.format(',f'));
                            barChart.valueFormat(d3.format('d'));
                            d3.select('#chart svg').datum([
                                {
                                    //key: "User",
                                    //color: "#51A351",
                                    values: $scope.item
                                }
                            ]).transition()
                                    .duration(500)
                                    //.attr('viewBox', '12 33 4 6')
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
                            $("#pieChart").empty();

                            $scope.devices = response.slice(0, 5)
                            angular.forEach($scope.devices, function (value, key) {
                                $scope.data.push({label: value.deviceType, value: value.visitCount})
                            })

                            var pie = new d3pie("pieChart", {
                                "header": {
                                    "title": {
                                        "fontSize": 24,
                                        "font": "open sans"
                                    },
                                    "subtitle": {
                                        "color": "#999999",
                                        "fontSize": 12,
                                        "font": "open sans"
                                    },
                                    "location": "top-left",
                                    "titleSubtitlePadding": 1
                                },
                                "footer": {
                                    "color": "#999999",
                                    "fontSize": 10,
                                    "font": "open sans",
                                    "location": "bottom-left"
                                },
                                "size": {
                                    "canvasHeight": 218,
                                    "pieOuterRadius": "100%"
                                },
                                "data": {
                                    "smallSegmentGrouping": {
                                        "enabled": true,
                                        "valueType": "value"
                                    },
                                    "content": [
                                        {
                                            "label": $scope.data[0].label,
                                            "value": $scope.data[0].value,
                                            "color": "#74C4C6"
                                        },
                                        {
                                            "label": $scope.data[1].label,
                                            "value": $scope.data[1].value,
                                            "color": "#228995"
                                        },
                                        {
                                            "label": $scope.data[2].label,
                                            "value": $scope.data[2].value,
                                            "color": "#5A717A"
                                        },
                                        {
                                            "label": $scope.data[3].label,
                                            "value": $scope.data[3].value,
                                            "color": "#3D464D"
                                        }, {
                                            "label": $scope.data[4].label,
                                            "value": $scope.data[4].value,
                                            "color": "#F1883C"
                                        }],
                                },
                                "labels": {
                                    "outer": {
                                        "pieDistance": 3
                                    },
//                                    "inner": {
//                                        "format": "label-value2"
//                                    },
                                    "mainLabel": {
                                        "fontSize": 11,
                                        fontFamily: 'proxima_nova_rgregular',
                                    },
                                    "percentage": {
                                        "color": "#ffffff",
                                        "decimalPlaces": null
                                    },
                                    "value": {
                                        "color": "#adadad",
                                        "fontSize": 11,
                                        fontFamily: 'proxima_nova_rgregular',
                                    },
                                    "truncation": {
                                        "enabled": true,
                                        "truncateLength": 10
                                    }
                                },
                                "tooltips": {
                                    "enabled": true,
                                    "type": "placeholder",
                                    "string": "{label}: {value}, {percentage}%"
                                },
                                "effects": {
                                    "pullOutSegmentOnClick": {
                                        "effect": "linear",
                                        "speed": 400,
                                        "size": 8
                                    }
                                },
                                "misc": {
                                    "colors": {
                                        "background": "#ffffff"
                                    },
                                    "gradient": {
                                        "enabled": true,
                                        "percentage": 100
                                    }
                                }
                            });

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
                    var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
                    return Math.round(input * factor) / factor;
                };
            });
})();