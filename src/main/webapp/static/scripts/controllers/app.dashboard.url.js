(function () {
    'use strict';
    angular.module('app.dashboard.url', ['nsPopover'])
            .controller('UrlController', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.path = $stateParams.searchId;

                    $scope.firstReferrers = []
                    $scope.lastReferrers = []
                    $scope.assistReferrers = []
                    $scope.data = []

                    $scope.getItems = function () {
                        if (!$stateParams.searchId) {
                            $stateParams.searchId = 0;
                        }


                        $http.get("../admin/report/extremeReferrerSummary/url/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {

                            $("#pieChart").empty();

                            if (response.firstReferrer.length === 0) {
                                $scope.firstReferrerEmptyMessage = true
                                $scope.firstReferrerErrorMessage = "No Data Found";
                            } else {
                                angular.forEach(response.firstReferrer.slice(0, 5), function (value, key) {
                                    $scope.firstReferrers.push(value);
                                    console.log($scope.firstReferrers.referrer)
                                });
                            }

                            //Last Referrer

                            if (response.lastReferrer.length === 0) {
                                $scope.lastReferrerEmptyMessage = true
                                $scope.lastReferrerErrorMessage = "No Data Found";
                            } else {
                                angular.forEach(response.lastReferrer.slice(0, 5), function (value, key) {
                                    $scope.lastReferrers.push(value);
                                    $scope.data.push({label: value.referrer.domainName, value: value.count});
                                });
                            }
                            console.log($scope.data)
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
                                    "pieOuterRadius": "100%"
                                },
                                "data": {
                                    "smallSegmentGrouping": {
                                        "enabled": true,
                                        "valueType": "value"
                                    },
                                    //"content": $scope.data
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
                                    "inner": {
                                        "format": "label-value2"
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

                            if (response.assistReferrer.length === 0) {
                                $scope.assistReferrerEmptyMessage = true
                                $scope.assistReferrerErrorMessage = "No Data Found";
                            } else {
                                angular.forEach(response.assistReferrer.slice(0, 5), function (value, key) {
                                    $scope.assistReferrers.push(value);
                                    console.log($scope.lastReferrers.referrer)
                                });
                            }
                        });
                    };
                    $scope.getItems();

                }]);
})();