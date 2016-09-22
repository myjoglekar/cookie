(function () {
    'use strict';

    angular.module('app.trendingTweets', ['angularUtils.directives.dirPagination'])
            .controller('TrendingTweetsController', function ($scope, $filter, $http, $templateCache, $timeout,SearchKeywords) {
                $scope.isLoading = true;
                
                /*Trending News*/
                $http.get('../admin/dashboard/google/news/'+SearchKeywords.keyword).success(function(response){
                    $scope.isLoading = false;
                    $scope.trendingNews = response;
                    console.log($scope.trendingNews);
                })

                /*RecentTweetsFromWorldWide*///te/google/news
                $http.get('../admin/dashboard/twitter/sentiment/'+ SearchKeywords.keyword).success(function(response){
                    $scope.isLoading = false;
                    $scope.trendingTweets = response.tweet_list;
                    console.log($scope.trendingTweets);
                })
            });
})();
