(function () {
    'use strict';
    angular.module('app.report.report', ['nsPopover'])
            .controller('ReportCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
//                    console.log($stateParams.searchId, $stateParams.startDate + " " + $stateParams.endDate)

                    $scope.getItems = function () {
                        if (!$stateParams.searchId) {
                            $stateParams.searchId = 0;
                        }

                        $scope.firstReferrers = []
                        $scope.lastReferrers = []
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

                        $scope.assistReferrers = []
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
                                $scope.dealerSummaryEmptyMessage = true
                                $scope.dealerSummaryErrorMessage = "No Data Found";
                            } else {
                                $scope.dealerSummarys = response.slice(0, 5);
                            }
//                            $scope.dealers = response.slice(0, 5);
                        });
                        $http.get("../admin/dashboard/byGeoReport/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.locations = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.geoReportEmptyMessage = true
                                $scope.geoReportErrorMessage = "No Data Found";
                            } else {
                                $scope.geoReports = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byReferrer/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            if (response.length == 0) {
                                $scope.referrerSiteEmptyMessage = true
                                $scope.referrerSiteErrorMessage = "No Data Found";
                            } else {
                                $scope.referrerSites = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byReferrerPage/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            if (response.length == 0) {
                                $scope.referrerPageEmptyMessage = true
                                $scope.referrerPageErrorMessage = "No Data Found";
                            } else {
                                $scope.referrerPages = response.slice(0, 5);
                            }
                        });

                    };
                    $scope.getItems();

                    $scope.demoFromHTML = function () {
                        var pdf = new jsPDF('p', 'pt', 'letter');
                        // source can be HTML-formatted string, or a reference
                        // to an actual DOM element from which the text will be scraped.
                        var source = $('#customers')[0];

                        // we support special element handlers. Register them with jQuery-style 
                        // ID selector for either ID or node name. ("#iAmID", "div", "span" etc.)
                        // There is no support for any other type of selectors 
                        // (class, of compound) at this time.
                        var specialElementHandlers = {
                            // element with id of "bypass" - jQuery style selector
                            '#bypassme': function (element, renderer) {
                                // true = "handled elsewhere, bypass text extraction"
                                return true
                            }
                        };
                        var margins = {
                            top: 80,
                            bottom: 60,
                            left: 40,
                            width: 522
                        };
                        // all coords and widths are in jsPDF instance's declared units
                        // 'inches' in this case
                        pdf.fromHTML(
                                source, // HTML string or DOM elem ref.
                                margins.left, // x coord
                                margins.top, {// y coord
                                    'width': margins.width, // max width of content on PDF
                                    'elementHandlers': specialElementHandlers
                                },
                                function (dispose) {
                                    // dispose: object with X, Y of the last line add to the PDF 
                                    //          this allow the insertion of new lines after html
                                    pdf.save('Test.pdf');
                                }, margins);
                    }
                }])
})();
