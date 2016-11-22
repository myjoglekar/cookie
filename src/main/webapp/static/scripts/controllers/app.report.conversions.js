/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular.module('app.conversion.conversion', ['ngPrettyJson', 'angularUtils.directives.dirPagination', 'nsPopover'])
            .controller('ConversionController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {
                    /*Dir pagination*/
                    // $scope.currentPage = 1;
                    $scope.conversionLoading = true;
                    $scope.count = 50;
                    $scope.total_count = 0;
                    $scope.num = 1;
                    var data = {count: $scope.count, page: $scope.page ? $scope.page : 1};
                    $scope.handler = function (num) {
                        data.count = 50;
                        data.page = num;
                        $http({method: 'GET', url: "../admin/report/formDataList/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate, params: data}).success(function (response) {
                            $scope.conversionLoading = false;
                            $scope.selectedForm = response.data[0];
                                $scope.formDataJson = JSON.parse($scope.selectedForm.formData)//{a:1, 'b':'foo', c:[false,null, {d:{e:1.3e5}}]};
                            $scope.conversions = response.data;
                            $scope.total_count = response.total;
                            //if (response.data[0]) {
                            $scope.selectConversion(response.data[0]);
                            //}
                        });
                    };
                    $scope.handler($scope.num);


                    function secondsToString(seconds)
                    {
                        var numyears = Math.floor(seconds / 31536000);
                        var numdays = Math.floor((seconds % 31536000) / 86400);
                        var numhours = Math.floor(((seconds % 31536000) % 86400) / 3600);
                        var numminutes = Math.floor((((seconds % 31536000) % 86400) % 3600) / 60);
                        var numseconds = (((seconds % 31536000) % 86400) % 3600) % 60;
                        return (numyears ? (numyears + " years ") : "") + (numdays ? (numdays + " days ") : "") + (numhours ? (numhours + " hours ") : "") + (numminutes ? (numminutes + " minutes ") : "") + numseconds + " seconds";

                    }


                   $scope.conversionListLoading = true;
                    $scope.selectConversion = function (conversion) {
                        $scope.selectedForm.totalVisitCount = "-";
                        $scope.selectedForm.visitCount = "-";
                        $scope.visitDetailsList = [];
                        $http({method: 'GET', url: "../admin/report/visitDetailsList/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate, params: conversion}).success(function (response) {
                            $scope.conversionListLoading = false;
                            $scope.visitDetailsList = response.data;
                            $scope.selectedForm.totalVisitCount = $scope.visitDetailsList.length;
                            $scope.selectedForm.visitCount = secondsToString(($scope.visitDetailsList[ $scope.visitDetailsList.length - 1].visitTime - $scope.visitDetailsList[0].visitTime) / (1000));
                        });
                        $scope.selectedForm = conversion;
                        $scope.formDataJson = JSON.parse($scope.selectedForm.formData)//{a:1, 'b':'foo', c:[false,null, {d:{e:1.3e5}}]};
                        $scope.showVisitDetailTable = true;
                    };

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








