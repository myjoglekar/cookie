(function () {
    'use strict';
    angular.module('app.dashboard', [])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', 'SearchKeywords',
                function ($scope, $location, toaster, $http, SearchKeywords) {

                    $http.get('../admin/dashboard/google/trend/' + SearchKeywords.keyword).success(function (response) {
                        $scope.labelCollection = response[0];
                        $scope.label = $scope.labelCollection[1]
                        console.log($scope.label);
                        var googleTrends = response;
                        var googleHitTrends;
                        var trends = [];
                        for (var i = 0; i < googleTrends.length; i++) {
                            googleHitTrends = googleTrends[i];

                            var theEvent = new Date(googleHitTrends[0]);
                            var now = new Date();
                            var sec_num = (now - theEvent) / 1000;
                            var days = Math.floor(sec_num / (3600 * 24));
                            var hours = Math.floor((sec_num - (days * (3600 * 24))) / 3600);
                            var minutes = Math.floor((sec_num - (days * (3600 * 24)) - (hours * 3600)) / 60);
                            var seconds = Math.floor(sec_num - (days * (3600 * 24)) - (hours * 3600) - (minutes * 60));                            

                            trends.push([days, googleHitTrends[1]]);                           
                        }
                        
                        $scope.flot = {
                            options: {
                                grid: {
                                    borderColor: '#eee',
                                    borderWidth: 1,
                                    color: '#ccc'
                                },
                                series: {
                                    lines: {
                                        show: false,
                                        color: 'grey'
                                    },
                                    splines: {
                                        show: true,
                                        tension: 0.3,
                                        lineWidth: 0.9,
                                        fill: .3
                                    }
                                }
                            },
                            data: [{
                                    color: "#3a8225",
                                    data: trends
                                }
//                                {
//                                    color: "#009688",
//                                    data: [[1, 1], [2, 2], [3, 5], [4, 7], [5, 4], [6, 13], [7, 15], [8, 6], [9, 5], [10, 7], [11, 5], [12, 10], [13, 5], [14, 20], [15, 5], [16, 7], [17, 5], [18, 1]]
//                                }
                            ]
                        };
                    });

                }]);
})();