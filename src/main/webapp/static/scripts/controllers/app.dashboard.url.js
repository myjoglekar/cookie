(function () {
    'use strict';
    angular.module('app.dashboard.url', ['nsPopover'])
            .controller('UrlController', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.path = $stateParams.searchId;

                    $scope.firstReferrers = []
                    $scope.lastReferrers = []
                    $scope.assistReferrers = []
                    $scope.getItems = function () {
                        if (!$stateParams.searchId) {
                            $stateParams.searchId = 0;
                        }


                        $http.get("../admin/report/extremeReferrerSummary/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
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
                                    console.log($scope.lastReferrers.referrer)
                                });
                            }

                        });

                        $http.get("../admin/report/referrerAssistSummary/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {

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
                        var width = 320, height = 320;
                        var chart = nv.models.pieChart()
                                .x(function (d) {
                                    return d.label
                                })
                                .y(function (d) {
                                    return d.value
                                })
                                .width(width).height(height)
                                .color(['#ef4c23', '#024965', '#3d464d', '#f48420', '#228995'])
                                .showLabels(true)
                                .showLegend(true);

                        d3.select("#chart3 svg")
                                .datum(data)
                                .transition().duration(1200)
                                .call(chart);

                        return chart;
                    });
                }]);
})();