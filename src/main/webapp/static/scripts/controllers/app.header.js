(function () {
    'use strict';

    angular.module('app.header', ['ngSanitize'])
            .controller('AppheaderCtrl', ['$scope', '$http', '$aside', '$rootScope', '$cookies', '$state', '$stateParams', '$filter', '$location',
                function ($scope, $http, $aside, $rootScope, $cookies, $state, $stateParams, $filter, $location) {
                    $scope.userName = $cookies.getObject("username");

                    // console.log($rootScope.location = $location)

                    //$scope.searchDealers = [{dealerName: "All", siteId: 0}]
//                    $scope.searchDealerName = $stateParams.searchId;
                    console.log($stateParams.startDate);
                    $http.get('../admin/dealer').success(function (response) {
                        $scope.searchDealers = response.data;
                        $scope.searchDealers.unshift({"siteId": 0, "id": 0, "dealerName": "All Dealers"});
                        $scope.name = $filter('filter')($scope.searchDealers, {siteId: $stateParams.searchId})[0];
                        $scope.selectName = $scope.name.dealerName;
                        console.log($scope.selectName)
                    });


                    //$scope.today = function () {
                    $scope.startDate = new Date();
                    $scope.startDate.setDate($scope.startDate.getDate() - 1);
                    $scope.endDate = new Date();
                    $scope.defaultDate = new Date();
                    $scope.defaultDate.setDate($scope.defaultDate.getDate() - 1);

                    var foo = {
                        "results": [{
                                "id": 1,
                                "name": "Test"
                            }, {
                                "id": 2,
                                "name": "Beispiel"
                            }, {
                                "id": 3,
                                "name": "Sample"
                            }]
                    };

                    //$scope.selectName = "All Dealer";
                    $scope.change = function (dashboard) {
                        $scope.selectName = dashboard.dealerName;
                        $scope.selectId= dashboard.id;
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

                    var url = window.location.href;
                    var hash = url.substring(url.indexOf('#') + 1);
                    //$scope.searchUrl="app.dashboard.v1.summary"
                    if (hash === "/app/dashboard/v1/" + $stateParams.searchId + "/summary/" + $stateParams.searchId)
                    {
                        $scope.searchUrl = "dashboard.v1.summary";
                        $('.nav-tabs li:eq(1)').removeClass('active');
                        $('.nav-tabs li:eq(0)').addClass('active');
                        $('.nav-tabs li:eq(2)').removeClass('active');
                    }
                    if (hash === "/app/dashboard/v1/" + $stateParams.searchId + "/media/" + $stateParams.searchId)
                    {
                        $scope.searchUrl = "dashboard.v1.media";
                        $('.nav-tabs li:eq(1)').addClass('active');
                        $('.nav-tabs li:eq(0)').removeClass('active');
                        $('.nav-tabs li:eq(2)').removeClass('active');
                    }
                    if (hash === "/app/dashboard/v1/" + $stateParams.searchId + "/url/" + $stateParams.searchId)
                    {
                        $scope.searchUrl = "dashboard.v1.url";
                        $('.nav-tabs li:eq(1)').removeClass('active');
                        $('.nav-tabs li:eq(0)').removeClass('active');
                        $('.nav-tabs li:eq(2)').addClass('active');
                    }
                    if (hash === "/app/admin/dealer/" + $stateParams.searchId)
                    {
                        $scope.searchUrl = "admin.dealer";

                    }
                    if (hash === "/app/report/reports/" + $stateParams.searchId)
                    {
                        $scope.searchUrl = "report.reports";

                    }
                    if (hash === "/app/conversion/conversions/" + $stateParams.searchId)
                    {
                        $scope.searchUrl = "conversion.conversion";
                    }
                    console.log("Url : " + hash)
                }]);

})();
