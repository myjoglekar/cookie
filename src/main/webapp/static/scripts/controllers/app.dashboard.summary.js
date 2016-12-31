(function () {
    'use strict';
    angular.module('app.dashboard.summary', ['nsPopover'])
            .controller('SummaryController', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.startDate = $stateParams.startDate;
                    $scope.endDate = $stateParams.endDate;
                    $scope.dashboardDeviceChartsLoading = true;
                    $scope.dashboardGeoReportLoading = true;
                    $scope.path = $stateParams.searchId;
                    $scope.totalPageVisitCharts = [];
                    $scope.totalSiteVisitCharts = [];
                    $scope.uniqueUserCountCharts = [];



                    $scope.loadingGeoReport = true;

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
                        $scope.dashboardGeoReportLoading = false;
                        if (response.length == 0) {
                            $scope.geoReportEmptyMessage = true
                            $scope.geoReportErrorMessage = "No Data Found";
                        } else {
                            $scope.geoCities = response.slice(0, 5);
                            $scope.geoStates = response.slice(0, 5);
                        }
                    });
                    $scope.byConversionFrequency = true;
                    $http.get("../admin/report/byConversionFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        $scope.byConversionFrequency = false;
                        if (response[0].avgDays == 0 && response[1].avgDays == 0 && response[2].avgDays == 0 && response[3].avgDays == 0 && response[4].avgDays == 0) {
                            $scope.conversionFrequencyEmptyMessage = true;
                            $scope.conversionFrequencyErrorMessage = "No Data Found";
                        } else {
                            $scope.conversionFrequencies = response.slice(0, 5)

                        }
                    });


                    $scope.item = [];
                    $scope.summaryUserVisit = true;
                //   $http.get("datas/byFrequency.json").success(function (response) {
                    $http.get("../admin/report/byFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        // $("#chartID").empty();
                        $scope.summaryUserVisit = false;
                        if (response[0].count == 0 && response[1].count == 0 && response[2].count == 0 && response[3].count == 0 && response[4].count == 0) {
                            $scope.summaryUserVisitEmptyMessage = true;
                            $scope.summaryUserVisitErrorMessage = "No Data Found";
                        } else {
                            $scope.frequencies = response.slice(0, 5);
                            angular.forEach($scope.frequencies, function (value, key) {
                                $scope.item.push({noOfTimes: value.noOfTimes, count: value.count})
                                var chart = c3.generate({
                                    bindto: "#chartID",
                                    data: {
                                        labels: true,
                                        type: 'bar',
                                        json: $scope.item,
                                        colors: {
                                            "count": '#62A6A8',
                                        },
                                        keys: {
                                            x: 'noOfTimes',
                                            value: ['count']
                                        }
                                    },
                                    axis: {
                                        x: {
                                            type: 'category'
                                        }
                                    },
                                    bar: {
                                        width: {
                                            ratio: 0.5
                                        }
                                    }
                                });
                            })


                        }
                    });


                    $scope.data = []
                    $http.get("../admin/dashboard/byDeviceType/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {

                        $scope.dashboardDeviceChartsLoading = false;
                        $("#pieChart").empty();
                        if (response.length == 0) {
                            $scope.deviceReportEmptyMessage = true
                            $scope.deviceReportErrorMessage = "No Data Found";
                        } else {
                            $scope.devices = response.slice(0, 5)
                            var colors = ['#74C4C6', '#228995', '#5A717A', '#3D464D', '#F1883C']
                            $scope.counter = 0;
                            angular.forEach($scope.devices, function (value, key) {
                                $scope.data.push({label: value.deviceType, value: value.visitCount, color: colors[$scope.counter]})
                                $scope.counter++;
                            })
                        }


//                        $scope.devices = response.slice(0, 5)
//                        var colors = ['#74C4C6', '#228995', '#5A717A', '#3D464D', '#F1883C']
//                        $scope.counter = 0;
//                        angular.forEach($scope.devices, function (value, key) {
//                            $scope.data.push({label: value.deviceType, value: value.visitCount, color: colors[$scope.counter]})
//                            $scope.counter++;
//                        })

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
                                "content": $scope.data
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
                                "pieCenterOffset": {
                                    'x': -90,
                                    //'y': 15,
                                },
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
