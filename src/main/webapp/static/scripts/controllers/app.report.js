app.controller('ReportCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams', '$q',
                function ($scope, $location, toaster, $http, $stateParams, $q) {
                    //alert('test');
                    $scope.downloadPdf = function () {
                        window.open('../admin/report/downloadReportPdf/' + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate)
                    }

                    $scope.urlLoading = true;
                    $scope.mediaLoading = true;
                    $scope.mediaAssistsLoading = true;
                    $scope.urlAssistsLoading = true;
                    $scope.deviceLoading = true;
                    $scope.referrerSitesLoading = true;
                    $scope.dealerSummaryLoading = true;
                    $scope.geoLoading = true;
                    $scope.referrerPageLoading = true;
                    $scope.frequencyLoadingsd = true;


                    $scope.orderByField = 'count';
                    $scope.reverseSort = true;
                    
                    $scope.orderByVisits = 'totalSiteVisit';
                    $scope.orderByVisitCount = 'visitCount';
                    $scope.reverseSort = true;
                    
                    
                    $scope.orderByUniqueVisits = 'uniqueUserCount';
                    $scope.reverseSort = true;

                    $scope.firstReferrers = []

                    if (!$stateParams.searchId) {
                        $stateParams.searchId = 0;
                    }

                    $http.get("../admin/report/extremeReferrerSummary/media/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        //$scope.statustext = response.statusText;
                        $scope.mediaLoading = false;
                        $("#pie").empty();
                        console.log(response.statusText)
                        $scope.mediaFirstReferrers = [];
                        if (response.firstReferrer.length === 0) {
                            $scope.mediaFirstReferrerEmptyMessage = true
                            $scope.mediaFirstReferrerErrorMessage = "No Data Found";
                        } else {
                            angular.forEach(response.firstReferrer.slice(0, 5), function (value, key) {
                                $scope.mediaFirstReferrers.push(value);
                            });
                        }

                        //Last Referrer
                        $scope.mediaLastReferrers = []
                        $scope.data = []
                        if (response.lastReferrer.length === 0) {
                            $scope.mediaLastReferrerEmptyMessage = true
                            $scope.mediaLastReferrerErrorMessage = "No Data Found";
                        } else {
                            var colors = ['#74C4C6', '#228995', '#5A717A', '#3D464D', '#F1883C']
                            $scope.counter = 0;
                            angular.forEach(response.lastReferrer.slice(0, 5), function (value, key) {
                                $scope.mediaLastReferrers.push(value);
                                $scope.data.push({label: value.referrer.referrerType, value: value.count, color: colors[$scope.counter]});
                                $scope.counter++;
                            });
                        }

                        var pie = new d3pie("pie", {
                            "header": {
                                "title": {
                                    "fontSize": 24,
                                    "font": "proxima_nova_rgregular"
                                },
                                "subtitle": {
                                    "color": "#999999",
                                    "fontSize": 12,
                                    "font": "proxima_nova_rgregular"
                                },
                                "location": "top-left",
                                "titleSubtitlePadding": 1
                            },
                            "footer": {
                                "color": "#999999",
                                "fontSize": 10,
                                "font": "proxima_nova_rgregular",
                                "location": "bottom-left"
                            },
                            "size": {
                                "canvasHeight": 200,
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
                                    fontFamily: 'proxima_nova_rgregular'
                                },
                                "percentage": {
                                    "color": "#ffffff",
                                    "decimalPlaces": null
                                },
                                "value": {
                                    "color": "#adadad",
                                    "fontSize": 11,
                                    fontFamily: 'proxima_nova_rgregular'
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
                                    'x': -60,
                                    'y': 15,
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

                    $http.get("../admin/report/referrerAssistSummary/media/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        $scope.mediaAssistsLoading = false;
                        $scope.mediaAssistReferrers = []

                        if (response.assistReferrer.length === 0) {
                            $scope.mediaAssistReferrerEmptyMessage = true
                            $scope.mediaAssistReferrerErrorMessage = "No Data Found";
                        } else {
                            angular.forEach(response.assistReferrer.slice(0, 5), function (value, key) {
                                $scope.mediaAssistReferrers.push(value);
                            });
                        }
                    });

                    $http.get("../admin/report/extremeReferrerSummary/url/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        $scope.urlLoading = false;
                        $scope.urlFirstReferrers = [];
                        
                        $("#pieUrl").empty();
                        if (response.firstReferrer.length === 0) {
                            $scope.urlFirstReferrerEmptyMessage = true
                            $scope.urlFirstReferrerErrorMessage = "No Data Found";
                        } else {
                            angular.forEach(response.firstReferrer.slice(0, 5), function (value, key) {
                                $scope.urlFirstReferrers.push(value);
                                console.log($scope.urlFirstReferrers)
                            });
                        }

                        //Last Referrer
                        $scope.urlLastReferrers = [];
                        $scope.data = []
                        if (response.lastReferrer.length === 0) {
                            $scope.urlLastReferrerEmptyMessage = true
                            $scope.urlLastReferrerErrorMessage = "No Data Found";
                        } else {
                            var colors = ['#74C4C6', '#228995', '#5A717A', '#3D464D', '#F1883C']
                            $scope.counter = 0;
                            angular.forEach(response.lastReferrer.slice(0, 5), function (value, key) {
                                $scope.urlLastReferrers.push(value);
                                 console.log($scope.urlLastReferrers)
                                $scope.data.push({label: value.referrer.referrerDomain, value: value.count, color: colors[$scope.counter]});
                                $scope.counter++;
                            });
                        }
                        console.log($scope.data);

                        var piechart = new d3pie("pieUrl", {
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
                                "canvasWidth": 200,
                                //"canvasHeight": "50%",
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
                                    fontFamily: 'proxima_nova_rgregular'
                                },
                                "percentage": {
                                    "color": "#ffffff",
                                    "decimalPlaces": null
                                },
                                "value": {
                                    "color": "#adadad",
                                    "fontSize": 11,
                                    fontFamily: 'proxima_nova_rgregular'
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
                                    'x': 60,
                                    'y': -125,
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

                    $http.get("../admin/report/referrerAssistSummary/url/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        $scope.urlAssistsLoading = false;
                        $scope.urlAssistReferrers = []

                        if (response.assistReferrer.length === 0) {
                            $scope.urlAssistReferrerEmptyMessage = true
                            $scope.urlAssistReferrerErrorMessage = "No Data Found";
                        } else {
                            angular.forEach(response.assistReferrer.slice(0, 5), function (value, key) {
                                $scope.urlAssistReferrers.push(value);
                            });
                        }
                    });
                    $http.get("../admin/dashboard/byDeviceType/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        $scope.deviceLoading = false;

                        if (response.length == 0) {
                            $scope.deviceEmptyMessage = true
                            $scope.deviceErrorMessage = "No Data Found";
                        } else {
                            $scope.devices = response.slice(0, 5);
                        }
                    });
                    $http.get("../admin/dashboard/topDealersByVisit/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        $scope.dealerSummaryLoading = false;

                        if (response.length == 0) {
                            $scope.dealerSummaryEmptyMessage = true
                            $scope.dealerSummaryErrorMessage = "No Data Found";
                        } else {
                            $scope.dealerSummarys = response.slice(0, 5);
                        }
//                            $scope.dealers = response.slice(0, 5);
                    });
                    $http.get("../admin/dashboard/byGeoReport/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.locations = response.slice(0, 5);
                        $scope.geoLoading = false;

                        if (response.length == 0) {
                            $scope.geoReportEmptyMessage = true
                            $scope.geoReportErrorMessage = "No Data Found";
                        } else {
                            $scope.geoReports = response.slice(0, 5);
                        }
                    });
                    $http.get("../admin/dashboard/byReferrer/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {

                        $scope.referrerSitesLoading = false;

                        if (response.length == 0) {
                            $scope.referrerSiteEmptyMessage = true
                            $scope.referrerSiteErrorMessage = "No Data Found";
                        } else {
                            $scope.referrerSites = response.slice(0, 5);
                        }
                    });
                    $http.get("../admin/dashboard/byReferrerPage/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {

                        $scope.referrerPageLoading = false;

                        if (response.length == 0) {
                            $scope.referrerPageEmptyMessage = true
                            $scope.referrerPageErrorMessage = "No Data Found";
                        } else {
                            $scope.referrerPages = response.slice(0, 5);
                        }
                    });

                    $scope.item = [];
                    $scope.timeUserMessage = true
                    $http.get("../admin/report/byConversionFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        $scope.timeUserMessage = false
                        if (response[0].avgDays == 0 && response[1].avgDays == 0 && response[2].avgDays == 0 && response[3].avgDays == 0 && response[4].avgDays == 0) {
                            $scope.conversionFrequencyEmptyMessage = true
                            $scope.conversionFrequencyErrorMessage = "No Data Found";
                        } else {
                            $scope.conversionFrequencies = response.slice(0, 5)

//                                //$scope.conversionFrequencies = response.slice(0, 5);
//                                $scope.conversionFrequenyOne = $scope.conversionFrequencies[0].avgDays;
//                                $scope.conversionFrequenyTwo = $scope.conversionFrequencies[1].avgDays;
//                                $scope.conversionFrequenyThree = $scope.conversionFrequencies[2].avgDays;
//                                $scope.conversionFrequenyFour = $scope.conversionFrequencies[3].avgDays;
//                                $scope.conversionFrequenyFive = $scope.conversionFrequencies[4].avgDays;
                        }
                    });
                    
                    
                    
                     $scope.item = [];
                    $scope.summaryUserVisit = true;
                 //  $http.get("datas/byFrequency.json").success(function (response) {
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
                                    bindto: "#reportID",
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
                    
                    
                    
//                     $scope.item = [];
//                    $scope.timeUserMessage = true;
//                    //$http.get("datas/byFrequency.json").success(function (response) {
//                    $http.get("../admin/report/byFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                        // $("#chartID").empty();
//                        $scope.timeUserMessage = false;
//                        if (response[0].count == 0 && response[1].count == 0 && response[2].count == 0 && response[3].count == 0 && response[4].count == 0) {
//                            $scope.conversionFrequencyEmptyMessage = true;
//                            $scope.conversionFrequencyErrorMessage = "No Data Found";
//                        } else {
//                            $scope.frequencies = response.slice(0, 5);
//                            angular.forEach($scope.frequencies, function (value, key) {
//                                $scope.item.push({noOfTimes: value.noOfTimes, count: value.count})
//                                var chart = c3.generate({
//                                    bindto: "#reportID",
//                                    data: {
//                                        labels: true,
//                                        type: 'bar',
//                                        json: $scope.item,
//                                        colors: {
//                                            "count": '#62A6A8',
//                                        },
//                                        keys: {
//                                            x: 'noOfTimes',
//                                            value: ['count']
//                                        }
//                                    },
//                                    axis: {
//                                        x: {
//                                            type: 'category'
//                                        }
//                                    },
//                                    bar: {
//                                        width: {
//                                            ratio: 0.5
//                                        }
//                                    }
//                                });
//                            })
//
//
//                        }
//                    });
                    


                }])

