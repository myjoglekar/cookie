(function () {
    'use strict';

    angular.module('app.header', ['ngSanitize', 'selectize'])
            .controller('AppheaderCtrl', ['$scope', '$http', '$aside', '$rootScope', '$cookies', '$state', '$stateParams', 'SearchKeywords', function ($scope, $http, $aside, $rootScope, $cookies, $state, $stateParams, SearchKeywords, $window) {
                    $scope.userName = $cookies.getObject("username");
                    $scope.SearchKeywords = SearchKeywords;
                    SearchKeywords.keyword = $stateParams.keyword;

                    if ($stateParams.keyword === ':keyword' || $stateParams.keyword === null) {
                        $scope.pageSearch = false;
                    } else {
                        $scope.pageSearch = true;
                    }

                    $scope.getItems = function () {
                        $http.get('../admin/keywords').success(function (response) {
                            $scope.keywords = response;
                        });
                        $http.get('../admin/association').success(function (response) {
                            $scope.associations = response;
                        });
                        $http.get('../admin/search/' + $stateParams.keyword + "/" + $stateParams.association).success(function (response) {
                            $scope.searchKeywords = response;
                        });
                    };
                    $scope.getItems();

                    $scope.disable = false;
                    $scope.associationConfig = {
                        maxItems: 1,
                        create: true,
                        valueField: 'association',
                        labelField: 'association',
                        searchField: ['association']
                    };

                    $scope.keywords = [];
                    $scope.keywordConfig = {
                        maxItems: 1,
                        create: true,
                        valueField: 'keyword',
                        labelField: 'keyword',
                        searchField: ['keyword']
                    };

                    $scope.go = function () {
                        $window.location.reload();
                    }
                }])
            .filter('highlight', function ($sce) {
                return function (text, phrase) {
                    if (phrase)
                        text = text.replace(new RegExp('(' + phrase + ')', 'gi'),
                                '<span class="highlighted">$1</span>')

                    return $sce.trustAsHtml(text)
                };
            })
            .factory('SearchKeywords', function () {
                return {keyword: " "};
            })
})();
