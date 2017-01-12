(function () {
    'use strict';
    angular.module('app.dashboard.url', ['nsPopover'])
            .controller('UrlController', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    console.log($stateParams.tab)
                    $scope.path = $stateParams.searchId;

                    $scope.dashboardUrlReferrer = true;
                    $scope.dashboardUrlAssists = true;

                    $scope.firstReferrers = []
                    $scope.lastReferrers = []
                    $scope.assistReferrers = []
                    $scope.data = []
                    
                    $scope.test1=[];
                    $scope.test2=[];
                    
                    
                    $scope.orderByField = 'count';
                    $scope.reverseSort = true;
                    
//                    $scope.sorting = {
//                        firstReferer: '',
//                        descending: true
//                    };
//
//                    $scope.changeFirstRefererSorting = function (firstReferer) {
//                        var sorting = $scope.sorting;
//                        if (sorting.firstReferer === firstReferer) {
//                            sorting.descending = !sorting.descending;
//                        } else {
//                            sorting.firstReferer = firstReferer;
//                            sorting.descending = true;
//                        }
//                    };
                    
//                    $scope.sort = {
//                        lastReferer: '',
//                        descending: true
//                    };
//
//                    $scope.changeLastRefererSorting = function (lastReferer) {
//                        var sort = $scope.sort;
//                        if (sort.lastReferer === lastReferer) {
//                            sort.descending = !sort.descending;
//                        } else {
//                            sort.lastReferer = lastReferer;
//                            sort.descending = true;
//                        }
//                    };

                    
                    $scope.assist = {
                        assistOrder: '',
                        descending: true
                    };

                    $scope.changeAssistSorting = function (assistOrder) {
                        var assist = $scope.assist;
                        if (assist.assistOrder === assistOrder) {
                            assist.descending = !assist.descending;
                        } else {
                            assist.assistOrder = assistOrder;
                            assist.descending = true;
                        }
                    };
                    
                    
                    $scope.getItems = function () {
                        if (!$stateParams.searchId) {
                            $stateParams.searchId = 0;
                        }


                        $http.get("../admin/report/extremeReferrerSummary/url/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            $scope.dashboardUrlReferrer = false;
                            $("#pieChart").empty();

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
                                //angular.forEach(response.lastReferrer.slice(0, 5), function (value, key) {
                                     angular.forEach(response.lastReferrer, function (value, key) {
                                    $scope.lastReferrers.push(value);
                                    $scope.data.push({label: value.referrer.referrerDomain, value: value.count, color: colors[$scope.counter]});
                                    $scope.counter++;
                                });
                            }
                            console.log("test:",$scope.data)
//                            var data=[];
//                            angular.forEach($scope.data,function(value,key){
//                              $scope.data.push(value);  
//                              
//                            })
//                            console.log("testing:",data)

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
                                    "content": $scope.data,

                                },
                                "labels": {
                                    "outer": {
                                        "pieDistance": 3
                                    },

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

                        $http.get("../admin/report/referrerAssistSummary/url/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            $scope.dashboardUrlAssists = false;
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
                    };
                    $scope.getItems();

                }]);
})();