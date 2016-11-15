(function () {
    'use strict';
    angular.module('app.report.report', ['nsPopover'])
            .controller('ReportCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams', '$q',
                function ($scope, $location, toaster, $http, $stateParams, $q) {
//                    console.log($stateParams.searchId, $stateParams.startDate + " " + $stateParams.endDate)

                    $scope.firstReferrers = []
                    $scope.lastReferrers = []
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



                    $scope.firstReferrerColumns = []
                    $scope.deviceColumns = []
                    $scope.referrerColumns = [];
                    $scope.columns = [];

                    $scope.downloadPdf = function () {
                        var firstReferrerReport = $http.get("../admin/report/extremeReferrerSummary/" + $stateParams.searchId),
                                referrerPage = $http.get("../admin/dashboard/byReferrerPage/" + $stateParams.searchId),
                                deviceType = $http.get("../admin/dashboard/byDeviceType/" + $stateParams.searchId)
                        $q.all([firstReferrerReport, deviceType, referrerPage]).then(function (arrayOfResults) {
                            console.log(arrayOfResults)
                            var firstReferrerPageData = [];
                            var devicePageData = [];
                            var referrerPageData = [];
                            
                            //firstReferrerReport
                            angular.forEach(arrayOfResults[0].data[0], function (value, key) {
                                $scope.firstReferrerColumns.push({text: key, style: 'tableHeader'})
                            });
                            firstReferrerPageData.push($scope.firstReferrerColumns)
                            angular.forEach(arrayOfResults[1].data, function (value, key) {
                                firstReferrerPageData.push(["" + value.uniqueUserCount + "", "" + value.visitPercent + "", "" + value.visitCount + "", "" + (value.deviceType ? value.deviceType : "") + ""]);
                            })
                            

                            //Device Type
                            angular.forEach(arrayOfResults[1].data[0], function (value, key) {
                                $scope.deviceColumns.push({text: key, style: 'tableHeader'})
                            });
                            devicePageData.push($scope.deviceColumns)
                            angular.forEach(arrayOfResults[1].data, function (value, key) {
                                devicePageData.push(["" + value.uniqueUserCount + "", "" + value.visitPercent + "", "" + value.visitCount + "", "" + (value.deviceType ? value.deviceType : "") + ""]);
                            })

                            //byReferrerPage
                            angular.forEach(arrayOfResults[2].data[0], function (value, key) {
                                $scope.referrerColumns.push({text: key, style: 'tableHeader'})
                            });
                            referrerPageData.push($scope.referrerColumns)
                            angular.forEach(arrayOfResults[2].data, function (value, key) {
                                referrerPageData.push(["" + value.uniqueUserCount + "", "" + value.visitCount + "", "" + (value.referrer ? value.referrer : "") + ""]);
                            })

                            pdfMake.createPdf({
                                header: 'L2t Media',
                                content: [
                                    {text: 'Device Type', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: devicePageData.slice(0, 10)
                                        }
                                    },
                                    {text: 'Referrer Page', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: referrerPageData.slice(0, 10)
                                        }
                                    }
                                ],
                                styles: {
                                    header: {
                                        fontSize: 18,
                                        bold: true,
                                        margin: [0, 0, 0, 10]
                                    },
                                    subheader: {
                                        fontSize: 16,
                                        bold: true,
                                        margin: [0, 10, 0, 5]
                                    },
                                    tableExample: {
                                        margin: [0, 5, 0, 15]
                                    },
                                    tableHeader: {
                                        bold: true,
                                        fontSize: 13,
                                        color: 'black'
                                    }
                                },
                                defaultStyle: {
                                    // alignment: 'justify'
                                }

                            }).download('Sample.pdf');
                        });
                    };

                    $scope.downloadPdf()

                    $scope.demoFromHTML = function () {

                        $(document).ready(function () {
                            $('#example').DataTable({
                                dom: 'Bfrtip',
                                buttons: [
                                    {
                                        extend: 'pdfHtml5',
                                        download: 'open'
                                    }
                                ]
                            });
                        });
//                        var pdf = new jsPDF({lineHeight:0.5});
//                        pdf.setFont("times");
//                        pdf.setFontType("bold");
//                        pdf.setFontSize(5);
////                                new jsPDF('p', 'pt', 'letter');
//                        // source can be HTML-formatted string, or a reference
//                        // to an actual DOM element from which the text will be scraped.
//                        var source = $('#customers')[0];
//
//                        // we support special element handlers. Register them with jQuery-style 
//                        // ID selector for either ID or node name. ("#iAmID", "div", "span" etc.)
//                        // There is no support for any other type of selectors 
//                        // (class, of compound) at this time.
//                        var specialElementHandlers = {
//                            // element with id of "bypass" - jQuery style selector
//                            '#bypassme': function (element, renderer) {
//                                // true = "handled elsewhere, bypass text extraction"
//                                return true
//                            }
//                        };
//                        var margins = {
//                            top: 20,
//                            bottom: 20,
//                            left: 20,
//                            width: 520
//                        };
//                        // all coords and widths are in jsPDF instance's declared units
//                        // 'inches' in this case
//                        pdf.fromHTML(
//                                source, // HTML string or DOM elem ref.
//                                margins.left, // x coord
//                                margins.top, {// y coord
//                                    'width': margins.width, // max width of content on PDF
//                                    'elementHandlers': specialElementHandlers
//                                },
//                                function (dispose) {
//                                    // dispose: object with X, Y of the last line add to the PDF 
//                                    //          this allow the insertion of new lines after html
//                                    pdf.save('Test.pdf');
//                                }, margins);
                    }
                }])
})();
