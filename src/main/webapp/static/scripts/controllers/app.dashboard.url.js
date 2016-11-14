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
                                    $scope.data.push({label: value.referrer.domainName, value: value.count});
                                });
                            }

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
                                        .tooltips(true)
                                        .showLegend(false);

                                d3.select("#chart3 svg")
                                        .datum($scope.data)
                                        .transition().duration(1200)
                                        .call(chart);

                                return chart;
                            });

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

                }]);
})();