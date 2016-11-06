/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular.module('app.report.conversion', ['ngPrettyJson', 'angularUtils.directives.dirPagination', 'nsPopover'])
            .controller('ConversionController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {
                    /*Dir pagination*/
                    // $scope.currentPage = 1;
                    $scope.count = 50;
                    $scope.total_count = 0;
                    $scope.num = 1;
                    var data = {count: $scope.count, page: $scope.page ? $scope.page : 1};
                    $scope.pageChangeHandler = function (num) {
                        data.count = 50;
                        data.page = num;
                        $http({method: 'GET', url: "../admin/report/formDataList/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate, params: data}).success(function (response) {
                            $scope.selectedForm = response[0];
                            $scope.formDataJson = JSON.parse($scope.selectedForm.formData)//{a:1, 'b':'foo', c:[false,null, {d:{e:1.3e5}}]};

                            $scope.conversions = response;
                            $scope.total_count = response.count;
                            console.log("Data : " + $scope.browsers)
                            console.log("Count : " + $scope.total_count)
                        });
                        console.log('reports page changed to ' + num);
                    };
                    $scope.pageChangeHandler($scope.num);

                    $scope.selectConversion = function (conversion) {
                        $http({method: 'GET', url: "../admin/report/visitDetailsList/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate, params: conversion}).success(function (response) {
                            $scope.visitDetailsList = response;
                        });
                        $scope.selectedForm = conversion;
                        $scope.formDataJson = JSON.parse($scope.selectedForm.formData)//{a:1, 'b':'foo', c:[false,null, {d:{e:1.3e5}}]};
                        $scope.showVisitDetailTable = true;
                    };
//                    if(!$stateParams.searchId){
//                            $stateParams.searchId = 0;
//                        }
//                    


                    /*Header Sortable*/
                    $scope.sort = {
                        column: '',
                        descending: false
                    };
                    $scope.visitSort = {
                        visitColumn: '',
                        descending: false
                    };
                    $scope.changeSorting = function (column) {
                        var sort = $scope.sort;
                        if (sort.column === column) {
                            sort.descending = !sort.descending;
                        } else {
                            sort.column = column;
                            sort.descending = false;
                        }
                    };
                    $scope.changeSortingFromDealer = function (visitColumn) {
                        var visitSort = $scope.visitSort;
                        if (visitSort.visitColumn === visitColumn) {
                            visitSort.descending = !visitSort.descending;
                        } else {
                            visitSort.visitColumn = visitColumn;
                            visitSort.descending = false;
                        }
                    };

                }])
})();








