(function () {
    'use strict';

    angular.module('app.header', ['ngSanitize'])
            .controller('AppheaderCtrl', ['$scope', '$http', '$aside', '$rootScope', '$cookies', '$state', '$stateParams', function ($scope, $http, $aside, $rootScope, $cookies, $state, $stateParams) {
                    $scope.userName = $cookies.getObject("username");
                    //$scope.searchDealers = [{dealerName: "All", siteId: 0}]
                    $scope.searchDealerName = $stateParams.searchId;
                    $http.get('../admin/dealer').success(function (response) {
                        $scope.searchDealers = response;
                    });

                    //$scope.today = function () {
                    $scope.startDate = new Date();
                    $scope.startDate.setDate($scope.startDate.getDate() - 1);
                    $scope.endDate = new Date();
                    //};

                    $scope.defaultDate = new Date();
                    $scope.defaultDate.setDate($scope.defaultDate.getDate() - 1);
                    //$scope.today();
//
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
                        console.log(date.startDate)
                    }

//                    var tomorrow = new Date();
//                    tomorrow.setDate(tomorrow.getDate() + 1);
//                    var afterTomorrow = new Date();
//                    afterTomorrow.setDate(tomorrow.getDate() + 1);
//                    $scope.events = [
//                        {
//                            date: tomorrow,
//                            status: 'full'
//                        },
//                        {
//                            date: afterTomorrow,
//                            status: 'partially'
//                        }
//                    ];

//                    function getDayClass(data) {
//                        var date = data.date,
//                                mode = data.mode;
//                        if (mode === 'day') {
//                            var dayToCheck = new Date(date).setHours(0, 0, 0, 0);
//
//                            for (var i = 0; i < $scope.events.length; i++) {
//                                var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);
//
//                                if (dayToCheck === currentDay) {
//                                    return $scope.events[i].status;
//                                }
//                            }
//                        }
//
//                        return '';
//                    }
                }]);

})();
