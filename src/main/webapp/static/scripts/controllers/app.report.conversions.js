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
                    
                    
                    $scope.orderByField = 'actionTime';
                    $scope.reverseSort = true;
                    
                    $scope.handler = function (num) {
                        data.count = 50;
                        data.page = num;
                        $http({method: 'GET', url: "../admin/report/formDataList/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate, params: data}).success(function (response) {
                            $scope.conversionLoading = false;


                            if (response.data.length === 0) {
                                $scope.conversionEmptyMessage = true;
                                $scope.conversionErrorMessage = "No Data Found";
                            } else {
                                $scope.selectedForm = response.data[0];
                                $scope.formDataJson = "";
                                try {
                                    $scope.formDataJson = JSON.parse($scope.selectedForm.formData)//{a:1, 'b':'foo', c:[false,null, {d:{e:1.3e5}}]};
                                } catch (e) {
                                }
                                $scope.conversions = response.data;
                                $scope.total_count = response.total;
                                console.log(response.total)
                                if (response.data[0]) {
                                    $scope.conversionListLoading = true;
                                    $scope.selectConversion(response.data[0]);
                                }
                            }
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
                        // return (numyears ? (numyears + " years ") : "") + (numdays ? (numdays + " days ") : "") + (numhours ? (numhours + " hours ") : "") + (numminutes ? (numminutes + " minutes ") : "") + numseconds + " seconds";
                        return (numdays + " days ");
                    }



                    //Search
                    $scope.startsWith = function (actual, expected) {
                        var lowerStr = (actual + "").toLowerCase();
                        return lowerStr.indexOf(expected.toLowerCase()) === 0;
                    }


                    
                    $scope.selectConversion = function (conversion) {
                        $scope.selectedForm.totalVisitCount = "-";
                        $scope.selectedForm.visitCount = "-";
                        $scope.visitDetailsList = [];
                        $http({method: 'GET', url: "../admin/report/visitDetailsList/" + $stateParams.searchId + "?" + "startDate=" + $stateParams.startDate + "&" + "endDate=" + $stateParams.endDate, params: conversion}).success(function (response) {
                            $scope.conversionListLoading = false;
                            if (response.data.length === 0) {
                                $scope.conversionListEmptyMessage = true;
                                $scope.conversionListErrorMessage = "No Data Found";
                            } else {
                                $scope.visitDetailsList = response.data;
                                $scope.selectedForm.totalVisitCount = $scope.visitDetailsList.length;
                                $scope.selectedForm.visitCount = secondsToString((( $scope.visitDetailsList[ $scope.visitDetailsList.length - 1].visitTime - $scope.visitDetailsList[0].visitTime) / (1000))*(-1));
                           // console.log($scope.selectForm.visitCount);
                            }
                        });

                        $scope.selectedForm = conversion;
                        $scope.formDataJson = JSON.parse($scope.selectedForm.formData)//{a:1, 'b':'foo', c:[false,null, {d:{e:1.3e5}}]};
                        $scope.showVisitDetailTable = true;
                    };

                    /*Header Sortable*/
                    $scope.sort = {
                        column: 'actionTime',
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








