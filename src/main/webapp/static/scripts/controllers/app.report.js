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
                            $scope.firstReferrers = [];
                            if (response.firstReferrer.length === 0) {
                                $scope.firstReferrerEmptyMessage = true
                                $scope.firstReferrerErrorMessage = "No Data Found";
                            } else {
                                angular.forEach(response.firstReferrer.slice(0, 5), function (value, key) {
                                    $scope.firstReferrers.push(value);
                                });
                            }

                            //Last Referrer
                            $scope.lastReferrers = [];
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
                            $scope.assistReferrers = []

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
                    $scope.downloadPdf = function () {
                        $scope.getItems();

                        $scope.firstReferrerColumns = []
                        $scope.lastReferrerColumns = []
                        $scope.referrerAssistColumns = []
                        $scope.deviceColumns = []
                        $scope.referrerSiteColumns = [];
                        $scope.dealerSummaryColumns = [];
                        $scope.geoReportColumns = [];
                        $scope.referrerColumns = [];
                        $scope.columns = [];

                        var extremeReferrerReport = $http.get("../admin/report/extremeReferrerSummary/" + $stateParams.searchId),
                                referrerAssistSummary = $http.get("../admin/report/referrerAssistSummary/" + $stateParams.searchId),
                                deviceType = $http.get("../admin/dashboard/byDeviceType/" + $stateParams.searchId),
                                referrerSite = $http.get("../admin/dashboard/byReferrer/" + $stateParams.searchId),
                                dealerSummary = $http.get("../admin/dashboard/topDealersByVisit/" + $stateParams.searchId),
                                geoReport = $http.get("../admin/dashboard/byGeoReport/" + $stateParams.searchId),
                                referrerPage = $http.get("../admin/dashboard/byReferrerPage/" + $stateParams.searchId)
                        $q.all([extremeReferrerReport, referrerAssistSummary, deviceType, referrerSite, dealerSummary, geoReport, referrerPage]).then(function (arrayOfResults) {
//                            console.log(arrayOfResults)

                            var firstReferrerPageData = [];
                            var lastReferrerPageData = [];
                            var referrerAssistPageData = [];
                            var devicePageData = [];
                            var referrerSitePageData = [];
                            var dealerSummaryPageData = [];
                            var geoReportPageData = [];
                            var referrerPageData = [];
                            var subKey = [];

                            //First Referrer
                            angular.forEach(arrayOfResults[0].data.firstReferrer[1], function (value, key) {
                                subKey = key;
                                angular.forEach(value, function (value, key) {
                                    $scope.subKeys = key;
                                    $scope.firstReferrerColumns.push({text: $scope.subKeys, style: 'tableHeader'})
                                })
                                if (subKey != 'referrer') {
                                    $scope.firstReferrerColumns.push({text: subKey, style: 'tableHeader'})
                                }
                            });
                            firstReferrerPageData.push($scope.firstReferrerColumns)
                            angular.forEach(arrayOfResults[0].data.firstReferrer, function (value, key) {
//                                console.log(value.referrer.domainName)
                                firstReferrerPageData.push(["" + value.referrer.referrerDomain + "", "" + (value.referrer.domainName ? value.referrer.domainName : "") + "", "" + value.count + ""]);
                            })

                            //Last Referrer
                            angular.forEach(arrayOfResults[0].data.lastReferrer[1], function (value, key) {
                                subKey = key;
                                angular.forEach(value, function (value, key) {
                                    $scope.subKeys = key;
                                    $scope.lastReferrerColumns.push({text: $scope.subKeys, style: 'tableHeader'})
                                })
                                if (subKey != 'referrer') {
                                    $scope.lastReferrerColumns.push({text: subKey, style: 'tableHeader'})
                                }
                            });
                            lastReferrerPageData.push($scope.lastReferrerColumns)
                            angular.forEach(arrayOfResults[0].data.lastReferrer, function (value, key) {
                                console.log(value.referrer.domainName)
                                lastReferrerPageData.push(["" + value.referrer.referrerDomain + "", "" + (value.referrer.domainName ? value.referrer.domainName : "") + "", "" + value.count + ""]);
                            })

                            //referrerAssistSummary
                            var subHeader = []
                            angular.forEach(arrayOfResults[1].data.assistReferrer[0], function (value, key) {
                                subHeader = key;
                                angular.forEach(value, function (value, key) {
                                    $scope.subHeaders = key;
                                    $scope.referrerAssistColumns.push({text: $scope.subHeaders, style: 'tableHeader'})
                                })
                                if (subHeader != 'referrer') {
                                    $scope.referrerAssistColumns.push({text: subHeader, style: 'tableHeader'})
                                }
                            });
                            referrerAssistPageData.push($scope.referrerAssistColumns);
                            angular.forEach(arrayOfResults[1].data.assistReferrer, function (value, key) {
                                referrerAssistPageData.push(["" + value.referrer.referrerDomain + "", "" + (value.referrer.domainName ? value.referrer.domainName : "") + "", "" + value.count + ""]);
                            });

                            //Device Type
                            angular.forEach(arrayOfResults[2].data[0], function (value, key) {
                                $scope.deviceColumns.push({text: key, style: 'tableHeader'})
                            });
                            devicePageData.push($scope.deviceColumns)
                            angular.forEach(arrayOfResults[2].data, function (value, key) {
                                devicePageData.push(["" + value.uniqueUserCount + "", "" + value.visitPercent + "", "" + value.visitCount + "", "" + (value.deviceType ? value.deviceType : "") + ""]);
                            })

                            //Referrere Site
                            angular.forEach(arrayOfResults[3].data[0], function (value, key) {
                                $scope.referrerSiteColumns.push({text: key, style: 'tableHeader'})
                            });
                            referrerSitePageData.push($scope.referrerSiteColumns)
                            angular.forEach(arrayOfResults[3].data, function (value, key) {
                                referrerSitePageData.push(["" + (value.referrer ? value.referrer : "") + "", "" + value.uniqueUserCount + "", "" + value.visitCount + ""]);
                            })

                            //Dealer Summary
                            angular.forEach(arrayOfResults[4].data[0], function (value, key) {
                                if (key != 'website') {
                                    $scope.dealerSummaryColumns.push({text: key, style: 'tableHeader'})
                                }
                            });
                            dealerSummaryPageData.push($scope.dealerSummaryColumns)
                            angular.forEach(arrayOfResults[4].data, function (value, key) {
                                dealerSummaryPageData.push(["" + value.uniqueUserCount + "", "" + value.totalSiteVisit + "", "" + (value.dealerName ? value.dealerName : "") + "", "" + value.totalPageVisit + ""]);
                            })

                            //Geo Report
                            angular.forEach(arrayOfResults[5].data[0], function (value, key) {
                                if (key != 'state') {
                                    $scope.geoReportColumns.push({text: key, style: 'tableHeader'})
                                }
                            });
                            geoReportPageData.push($scope.geoReportColumns)
                            angular.forEach(arrayOfResults[5].data, function (value, key) {
                                geoReportPageData.push(["" + value.uniqueUserCount + "", "" + value.visitPercent + "", "" + (value.dealerName ? value.dealerName : "") + "", "" + value.visitCount + "", "" + (value.city ? value.city : "") + "", "" + (value.country ? value.country : "") + ""]);
                            })

                            //byReferrerPage
                            angular.forEach(arrayOfResults[6].data[0], function (value, key) {
                                $scope.referrerColumns.push({text: key, style: 'tableHeader'})
                            });
                            referrerPageData.push($scope.referrerColumns)
                            angular.forEach(arrayOfResults[6].data, function (value, key) {
                                $scope.findUrlLength = space(value.referrer, 30)
                                function space(str, after) {
                                    if (!str) {
                                        return false;
                                    }
                                    after = after || 4;
                                    $scope.reg = new RegExp(".{" + after + "}", "g");
                                    return str.replace($scope.reg, function (a) {
                                        return a + ' ';
                                    });
                                }


                                referrerPageData.push(["" + value.uniqueUserCount + "", "" + value.visitCount + "", "" + ($scope.findUrlLength ? $scope.findUrlLength : "") + ""]);


                            })

                            pdfMake.createPdf({
                                header: {
                                    margin: 10,
                                    columns: [
                                        {
                                            image: 'data:image/jpeg;base64,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',
                                            width: 60
                                        },
                                        {
                                            margin: [10, 0, 0, 0],
                                            text: "test"
                                        }
                                    ]
                                },
//                                header: {
//                                    margin: 10,
//                                    columns: [
//                                        {
//                                            // usually you would use a dataUri instead of the name for client-side printing
//                                            // sampleImage.jpg however works inside playground so you can play with it
//                                            // image: 'img/logos/digital.jpg',
//                                            width: 40
//                                        },
//                                        {
//                                            margin: [10, 0, 0, 0],
//                                            text: 'Here goes the rest'
//                                        }
//                                    ]
//                                },
                                footer: function (pagenumber, pagecount) {
                                    return {margin: [40, 0], text: pagenumber + ' / ' + pagecount};
                                },
                                content: [
                                    {text: 'First Referrer', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: firstReferrerPageData.slice(0, 10)
                                        }
                                    },
                                    {text: 'Last Referrer', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: lastReferrerPageData.slice(0, 10)
                                        }
                                    },
                                    {text: 'Assists', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: referrerAssistPageData.slice(0, 10)
                                        }
                                    },
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
                                    {text: 'Referrer Site', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: referrerSitePageData.slice(0, 10)
                                        }
                                    },
                                    {text: 'Dealer Summary', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: dealerSummaryPageData.slice(0, 10)
                                        }
                                    },
                                    {text: 'Geo Report', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: geoReportPageData.slice(0, 10)
                                        }
                                    },
                                    {text: 'Referrer Page', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            widths: [170, 150, 200],
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
//                    $scope.downloadPdf()
                }])
})();
