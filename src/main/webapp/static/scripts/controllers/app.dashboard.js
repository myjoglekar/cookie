(function () {
    'use strict';
    angular.module('app.dashboard', ['nsPopover'])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
//                    console.log($stateParams.searchId, $stateParams.startDate + " " + $stateParams.endDate)
                    $scope.totalPageVisitCharts = [];
                    $scope.totalSiteVisitCharts = [];
                    $scope.uniqueUserCountCharts = [];
                    $scope.getItems = function () {
                        if (!$stateParams.searchId) {
                            $stateParams.searchId = 0;
                        }
                        $http.get("../admin/dashboard/dashboardTickers/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            //$scope.dashboardTickers = response;
                            angular.forEach(response, function (value, key) {
                                $scope.totalVisits = value.totalVisits;
                                $scope.totalSiteVisit = value.totalSiteVisit;
                                $scope.uniqueSiteVisit = value.uniqueSiteVisit;
                                $scope.referrerDomains = value.referrerDomains;
                                $scope.uniqueUserCount = value.uniqueUserCount;
                                $scope.formFilled = value.formFilled;
                            });
                        });
                        $http.get("../admin/dashboard/dashboardTickersYesterday/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            angular.forEach(response, function (object, key) {
                                $scope.yesterdayFormFilled = object.formFilled;
                                $scope.yesterdaySiteVisit = object.totalSiteVisit;
                                $scope.yesterdayVisits = object.totalVisits;
                                $scope.yesterdayUniqueSiteVisit = object.uniqueSiteVisit;
                                $scope.yesterdayUniqueUserCount = object.uniqueUserCount;
                                $scope.yesterdayReferrerDomains = object.referrerDomains;
                            });
                        });
                        $http.get("../admin/dashboard/byDeviceType/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            if (response.length == 0) {
                                $scope.deviceEmptyMessage = true
                                $scope.deviceErrorMessage = "No Data Found";
                            } else {
                                $scope.devices = response.slice(0, 5);
                            }
                            console.log($scope.devices)
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
                        $http.get("../admin/dashboard/byLocation/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.locations = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.locationEmptyMessage = true
                                $scope.locationErrorMessage = "No Data Found";
                            } else {
                                $scope.locations = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byOs/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.byOs = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.osEmptyMessage = true
                                $scope.osErrorMessage = "No Data Found";
                            } else {
                                $scope.byOs = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byReferrer/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.referrers = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.referrerEmptyMessage = true
                                $scope.referrerErrorMessage = "No Data Found";
                            } else {
                                $scope.referrers = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byMonthlyForSixMonths/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.months = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.monthEmptyMessage = true
                                $scope.monthErrorMessage = "No Data Found";
                            } else {
                                $scope.months = response.slice(0, 5);
                            }
                        });
                        $http.get("../admin/dashboard/byDailyForOneMonths/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.dailyForMonths = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.dailyForMonthEmptyMessage = true
                                $scope.dailyForMonthErrorMessage = "No Data Found";
                            } else {
                                $scope.dailyForMonths = response.slice(0, 5);
                            }
                        });

                        $http.get("../admin/dashboard/byBrowser/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.browsers = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.browserEmptyMessage = true
                                $scope.browserErrorMessage = "No Data Found";
                            } else {
                                $scope.browsers = response.slice(0, 5);
                            }
                        });

                        $http.get("../admin/report/byFrequency/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
//                            $scope.frequencies = response.slice(0, 5);
                            if (response.length == 0) {
                                $scope.frequencyEmptyMessage = true
                                $scope.frequencyErrorMessage = "No Data Found";
                            } else {
                                $scope.frequencies = response.slice(0, 5);
                            }
                        });

                        $http.get("../admin/dashboard/hourlyVisitChart/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate).success(function (response) {
                            angular.forEach(response, function (value, key) {
                                $scope.totalPageVisitCharts.push([value.hour, value.totalPageVisit]);
                                $scope.totalSiteVisitCharts.push([value.hour, value.totalSiteVisit]);
                                $scope.uniqueUserCountCharts.push([value.hour, value.uniqueUserCount]);
                            });
                            $scope.flotData = {
                                options: {
                                    grid: {
                                        borderColor: '#eee',
                                        borderWidth: 1,
                                        //color: '#ccc'
                                    },
                                    series: {
                                        lines: {
                                            show: true,
                                            color: 'grey'
                                        }
                                    },
                                    legend: {
                                        show: true
                                    },
                                    yaxis: {
                                        show: true
                                    },
                                    xaxis: {
                                        show: true
                                    }
                                },
                                data: [{
                                        shadowSize: 0,
                                        color: "#86c0ba",
                                        label: "Page",
                                        legendlabel: {
                                            color: "red"
                                        },
                                        data: $scope.totalPageVisitCharts
                                    },
                                    {
                                        shadowSize: 0,
                                        color: "#ADBD60",
                                        label: "Site",
                                        data: $scope.totalSiteVisitCharts
                                    },
                                    {
                                        shadowSize: 0,
                                        color: "#DB9090",
                                        label: "User",
                                        data: $scope.uniqueUserCountCharts
                                    }]
                            };
                        });

                    };
                    $scope.getItems();
                }])
            .directive('barChartDirective', function () {
                return{
                    restrict: 'A',
                    scope: {
                        setBarChartFn: '&',
                        barChartId: '@',
                        barChartUrl: '@'
                    },
                    link: function (scope, element, attr) {
//                        var margin = {top: 40, right: 40, bottom: 0, left: 40},
//                        width = 450 - margin.left - margin.right,
//                                height = 300 - margin.top - margin.bottom;
//                        var x = d3.scale.ordinal().rangeRoundBands([0, width], .05);
//                        var y = d3.scale.linear().range([height, 0]);
//                        var xAxis = d3.svg.axis()
//                                .scale(x)
//                                .orient("bottom")
//                        var yAxis = d3.svg.axis()
//                                .scale(y)
//                                .orient("left")
//                                .ticks(10);
//                        var svg = d3.select(element[0]).append("svg")
//                                //.attr("viewBox", "0 0 380 270")
//                                                    .attr("width", width + margin.left + margin.right)
//                                                    .attr("height", height + margin.top + margin.bottom)
//                                .append("g")
//                                .attr("transform",
//                                        "translate(" + margin.left + "," + margin.top + ")");
//
//                            d3.json("datas/barChart.json", function (error, barData) {
//                                var data = barData.slice(0, 10)
//                                data.forEach(function (d) {
//                                    d.Letter = d.Letter;
//                                    d.Freq = +d.Freq;
//                                });
//                                x.domain(data.map(function (d) {
//                                    return d.Letter;
//                                }));
//                                y.domain([0, d3.max(data, function (d) {
//                                        return d.Freq;
//                                    })]);
//                                svg.append("g")
//                                        .attr("class", "x axis")
//                                        .attr("transform", "translate(0," + height + ")")
//                                        .call(xAxis)
//                                        .selectAll("text")
//                                        .style("text-anchor", "end")
//                                        .attr("dx", "-.8em")
//                                        .attr("dy", "-.55em")
//                                        .attr("transform", "rotate(-90)");
//                                svg.append("g")
//                                        .attr("class", "y axis")
//                                        .call(yAxis)
//                                        .append("text")
//                                        .attr("transform", "rotate(-90)")
//                                        .attr("y", 5)
//                                        .attr("dy", ".71em")
//                                        .style("text-anchor", "end")
//                                        .text("Frequency");
//                                // Add bar chart
//                                svg.selectAll("bar")
//                                        .data(data)
//                                        .enter().append("rect")
//                                        .attr("class", "bar")
//                                        .attr("x", function (d) {
//                                            return x(d.Letter);
//                                        })
//                                        .attr("width", x.rangeBand())
//                                        .attr("y", function (d) {
//                                            return y(d.Freq);
//                                        })
//                                        .attr("height", function (d) {
//                                            return height - y(d.Freq);
//                                        });
//                            });

                        //Margin conventions
                        var margin = {top: 10, right: 50, bottom: 20, left: 50};

                        var widther = window.outerWidth;

                        var width = widther - margin.left - margin.right,
                                height = 250 - margin.top - margin.bottom;

//Appends the svg to the chart-container div
                        var svg = d3.select(".g-chart").append("svg")
                                .attr("width", width + margin.left + margin.right)
                                .attr("height", height + margin.top + margin.bottom)
                                .append("g")
                                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

//Creates the xScale 
                        var xScale = d3.scale.linear()
                                .range([0, width]);

//Creates the yScale
                        var y0 = d3.scale.ordinal()
                                .rangeBands([height, 0], 0.2)
                                .domain(["Cat5", "Cat4", "Cat3", "Cat2", "Cat1"]);

//Defines the y axis styles
                        var yAxis = d3.svg.axis()
                                .scale(y0)
                                .orient("left");

//Defines the y axis styles
                        var xAxis = d3.svg.axis()
                                .scale(xScale)
                                .orient("bottom")
                                .tickFormat(function (d) {
                                    return d + "%";
                                })
                                .tickSize(height)
                                .ticks(numTicks(width));

//Loads the data
                        d3.csv("datas/template.csv", ready);

                        function ready(err, data) {

                            if (err)
                                throw "error loading data";
                            console.log("hello");

                            //FORMAT data
                            data.forEach(function (d) {
                                d.num = +d.num;
                            });

                            //Sets the max for the xScale
                            var maxX = d3.max(data, function (d) {
                                return d.num;
                            });

                            //Defines the xScale max
                            xScale.domain([0, maxX]);

                            //Appends the y axis
                            var yAxisGroup = svg.append("g")
                                    .attr("class", "y axis")
                                    .call(yAxis);

                            //Appends the x axis		
                            var xAxisGroup = svg.append("g")
                                    .attr("class", "x axis")
                                    .call(xAxis);

                            //Binds the data to the bars    	
                            var categoryGroup = svg.selectAll(".g-category-group")
                                    .data(data)
                                    .enter()
                                    .append("g")
                                    .attr("class", "g-category-group")
                                    .attr("transform", function (d) {
                                        return "translate(0," + y0(d.category) + ")";
                                    });

                            //Appends first bar		
                            var bars = categoryGroup.append("rect")
                                    .attr("width", function (d) {
                                        return xScale(d.num);
                                    })
                                    .attr("height", y0.rangeBand() / 1.5)
                                    .attr("class", "g-num")
                                    .attr("transform", "translate(0,4)");

                            //Binds data to labels
                            var labelGroup = svg.selectAll("g-num")
                                    .data(data)
                                    .enter()
                                    .append("g")
                                    .attr("class", "g-label-group")
                                    .attr("transform", function (d) {
                                        return "translate(0," + y0(d.category) + ")";
                                    });

                            //Appends labels   
                            var barLabels = labelGroup.append("text")
                                    .text(function (d) {
                                        return  d.num;
                                    })
                                    .attr("x", function (d) {
                                        return xScale(d.num) - 20;
                                    })
                                    .attr("y", y0.rangeBand() / 1.7)
                                    .attr("class", "g-labels");

                            //RESPONSIVENESS
                            d3.select(window).on("resize", resized);

                            function resized() {

                                //new margin
                                var newMargin = {top: 10, right: 80, bottom: 20, left: 50};

                                //Get the width of the window
                                var w = d3.select(".g-chart").node().clientWidth;
                                //console.log("resized", w);

                                //Change the width of the svg
                                d3.select("svg")
                                        .attr("width", w);

                                //Change the xScale
                                xScale
                                        .range([0, w - newMargin.right]);

                                //Update the bars
                                bars
                                        .attr("width", function (d) {
                                            return xScale(d.num);
                                        });

                                //Updates bar labels
                                barLabels
                                        .attr("x", function (d) {
                                            return xScale(d.num) - 20;
                                        })
                                        .attr("y", y0.rangeBand() / 1.7)

                                //Updates xAxis
                                xAxisGroup
                                        .call(xAxis);

                                //Updates ticks
                                xAxis
                                        .scale(xScale)
                                        .ticks(numTicks(w));

                            }
                            ;

                        }

//Determines number of ticks base on width
                        function numTicks(widther) {
                            if (widther <= 400) {
                                return 4
                                //console.log("return 4")
                            } else {
                                return 10
                                //console.log("return 5")
                            }
                        }

                    }
                };
            })
            .directive('devicePieChartDirective', function () {
                return{
                    restrict: 'A',
                    //        template: '<div id="pieChartDashboard"></div>',
                    scope: {
                        // value: "@value
                        // ",
                        //collection: '@',
                        setPieChartFn: '&',
                        pieChartId: '@',
                        pieChartUrl: '@'
                    },
                    link: function (scope, element, attr) {

                        var width = 300,
                                height = 240,
                                radius = Math.min(width, height) / 2;
                        var color = d3.scale.ordinal()
                                .range(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);
                        var arc = d3.svg.arc()
                                .outerRadius(radius - 10)
                                .innerRadius(0);
                        var labelArc = d3.svg.arc()
                                .outerRadius(radius - 40)
                                .innerRadius(radius - 40);
                        var pie = d3.layout.pie()
                                .sort(null)
                                .value(function (d) {
                                    return d.population;
                                });
                        var svg = d3.select(element[0]).append("svg")
                                .attr("viewBox", "-30 0 380 240")
                                //                    .attr("width", width)
                                //                    .attr("height", height)
                                .append("g")
                                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");


                        scope.refreshWidgetPie = function () {
                            //                element.html('');
                            d3.csv("datas/devicePieChart.csv", type, function (error, data) {
                                if (error)
                                    throw error;
                                var g = svg.selectAll(".arc")
                                        .data(pie(data))
                                        .enter().append("g")
                                        .attr("class", "arc");
                                g.append("path")
                                        .attr("d", arc)
                                        .style("fill", function (d) {
                                            return color(d.data.color);
                                        });
                                g.append("text")
                                        .attr("transform", function (d) {
                                            return "translate(" + labelArc.centroid(d) + ")";
                                        })
                                        .attr("dy", ".35em")
                                        .text(function (d) {
                                            return d.data.color;
                                        });
                            });
                            function type(d) {
                                d.population = +d.population;
                                return d;
                            }//            
                        }
                        scope.setPieChartFn({pieChartFn: scope.refreshWidgetPie});
                        scope.refreshWidgetPie();
                    }
                };
            })
            .directive('percentagePieChartDirective', function () {
                return{
                    restrict: 'A',
                    //        template: '<div id="pieChartDashboard"></div>',
                    scope: {
                        // value: "@value
                        // ",
                        //collection: '@',
                        setPieChartFn: '&',
                        pieChartId: '@',
                        pieChartUrl: '@'
                    },
                    link: function (scope, element, attr) {

                        var width = 300,
                                height = 240,
                                radius = Math.min(width, height) / 2;
                        var color = d3.scale.ordinal()
                                .range(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);
                        var arc = d3.svg.arc()
                                .outerRadius(radius - 10)
                                .innerRadius(0);
                        var labelArc = d3.svg.arc()
                                .outerRadius(radius - 40)
                                .innerRadius(radius - 40);
                        var pie = d3.layout.pie()
                                .sort(null)
                                .value(function (d) {
                                    return d.population;
                                });
                        var svg = d3.select(element[0]).append("svg")
                                .attr("viewBox", "-30 0 380 240")
                                //                    .attr("width", width)
                                //                    .attr("height", height)
                                .append("g")
                                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");


                        scope.refreshWidgetPie = function () {
                            //                element.html('');
                            d3.csv("datas/percentagePieChart.csv", type, function (error, data) {
                                if (error)
                                    throw error;
                                var g = svg.selectAll(".arc")
                                        .data(pie(data))
                                        .enter().append("g")
                                        .attr("class", "arc");
                                g.append("path")
                                        .attr("d", arc)
                                        .style("fill", function (d) {
                                            return color(d.data.color);
                                        });
                                g.append("text")
                                        .attr("transform", function (d) {
                                            return "translate(" + labelArc.centroid(d) + ")";
                                        })
                                        .attr("dy", ".35em")
                                        .text(function (d) {
                                            return d.data.color;
                                        });
                            });
                            function type(d) {
                                d.population = +d.population;
                                return d;
                            }//            
                        }
                        scope.setPieChartFn({pieChartFn: scope.refreshWidgetPie});
                        scope.refreshWidgetPie();
                    }
                };
            })
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
                    // If we want 1 decimal place, we want to mult/div by 10
                    // If we want 2 decimal places, we want to mult/div by 100, etc
                    // So use the following to create that factor
                    var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
                    return Math.round(input * factor) / factor;
                };
            });
})();