/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// First define your cloud data, using `text` and `size` properties:
(function () {
    'use strict';
    angular.module('app.wordClouds', [])
            .controller('WordCloudsController', ['$scope', 'SearchKeywords', '$http', function ($scope, SearchKeywords, $http) {

                    var skillsToDraw = []
                    var width = 500;
                    var height = 490;
                    var fill = d3.scale.category20();
                    $scope.topValue = 0;
                    $scope.isLoading = true;
                    $http.get('../admin/dashboard/wordCloud/' + SearchKeywords.keyword).success(function (response) {
                        
                        $scope.isLoading = false;
                        skillsToDraw = response;
                        if(skillsToDraw.length ==  0) {
                            return;
                        }
                        skillsToDraw = response;
                        $scope.topValue = skillsToDraw[0][1];
                        var maxSize = 40;
                        d3.layout.cloud()
                                .size([width, height])
                                .words(skillsToDraw)
                                .rotate(function () {
                                    return ~~(Math.random() * 2) * 90;
                                })
                                .font("Impact")
                                .fontSize(function (d) {
                                    return ((d[1] * maxSize)/$scope.topValue);
                                })
                                .on("end", drawSkillCloud)
                                .start();

                        var svg = document.getElementsByTagName("svg")[0];
                        var bbox = svg.getBBox();
                        var viewBox = [bbox.x, bbox.y, bbox.width, bbox.height].join(" ");
                        svg.setAttribute("viewBox", viewBox);
                    });
                    // apply D3.js drawing API
                    function drawSkillCloud(words) {
                        d3.select("#cloud").append("svg")
                                //.attr("viewBox", "0 0 400 300")
                                .append("g")
//                                .attr("width", width)
//                                .attr("height", height)
//                                .append("g")
                                .attr("transform", "translate(" + ~~(width / 2) + "," + ~~(height / 2) + ")")
                                .selectAll("text")
                                .data(words)
                                .enter().append("text")
                                .style("font-size", function (d) {
                                    return ((d[1] * 40 )/$scope.topValue) + "px";
                                })
                                .style("-webkit-touch-callout", "none")
                                .style("-webkit-user-select", "none")
                                .style("-khtml-user-select", "none")
                                .style("-moz-user-select", "none")
                                .style("-ms-user-select", "none")
                                .style("user-select", "none")
                                .style("cursor", "default")
                                .style("font-family", "Impact")
                                .style("fill", function (d, i) {
                                    return fill(i);
                                })
                                .attr("text-anchor", "middle")
                                .attr("transform", function (d) {
                                    return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                                })
                                .text(function (d) {
                                    return d[0];
                                });
                    }

// set the viewbox to content bounding box (zooming in on the content, effectively trimming whitespace)


                }]);
})();
