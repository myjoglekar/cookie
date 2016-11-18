(function () {
    'use strict';
    angular.module('app.report.report', ['nsPopover'])
            .controller('ReportCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams', '$q',
                function ($scope, $location, toaster, $http, $stateParams, $q) {

                    $scope.downloadPdf = function () {
                        window.open('../admin/report/downloadReportPdf/' + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate)
                    }

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
                                var colors = ['#74C4C6', '#228995', '#5A717A', '#3D464D', '#F1883C']
                                $scope.counter = 0;
                                angular.forEach(response.lastReferrer.slice(0, 5), function (value, key) {
                                    $scope.mediaLastReferrers.push(value);
                                    $scope.data.push({label: value.referrer.domainName, value: value.count, color: colors[$scope.counter]});
                                    $scope.counter++;
                                });
                            }

                            var pie = new d3pie("pie", {
                                "header": {
                                    "title": {
                                        "fontSize": 24,
                                        "font": "proxima_nova_rgregular"
                                    },
                                    "subtitle": {
                                        "color": "#999999",
                                        "fontSize": 12,
                                        "font": "proxima_nova_rgregular"
                                    },
                                    "location": "top-left",
                                    "titleSubtitlePadding": 1
                                },
                                "footer": {
                                    "color": "#999999",
                                    "fontSize": 10,
                                    "font": "proxima_nova_rgregular",
                                    "location": "bottom-left"
                                },
                                "size": {
                                    "canvasHeight": 200,
                                    "pieOuterRadius": "100%"
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
                                    "pieCenterOffset": {
                                        'x': -60,
                                        'y': 15,
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
                                var colors = ['#74C4C6', '#228995', '#5A717A', '#3D464D', '#F1883C']
                                $scope.counter = 0;
                                angular.forEach(response.lastReferrer.slice(0, 5), function (value, key) {
                                    $scope.urlLastReferrers.push(value);
                                    $scope.data.push({label: value.referrer.domainName, value: value.count, color: colors[$scope.counter]});
                                    $scope.counter++;
                                });
                            }

                            var piechart = new d3pie("pieUrl", {
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
                                    "canvasWidth": 200,
                                    //"canvasHeight": "50%",
                                    "pieOuterRadius": "100%"
                                },
                                "data": {
                                    "smallSegmentGrouping": {
                                        "enabled": true,
                                        "valueType": "value"
                                    },
                                    "content": $scope.data
//                                    "content": [
//                                        {
//                                            "label": urlData[0].label,
//                                            "value": urlData[0].value,
//                                            "color": "#74C4C6"
//                                        },
//                                        {
//                                            "label": urlData[1].label,
//                                            "value": urlData[1].value,
//                                            "color": "#228995"
//                                        },
//                                        {
//                                            "label": urlData[2].label,
//                                            "value": urlData[2].value,
//                                            "color": "#5A717A"
//                                        },
//                                        {
//                                            "label": urlData[3].label,
//                                            "value": urlData[3].value,
//                                            "color": "#3D464D"
//                                        }, {
//                                            "label": urlData[4].label,
//                                            "value": urlData[4].value,
//                                            "color": "#F1883C"
//                                        }],
                                },
                                "labels": {
                                    "outer": {
                                        "pieDistance": 3
                                    },
//                                    "inner": {
//                                        "format": "label-value2"
//                                    },
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
                                    "pieCenterOffset": {
                                        'x': 60,
                                        'y': -125,
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

                        $scope.item = [];
                        $http.get("../admin/report/byConversionFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            if (response.length == 0) {
                                $scope.conversionFrequencyEmptyMessage = true
                                $scope.conversionFrequencyErrorMessage = "No Data Found";
                            } else {
                                $scope.conversionFrequencies = response.slice(0, 5)

//                                //$scope.conversionFrequencies = response.slice(0, 5);
//                                $scope.conversionFrequenyOne = $scope.conversionFrequencies[0].avgDays;
//                                $scope.conversionFrequenyTwo = $scope.conversionFrequencies[1].avgDays;
//                                $scope.conversionFrequenyThree = $scope.conversionFrequencies[2].avgDays;
//                                $scope.conversionFrequenyFour = $scope.conversionFrequencies[3].avgDays;
//                                $scope.conversionFrequenyFive = $scope.conversionFrequencies[4].avgDays;
                            }

                            console.log($scope.conversionFrequenyOne)
                        });

                        $scope.item = [];
                        $http.get("../admin/report/byFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            $scope.frequencies = response.slice(0, 5);
                            angular.forEach($scope.frequencies, function (value, key) {
                                $scope.item.push({letter: value.noOfTimes, frequency: value.count})
                            })

                            var data = $scope.item;

                            var margin = {top: 20, right: 20, bottom: 30, left: 40};
                            var width = 600 - margin.left - margin.right;
                            var height = 240 - margin.top - margin.bottom;
                            var xScale = d3.scale.ordinal().rangeRoundBands([0, width], .1)
                            //.domain([0,100])
                            //.range([0,width]);

                            var yScale = d3.scale.linear()
                                    .range([height, 0]);


                            var xAxis = d3.svg.axis()
                                    .scale(xScale)
                                    .orient("bottom");

                            var yAxis = d3.svg.axis()
                                    .scale(yScale)
                                    .orient("left");

                            var svgContainer = d3.select("#reportID").append("svg")

                                    .attr("width", width + margin.left + margin.right)
                                    .attr("height", height + margin.top + margin.bottom)
                                    .append("g")
                                    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

                            xScale.domain(data.map(function (d) {
                                return d.letter;
                            }));
                            yScale.domain([0, d3.max(data, function (d) {
                                    return d.frequency;
                                })]);

                            var xAxis_g = svgContainer.append("g")
                                    .attr("class", "x axis")
                                    .attr("transform", "translate(0," + (height) + ")")
                                    .call(xAxis);

                            var yAxis_g = svgContainer.append("g")
                                    .attr("class", "y axis")
                                    .call(yAxis)
                                    .append("text")
                                    .attr("transform", "rotate(-90)")
                                    .attr("y", 0 - margin.left)
                                    .attr("x", 0 - (height / 2)).attr("dy", "1em")
                                    .style("text-anchor", "middle").text("Count");

                            svgContainer.selectAll(".bar")
                                    .data(data)
                                    .enter().append("rect")
                                    .attr("class", "bar")
                                    //.attr("fill", "#74c4c6")
                                    .attr("x", function (d) {
                                        return xScale(d.letter);
                                    })
                                    .attr("width", xScale.rangeBand())
                                    .attr("y", function (d) {
                                        return yScale(d.frequency);
                                    })
                                    .attr("height", function (d) {
                                        return height - yScale(d.frequency);
                                    });

                            d3.select(window).on('resize', resize);
                            resize();
                            function resize() {
                                console.log('----resize function----');
                                // update width
                                width = parseInt(d3.select('#reportID').style('width'), 10);
                                width = width - margin.left - margin.right;

                                height = parseInt(d3.select("#reportID").style("height"));
                                height = height - margin.top - margin.bottom;
                                console.log('----resiz width----' + width);
                                console.log('----resiz height----' + height);
                                // resize the chart
                                //xScale.range([0, width]);
                                xScale.rangeRoundBands([0, width], .1);
                                yScale.range([height, 0]);

                                yAxis.ticks(Math.max(height / 50, 2));
                                xAxis.ticks(Math.max(width / 50, 2));

                                d3.select(svgContainer.node().parentNode)
                                        .style('width', (width + margin.left + margin.right) + 'px');

                                svgContainer.selectAll('.bar')
                                        .attr("x", function (d) {
                                            return xScale(d.letter);
                                        })
                                        .attr("width", xScale.rangeBand());

                                svgContainer.select('.x.axis').call(xAxis.orient('bottom'));

                            }
                        });
                    };

                    $scope.getItems();                   
                }])
})();
