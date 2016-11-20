(function () {
    'use strict';
    angular.module('app.dashboard.summary', ['nsPopover'])
            .controller('SummaryController', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.dashboardGeoReportLoading = true;
                    $scope.path = $stateParams.searchId;
                    $scope.totalPageVisitCharts = [];
                    $scope.totalSiteVisitCharts = [];
                    $scope.uniqueUserCountCharts = [];

                    $scope.loadingGeoReport = true;

                    $scope.getItems = function () {
                        if (!$stateParams.searchId) {
                            $stateParams.searchId = 0;
                        }
                        $http.get("../admin/dashboard/dashboardTickers/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            angular.forEach(response, function (value, key) {
                                $scope.totalVisits = value.totalVisits;
                                $scope.totalSiteVisit = value.totalSiteVisit;
                                $scope.uniqueSiteVisit = value.uniqueSiteVisit;
                                $scope.referrerDomains = value.referrerDomains;
                                $scope.uniqueUserCount = value.uniqueUserCount;
                                $scope.formFilled = value.formFilled;
                            });
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
                                $scope.dealerEmptyMessage = true
                                $scope.dealerErrorMessage = "No Data Found";
                            } else {
                                $scope.dealers = response.slice(0, 5);
                            }
//                            $scope.dealers = response.slice(0, 5);
                        });

                        $http.get("../admin/dashboard/byGeoReport/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            $scope.dashboardGeoReportLoading = false;
                            if (response.length == 0) {
                                $scope.geoReportEmptyMessage = true
                                $scope.geoReportErrorMessage = "No Data Found";
                            } else {
                                $scope.geoCities = response.slice(0, 5);
                                $scope.geoStates = response.slice(0, 5);
                            }
                        });

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

//                            function visitChart() {
//                                var visit_chart = []
//                                var temp = 5 - $scope.item.length;
//                                if (temp != 0) {
//                                    for (var j = temp; j <= 5; j++) {
//                                        visit_chart.push({letter: "", frequency: 0})
//                                    }
//                                    $scope.visitCollection = $scope.item.concat(visit_chart);
//                                    return $scope.visitCollection;
//                                } else {
//                                    $scope.visitCollection = $scope.item
//                                    return $scope.visitCollection
//                                }
//                            }

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

                            var svgContainer = d3.select("#chartID").append("svg")

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
                                width = parseInt(d3.select('#chartID').style('width'), 10);
                                width = width - margin.left - margin.right;

                                height = parseInt(d3.select("#chartID").style("height"));
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


                        $scope.data = []
                        $http.get("../admin/dashboard/byDeviceType/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            if (response.length == 0) {
//                                $scope.deviceEmptyMessage = true
//                                $scope.deviceErrorMessage = "No Data Found";
//                            } else {
//                                $scope.devices = response.slice(0, 5);
//                            }
                            $("#pieChart").empty();

                            $scope.devices = response.slice(0, 5)
                            var colors = ['#74C4C6', '#228995', '#5A717A', '#3D464D', '#F1883C']
                            $scope.counter = 0;
                            angular.forEach($scope.devices, function (value, key) {
                                //$scope.count += 1
                                $scope.data.push({label: value.deviceType, value: value.visitCount, color: colors[$scope.counter]})
                                $scope.counter++;
                            })
//                            console.log($scope.data)
//                            function dashboardSummaryChart() {
//                                var dashboard_Summary = []
//                                var temp = 5 - $scope.data.length;
//                                if (temp != 5) {
//                                    if (temp != 0) {
//                                        for (var j = temp; j <= 5; j++) {
//                                            dashboard_Summary.push({label: "", value: 0})
//                                        }
//                                        $scope.summaryCollection = $scope.data.concat(dashboard_Summary);
//                                        return $scope.summaryCollection;
//                                    } else {
//                                        $scope.summaryCollection = $scope.data;
//                                        return $scope.summaryCollection;
//                                    }
//                                } else {
//                                    for (var j = 0; j <= 5; j++) {
//                                        dashboard_Summary.push({label: "", value: 0});
//                                    }
//                                    $scope.summaryCollection = $scope.data.concat(dashboard_Summary);
//                                    return $scope.summaryCollection;
//                                }
//                            }
//
//                            var deviceData = dashboardSummaryChart();
//                            console.log(deviceData)
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
                                    "canvasHeight": 218,
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
//                                            "label": deviceData[0].label,
//                                            "value": deviceData[0].value,
//                                            "color": "#74C4C6"
//                                        },
//                                        {
//                                            "label": deviceData[1].label,
//                                            "value": deviceData[1].value,
//                                            "color": "#228995"
//                                        },
//                                        {
//                                            "label": deviceData[2].label,
//                                            "value": deviceData[2].value,
//                                            "color": "#5A717A"
//                                        },
//                                        {
//                                            "label": deviceData[3].label,
//                                            "value": deviceData[3].value,
//                                            "color": "#3D464D"
//                                        }, {
//                                            "label": deviceData[4].label,
//                                            "value": deviceData[4].value,
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
                                        'x': -80,
                                        //'y': 15,
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
                    };
                    $scope.getItems();



                }])
            .filter('monthName', [function () {
                    return function (monthNumber) { //1 = January
                        var monthNames = ['January', 'February', 'March', 'April', 'May', 'June',
                            'July', 'August', 'September', 'October', 'November', 'December'];
                        return monthNames[monthNumber - 1];
                    };
                }])
            .filter('setDecimal', function () {
                return function (input, places) {
                    if (isNaN(input))
                        return input;
                    var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
                    return Math.round(input * factor) / factor;
                };
            });
})();
