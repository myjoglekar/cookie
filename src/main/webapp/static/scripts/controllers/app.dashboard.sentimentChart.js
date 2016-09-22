(function () {
    'use strict';
    angular.module('app.dashboard.sentimentChart', [])
            .controller('DashboardSentimentChartController', ['$scope', 'SearchKeywords', '$http', function ($scope, SearchKeywords, $http) {

                    $http.get('../admin/dashboard/twitter/sentiment/' + SearchKeywords.keyword).success(function (response) {
                        $scope.isLoading = false;
                        if(response.length ==  0) {
                            return;
                        }
                        
                        
                        $scope.morris = {
                            donut: {
                                data: [
                                    {
                                        label: "Negative %",
                                        value: response.tweet_score.neg
                                    },
                                    {
                                        label: "Neutral %",
                                        value: response.tweet_score.neu
                                    },
                                    {
                                        label: "Positive %",
                                        value: response.tweet_score.pos
                                    }
                                ]

                            }
                        };

                        Morris.Donut({
                            element: 'morris4',
                            data: $scope.morris.donut.data,
                            resize: true,
                            colors: ['#F13C6E', '#03A9F4', '#009688']
                        });

                    });

                    $scope.isLoading = true;
//                    
                }]);
})();
