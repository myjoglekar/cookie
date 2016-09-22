(function () {
    'use strict';

    angular.module('app.admin.searchkeyword', ['selectize'])

            .controller('SearchKeywordController', function ($scope, $filter, $http) {

                $scope.searchKeywords = [];

                $scope.disable = false;
                $scope.associationConfig = {
                    maxItems: 1,
                    create: true,
                    valueField: 'association',
                    labelField: 'association',
                    searchField: ['association']
                };
                $scope.associations = [];
                
                $scope.categoryConfig = {
                    maxItems: 1,
                    create: true,
                    valueField: 'category',
                    labelField: 'category',
                    searchField: ['category']
                };
                $scope.categories = [];

                $scope.getItems = function () {
                    $http.get("../admin/searchKeywords").success(function (response) {
                        $scope.searchKeywords = response;
                    });
                    $http.get("../admin/categories").success(function (response) {
                        $scope.categories = response;
                    });
                    $http.get("../admin/association").success(function (response) {
                        $scope.associations = response;
                    });
                };
                $scope.getItems();

                $scope.add = function () {
                    $scope.searchKeyword = "";
                };

                $scope.save = function (searchKeyword) {
                    var saveItems = {
                        id: searchKeyword["id"],
                        seatchText: searchKeyword["seatchText"],
                        associationText: searchKeyword["associationId"],
                        categoryText: searchKeyword["categoryId"]
                    };
                    $http({method: searchKeyword.id ? 'PUT' : 'POST', url: '../admin/searchKeywords', data: saveItems}).success(function (response) {
                        $scope.getItems();
                    });
                    $scope.searchKeyword = "";
                };

                $scope.edit = function (searchKeyword) {
                    $scope.searchKeyword = searchKeyword;
                    var data = {
                        id: searchKeyword.id,
                        seatchText: searchKeyword.seatchText,
                        associationId: searchKeyword.associationId.association,
                        categoryId: searchKeyword.categoryId.category
                    };
                    $scope.searchKeyword = data;
                };

                $scope.delete = function (searchKeyword) {
                    $http({method: 'DELETE', url: '../admin/searchKeywords/' + searchKeyword.id}).success(function (response) {
                        $scope.getItems();
                    });
                };

                $scope.remove = function (index) {
                    $scope.searchKeywords.splice(index, 1);
                }

            });

})();