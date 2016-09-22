/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular.module('app.topKeywords', [])
            .controller('TopKeywordsController', ['$scope', '$http', '$stateParams', 'SearchKeywords', function ($scope, $http, $stateParams, SearchKeywords) {

                    $scope.SearchKeywords = SearchKeywords;

                    d3.chart('BarChart', {
                        initialize: function () {
                            var chart = this;

                            chart.margins = {
                                top: 10,
                                bottom: 25,
                                left: 50,
                                right: 10,
                                padding: 5,
                            };

                            chart.x = d3.scale.linear();
                            chart.y = d3.scale.linear();

                            chart.base
                                    .classed('Barchart', true);

                            chart.areas = {};

                            chart.layers = {};

                            chart.areas.ylabels = chart.base.append('g')
                                    .classed('ylabels', true)
                                    .attr('width', chart.margins.left)
                                    .style("font-size", "15")
                                    .style("fill", "grey")
                                    .attr('transform',
                                            'translate(' + (chart.margins.left - 1) + ',' + (chart.margins.top + 1) + ')');

                            chart.layers.bars = chart.base.append('g')
                                    .classed('bars', true)
                                    .attr('transform',
                                            'translate(' + chart.margins.left + ',' + (chart.margins.top + 1) + ')');

                            chart.layers.xlabels = chart.base.append('g')
                                    .classed('xlabels', true)
                                    .attr('height', chart.margins.bottom);


                            chart.on("change:width", function () {
                                chart.x.range([0, chart.w - chart.margins.left]);
                                chart.layers.bars.attr('width', chart.w - chart.margins.left);
                                chart.layers.xlabels.attr('width', chart.w - chart.margins.left);
                            });

                            chart.on("change:height", function () {
                                chart.y.range([chart.h - chart.margins.bottom - chart.margins.top, 0]);
                                chart.areas.ylabels
                                        .attr('height', chart.h - chart.margins.bottom - chart.margins.top - 1);
                                chart.layers.bars
                                        .attr('height', chart.h - chart.margins.bottom - chart.margins.top);
                                chart.layers.xlabels
                                        .attr('transform', 'translate(' + chart.margins.left + ',' +
                                                (chart.h - chart.margins.bottom + 1) + ')');
                            });

                            chart.layer('bars', chart.layers.bars, {
                                dataBind: function (data) {

                                    chart.data = data;

                                    chart.bars = data.length;

                                    chart.bar_width = (chart.w - chart.margins.left - ((chart.bars - 1) *
                                            chart.margins.padding)) / chart.bars;

                                    chart.x.domain([0, chart.bars])

                                    chart.datamax = chart.usermax ||
                                            d3.extent(data, function (d) {
                                                return d.value;
                                            })[1];

                                    chart.y.domain([0, chart.datamax]);

                                    var yAxis = d3.svg.axis()
                                            .scale(chart.y)
                                            .orient('left')
                                            .ticks(5)
                                            .tickFormat(chart._yformat || d3.format('.0%'));

                                    chart.areas.ylabels
                                            .call(yAxis);


                                    return this.selectAll('rect')
                                            .data(data, function (d) {
                                                return d.name;
                                            });
                                },
                                insert: function () {
                                    return this.append('rect')
                                            .classed('bar', true)
                                            .classed('highlight', function (d) {
                                                return d.highlight;
                                            });
                                },
                                events: {
                                    exit: function () {
                                        this.remove();
                                    }
                                }
                            });

                            chart.layer('xlabels', chart.layers.xlabels, {
                                dataBind: function (data) {
                                    this.append('line')
                                            .attr('x1', 0)
                                            .attr('x2', chart.w - chart.margins.left)
                                            .attr('y1', 0)
                                            .attr('y2', 0)
                                            .style('stroke', 'lightgrey')
                                            .style('stroke-width', '1')
                                            .style('shape-rendering', 'crispEdges');


                                    return this.selectAll('text')
                                            .data(data, function (d) {
                                                return d.name;
                                            });
                                },
                                insert: function () {
                                    return this.append('text')
                                            .style("font-size", "15")
                                            .style("fill", "grey")
                                            //.classed('label', true)
                                            .attr('text-anchor', 'middle')
                                            .attr('x', function (d, i) {
                                                return chart.x(i) - 0.5 + chart.bar_width / 2;
                                            })
                                            .attr('dy', '1em')
                                            .text(function (d) {
                                                return d.name;
                                            });
                                },
                                events: {
                                    exit: function () {
                                        this.remove();
                                    }
                                }
                            });

                            var onEnter = function () {
                                this.attr('x', function (d, i) {
                                    return chart.x(i) - 0.5;
                                })
                                        .attr('y', function (d) {
                                            return chart.h - chart.margins.bottom -
                                                    chart.margins.top - chart.y(chart.datamax - d.value) - 0.5;
                                        })
                                        .attr('val', function (d) {
                                            return d.value;
                                        })
                                        .attr('width', chart.bar_width)
                                        .attr('height', function (d) {
                                            return chart.y(chart.datamax - d.value);
                                        });
                            };

                            chart.layer('bars').on('enter', onEnter);
                            chart.layer('bars').on('update', onEnter);
                        },
                        max: function (datamax) {
                            if (!arguments.length) {
                                return this.usermax;
                            }

                            this.usermax = datamax;

                            if (this.data)
                                this.draw(this.data);

                            return this;
                        },
                        yFormat: function (format) {
                            if (!arguments.length) {
                                return this._yformat;
                            }
                            this._yformat = format;
                            return this;
                        },
                        width: function (newWidth) {
                            if (!arguments.length) {
                                return this.w;
                            }
                            this.w = newWidth;

                            this.x.range([this.margins.left, this.w - this.margins.right]);

                            this.base.attr('width', this.w);

                            this.trigger("change:width");
                            if (this.data)
                                this.draw(this.data);

                            return this;
                        },
                        height: function (newHeight) {
                            if (!arguments.length) {
                                return this.h;
                            }

                            // save new height
                            this.h = newHeight;

                            // adjust the y scale
                            this.y.range([this.h - this.margins.top, this.margins.bottom]);

                            // adjust the base width
                            this.base.attr('height', this.h);

                            this.trigger("change:height");
                            if (this.data)
                                this.draw(this.data);
                            return this;
                        }
                    });

                    var data = [];
                    $scope.isLoading = true;
                    $http.get('../admin/dashboard/topKeywords/' + $scope.SearchKeywords.keyword).success(function (response) {
                        $scope.isLoading = false;
                        data = response.slice(0, 10);
                        var topKeywords = d3.select('#topKeywords')
                                .append('svg')
                                .chart('BarChart') //**Moving transform position out of here**
                                .yFormat(d3.format("d"))
                                .height(500)
                                .width(1000);


                        function transform(data) {
                            return data.map(function (d) {
                                return {name: d[0], value: d[1]};
                            });
                        }


                        topKeywords.draw(transform(data));

                        $("#width_box").on("change", function (e) {
                            var newWidth = +($(e.target).val());
                            topKeywords.width(newWidth);
                        });

                        $("#height_box").on("change", function (e) {
                            var newHeight = +($(e.target).val());
                            topKeywords.height(newHeight);
                        });
                    });
                }]);
})();
