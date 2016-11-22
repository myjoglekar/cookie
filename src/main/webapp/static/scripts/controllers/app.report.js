(function () {
    'use strict';
    angular.module('app.report.report', ['nsPopover'])
            .controller('ReportCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams', '$q',
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


                                },
                }])
})();
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
