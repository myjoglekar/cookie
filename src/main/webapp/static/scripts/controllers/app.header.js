(function () {
    'use strict';

    angular.module('app.header', ['ngSanitize'])
            .controller('AppheaderCtrl', ['$scope', '$http', '$aside', '$rootScope', '$cookies', '$state', '$stateParams', function ($scope, $http, $aside, $rootScope, $cookies, $state, $stateParams) {
                    $scope.userName = $cookies.getObject("username");
                    //$scope.searchDealers = [{dealerName: "All", siteId: 0}]
//                    $scope.searchDealerName = $stateParams.searchId;
                    $http.get('../admin/dealer').success(function (response) {
                        $scope.searchDealers = response.data;
                    });

                    //$scope.today = function () {
                    $scope.startDate = new Date();
                    $scope.startDate.setDate($scope.startDate.getDate() - 1);
                    $scope.endDate = new Date();

                    $scope.defaultDate = new Date();
                    $scope.defaultDate.setDate($scope.defaultDate.getDate() - 1);
//
                    $scope.selectName = "All Dealer";
                    $scope.change = function (dashboard) {
                        $scope.selectName = dashboard.dealerName;
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
                    
                    var url=window.location.href;
                   
                    var hash = url.substring(url.indexOf('#')+1);
                    if(hash=="/app/dashboard/v1/0/media/0")
                    {
                         $scope.searchUrl="app.dashboard.v1.media";
    
                    }
                    if(hash=="/app/dashboard/v1/0/url/0")
                    {
                         $scope.searchUrl="app.dashboard.v1.url";
                      
                    }
                    if(hash=="/app/dashboard/v1/0/summary/0")
                    {
                         $scope.searchUrl="app.dashboard.v1.summary";
                        
                    }
                    
                    
                    
                    
                    
                    
                }]);

})();

