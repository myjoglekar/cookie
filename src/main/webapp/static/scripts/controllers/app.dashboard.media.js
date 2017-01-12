(function () {
    'use strict';
    angular.module('app.dashboard.media', ['nsPopover'])
            .controller('MediaController', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {

                    $scope.dashboardMediaReferrer = true;
                    $scope.dashboardMediaAssists = true;
                    $scope.path = $stateParams.searchId;

                    $scope.firstReferrers = [];
                    $scope.lastReferrers = [];
                    $scope.assistReferrers = [];
                    $scope.data = [];

                    if (!$stateParams.searchId) {
                        $stateParams.searchId = 0;
                    }
                    
                    $scope.orderByField = 'count';
                    $scope.reverseSort = true;
                    
                     $scope.sorting = {
                        firstReferer: '',
                        descending: true
                    };

                    $scope.changeFirstRefererSorting = function (firstReferer) {
                        var sorting = $scope.sorting;
                        if (sorting.firstReferer === firstReferer) {
                            sorting.descending = !sorting.descending;
                        } else {
                            sorting.firstReferer = firstReferer;
                            sorting.descending = true;
                        }
                    };
                    
                    $scope.sort = {
                        lastReferer: '',
                        descending: true
                    };

                    $scope.changeLastRefererSorting = function (lastReferer) {
                        var sort = $scope.sort;
                        if (sort.lastReferer === lastReferer) {
                            sort.descending = !sort.descending;
                        } else {
                            sort.lastReferer = lastReferer;
                            sort.descending = true;
                        }
                    };
                    
                    $scope.assist = {
                        assistReferer: '',
                        descending: true
                    };

                    $scope.changeAssistSorting = function (assistReferer) {
                        var assist = $scope.assist;
                        if (assist.assistReferer === assistReferer) {
                            assist.descending = !assist.descending;
                        } else {
                            assist.assistReferer = assistReferer;
                            assist.descending = true;
                        }
                    };

                    $http.get("../admin/report/extremeReferrerSummary/media/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                        $("#pieChart").empty();
                        $scope.dashboardMediaReferrer = false;
                        if (response.firstReferrer.length === 0) {
                            $scope.firstReferrerEmptyMessage = true
                            $scope.firstReferrerErrorMessage = "No Data Found";
                        } else {
                            angular.forEach(response.firstReferrer, function (value, key) {
                                $scope.firstReferrers.push(value);
                                console.log($scope.firstReferrers.referrer)
                            });
                        }

                        //Last Referrer

                        if (response.lastReferrer.length === 0) {
                            $scope.lastReferrerEmptyMessage = true
                            $scope.lastReferrerErrorMessage = "No Data Found";
                        } else {
                            var colors = ['#74C4C6', '#228995', '#5A717A', '#3D464D', '#F1883C']
                            $scope.counter = 0;
                            angular.forEach(response.lastReferrer, function (value, key) {
                                $scope.lastReferrers.push(value);
                                $scope.data.push({label: value.referrer.referrerType, value: value.count, color: colors[$scope.counter]})
                                $scope.counter++;
                            });
                        }

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
                                "canvasHeight": 250,
                                "pieOuterRadius": "80%"
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
                                    "fontSize": 11
                                },
                                "percentage": {
                                    "color": "#ffffff",
                                    "decimalPlaces": null
                                },
                                "value": {
                                    "color": "#adadad",
                                    "fontSize": 11
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
                                    'x': -95,
                                    'y': -15,
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
                        $scope.dashboardMediaAssists = false;
                        if (response.assistReferrer.length === 0) {
                            $scope.assistReferrerEmptyMessage = true
                            $scope.assistReferrerErrorMessage = "No Data Found";
                        } else {
                            angular.forEach(response.assistReferrer, function (value, key) {
                                $scope.assistReferrers.push(value);
                                console.log($scope.lastReferrers.referrer)
                            });
                        }
                    });
                }]);
})();