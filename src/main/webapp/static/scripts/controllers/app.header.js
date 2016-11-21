(function () {
    'use strict';

    angular.module('app.header', ['ngSanitize'])
            .controller('AppheaderCtrl', ['$scope', '$http', '$aside', '$rootScope', '$cookies', '$state', '$stateParams', '$filter', '$location',
                function ($scope, $http, $aside, $rootScope, $cookies, $state, $stateParams, $filter, $location) {
                    $scope.userName = $cookies.getObject("username");
                    $scope.toDate = function (strDate) {
                        if (!strDate) {
                            return new Date();
                        }
                        var from = strDate.split("/");
                        var f = new Date(from[2], from[0] - 1, from[1]);
                        return f;
                    }
                    $scope.getYesterday = function () {
                        var today = new Date();
                        var yesterday = new Date(today);
                        yesterday.setDate(today.getDate() - 1);
                        return yesterday;
                    }
                    $scope.changeDates = function () {
                        $stateParams.startDate = $('#startDate').val();
                        $stateParams.endDate = $('#endDate').val();
                    };
                    $scope.loadNewUrl = function () {
                        console.log($stateParams);
                        console.log($scope.getCurrentTab());
                        console.log($scope.getCurrentPage());
                        if ($scope.getCurrentPage() === "dashboard") {
                            $state.go("app.dashboard.v1." + $scope.getCurrentTab(), {searchId: ($stateParams.searchId ? $stateParams.searchId : 0), startDate: $('#startDate').val(), endDate: $('#endDate').val()});
                            //$location.path("/app/dashboard/v1/" + $stateParams.searchId + "/" + $scope.getCurrentTab() + "/" + $stateParams.searchId  + "?startDate=" + $('#startDate').val() + "&endDate=" +  $('#endDate').val()  );
                        } else if ($scope.getCurrentPage() === "report") {
                            alert("GO");
                            $state.go("app.report.reports", {searchId: ($stateParams.searchId ? $stateParams.searchId : 0), startDate: $('#startDate').val(), endDate: $('#endDate').val()});
                            alert("Load");
                            //$location.path("/app/report/reports/" + $stateParams.searchId + "?startDate=" + $('#startDate').val() + "&endDate=" +  $('#endDate').val() );
                        } else if ($scope.getCurrentPage() === "dealer") {
                            $state.go("app.admin.dealer", {searchId: ($stateParams.searchId ? $stateParams.searchId : 0), startDate: $('#startDate').val(), endDate: $('#endDate').val()});
                            //$location.path("/app/admin/dealer/" + $stateParams.searchId + "?startDate=" + $('#startDate').val() + "&endDate=" + $('#endDate').val());
                        } else if ($scope.getCurrentPage() === "conversion") {
                            $state.go("app.conversion.conversion", {searchId: ($stateParams.searchId ? $stateParams.searchId : 0), startDate: $('#startDate').val(), endDate: $('#endDate').val()});
//                            $location.path("/app/conversion/conversions/" + $stateParams.searchId + "?startDate=" + $('#startDate').val() + "&endDate=" + $('#endDate').val());
                        } else {
                            $location.path("/" + "?startDate=" + $('#startDate').val() + "&endDate=" + $('#endDate').val());
                        }
                    };
                    $scope.getCurrentPage = function () {
                        var url = window.location.href;
                        if (url.indexOf("dealer") > 0) {
                            return "dealer";
                        }
                        if (url.indexOf("report") > 0) {
                            return "report";
                        }
                        if (url.indexOf("conversion") > 0) {
                            return "conversion";
                        }
                        return "dashboard";
                    };
                    $scope.getCurrentTab = function () {
                        var url = window.location.href;
                        if (url.indexOf("url") > 0) {
                            return "url";
                        }
                        if (url.indexOf("media") > 0) {
                            return "media";
                        }
                        return "summary";
                    };
                    console.log($stateParams.startDate);
                    $http.get('../admin/dealer').success(function (response) {
                        $scope.searchDealers = response.data;
                        $scope.searchDealers.unshift({"siteId": 0, "id": 0, "dealerName": "All Dealers"});
                        $scope.name = $filter('filter')($scope.searchDealers, {siteId: $stateParams.searchId})[0];
                        $scope.selectName = $scope.name.dealerName;
                        console.log($scope.selectName)
                    });
                    $scope.startDate = $stateParams.startDate ? $scope.toDate(decodeURIComponent($stateParams.startDate)) : $scope.getYesterday();
                    $scope.endDate = $stateParams.endDate ? $scope.toDate(decodeURIComponent($stateParams.endDate)) : new Date();

                    $scope.change = function (searchDealer) {
                        $scope.selectName = searchDealer.dealerName;
                        $scope.selectId = searchDealer.id;
                        $stateParams.searchId = searchDealer.id;
                    };

                    $scope.clear = function () {
                        $scope.startDate = null;
                        $scope.endDate = null;
                    };

                    $scope.openStartDate = function () {
                        $scope.popupSd.opened = true;
                    };

                    $scope.openEndDate = function () {
                        $scope.popupEd.opened = true;
                    };
                    $scope.format = 'MM/dd/yyyy';
                    $scope.popupSd = {
                        opened: false
                    };

                    $scope.popupEd = {
                        opened: false
                    };
                    $scope.go = function (date) {
                        console.log(date.startDate);
                    };


                }]);

})();
