(function () {
    'use strict';
    angular.module('app.report.report', ['nsPopover'])
            .controller('ReportCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams', '$q',
                function ($scope, $location, toaster, $http, $stateParams, $q) {
                    console.log($stateParams.searchId, $stateParams.startDate + " " + $stateParams.endDate)

                    $scope.firstReferrers = []
                    $scope.getItems = function () {
                        if (!$stateParams.searchId) {
                            $stateParams.searchId = 0;
                        }

                        $http.get("../admin/report/extremeReferrerSummary/media/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
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
                                angular.forEach(response.lastReferrer.slice(0, 5), function (value, key) {
                                    $scope.mediaLastReferrers.push(value);
                                    $scope.data.push({label: value.referrer.domainName, value: value.count});
                                });
                            }

                            var pie = new d3pie("pie", {
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
                            $scope.urlFirstReferrers = [];
                            if (response.firstReferrer.length === 0) {
                                $scope.urlFirstReferrerEmptyMessage = true
                                $scope.urlFirstReferrerErrorMessage = "No Data Found";
                            } else {
                                angular.forEach(response.firstReferrer.slice(0, 5), function (value, key) {
                                    $scope.urlFirstReferrers.push(value);
                                });
                            }

                            //Last Referrer
                            $scope.urlLastReferrers = [];
                            $scope.data = []
                            if (response.lastReferrer.length === 0) {
                                $scope.urlLastReferrerEmptyMessage = true
                                $scope.urlLastReferrerErrorMessage = "No Data Found";
                            } else {
                                angular.forEach(response.lastReferrer.slice(0, 5), function (value, key) {
                                    $scope.urlLastReferrers.push(value);
                                    $scope.data.push({label: value.referrer.domainName, value: value.count});
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

                        $scope.firstReferrerColumns = [];
                        $scope.lastReferrerColumns = [];
                        $scope.referrerAssistColumns = [];
                        $scope.deviceColumns = [];
                        $scope.referrerSiteColumns = [];
                        $scope.dealerSummaryColumns = [];
                        $scope.geoReportColumns = [];
                        $scope.referrerColumns = [];
                        $scope.columns = [];
                        $scope.urlFirstReferrerColumns = [];
                        $scope.urlLastReferrerColumns = [];
                        $scope.urlReferrerAssistColumns = [];

                        var extremeReferrerReport = $http.get("../admin/report/extremeReferrerSummary/media/" + $stateParams.searchId),
                                referrerAssistSummary = $http.get("../admin/report/referrerAssistSummary/media/" + $stateParams.searchId),
                                deviceType = $http.get("../admin/dashboard/byDeviceType/" + $stateParams.searchId),
                                referrerSite = $http.get("../admin/dashboard/byReferrer/" + $stateParams.searchId),
                                dealerSummary = $http.get("../admin/dashboard/topDealersByVisit/" + $stateParams.searchId),
                                geoReport = $http.get("../admin/dashboard/byGeoReport/" + $stateParams.searchId),
                                referrerPage = $http.get("../admin/dashboard/byReferrerPage/" + $stateParams.searchId),
                                urlExtremeReferrerReport = $http.get("../admin/report/extremeReferrerSummary/url/" + $stateParams.searchId),
                                urlReferrerAssistSummary = $http.get("../admin/report/referrerAssistSummary/url/" + $stateParams.searchId)
                        $q.all([extremeReferrerReport, referrerAssistSummary, deviceType, referrerSite, dealerSummary, geoReport, referrerPage, urlExtremeReferrerReport, urlReferrerAssistSummary]).then(function (arrayOfResults) {
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
                            var UrlFirstReferrerPageData = [];
                            var UrlLastReferrerPageData = [];
                            var UrlReferrerAssistPageData = [];

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
                            });

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

                            });

                            //Url FirstReferrer
                            angular.forEach(arrayOfResults[7].data.firstReferrer[1], function (value, key) {
                                subKey = key;
                                angular.forEach(value, function (value, key) {
                                    $scope.subKeys = key;
                                    $scope.urlFirstReferrerColumns.push({text: $scope.subKeys, style: 'tableHeader'})
                                })
                                if (subKey != 'referrer') {
                                    $scope.urlFirstReferrerColumns.push({text: subKey, style: 'tableHeader'})
                                }
                            });
                            UrlFirstReferrerPageData.push($scope.urlFirstReferrerColumns)
                            angular.forEach(arrayOfResults[7].data.firstReferrer, function (value, key) {
                                UrlFirstReferrerPageData.push(["" + value.referrer.referrerDomain + "", "" + (value.referrer.domainName ? value.referrer.domainName : "") + "", "" + value.count + ""]);
                            })

                            //Url Last Referrer
                            angular.forEach(arrayOfResults[7].data.lastReferrer[0], function (value, key) {
                                subKey = key;
                                angular.forEach(value, function (value, key) {
                                    $scope.subKeys = key;
                                    $scope.urlLastReferrerColumns.push({text: $scope.subKeys, style: 'tableHeader'})
                                })
                                if (subKey != 'referrer') {
                                    $scope.urlLastReferrerColumns.push({text: subKey, style: 'tableHeader'})
                                }
                            });
                            UrlLastReferrerPageData.push($scope.urlLastReferrerColumns)
                            angular.forEach(arrayOfResults[7].data.lastReferrer, function (value, key) {
                                console.log(value.referrer.domainName)
                                UrlLastReferrerPageData.push(["" + value.referrer.referrerDomain + "", "" + (value.referrer.domainName ? value.referrer.domainName : "") + "", "" + value.count + ""]);
                            })

                            //referrerAssistSummary
                            var subHeader = []
                            angular.forEach(arrayOfResults[8].data.assistReferrer[0], function (value, key) {
                                subHeader = key;
                                angular.forEach(value, function (value, key) {
                                    $scope.subHeaders = key;
                                    $scope.urlReferrerAssistColumns.push({text: $scope.subHeaders, style: 'tableHeader'})
                                })
                                if (subHeader != 'referrer') {
                                    $scope.urlReferrerAssistColumns.push({text: subHeader, style: 'tableHeader'})
                                }
                            });
                            UrlReferrerAssistPageData.push($scope.urlReferrerAssistColumns);
                            angular.forEach(arrayOfResults[8].data.assistReferrer, function (value, key) {
                                UrlReferrerAssistPageData.push(["" + value.referrer.referrerDomain + "", "" + (value.referrer.domainName ? value.referrer.domainName : "") + "", "" + value.count + ""]);
                            });

                            pdfMake.createPdf({
                                header: {
                                    margin: 10,
                                    columns: [
                                        {
                                            image: 'data:image/jpeg;base64,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',
                                            width: 60
                                        }
//                                        {
//                                            margin: [10, 0, 0, 0],
////                                            text: "test"
//                                        }
                                    ]
                                },
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
                                    {text: 'Url First Referrer', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: UrlFirstReferrerPageData.slice(0, 10)
                                        }
                                    },
                                    {text: 'Url Last Referrer', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: UrlLastReferrerPageData.slice(0, 10)
                                        }
                                    },
                                    {text: 'Url Assists', style: 'subheader'},
                                    {
                                        style: 'tableExample',
                                        table: {
                                            headerRows: 1,
                                            // keepWithHeaderRows: 1,
                                            // dontBreakRows: true,
                                            body: UrlReferrerAssistPageData.slice(0, 10)
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
                                        fontFamily: 'proxima_nova_rgregular',
                                        bold: true,
                                        margin: [0, 0, 0, 10]
                                    },
                                    subheader: {
                                        fontSize: 16,
                                        fontFamily: 'proxima_nova_rgregular',
                                        bold: true,
                                        margin: [0, 10, 0, 5]
                                    },
                                    tableExample: {
                                        margin: [0, 5, 0, 15]
                                    },
                                    tableHeader: {
                                        bold: true,
                                        fontFamily: 'proxima_nova_rgregular',
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
