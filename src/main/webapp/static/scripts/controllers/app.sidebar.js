(function () {
    'use strict';

    angular.module('app.sidebar', [])
    .controller('AppsidebarCtrl', ['$scope', '$rootScope', '$cookies', '$stateParams', '$state', '$location',
            function ($scope, $rootScope, $cookies, $stateParams, $state, $location) {
                console.log("Aside : " + $stateParams.searchId)
                $scope.path = $stateParams.searchId;
                $scope.startDate = $stateParams.startDate;
                $scope.endDate = $stateParams.endDate;
                console.log($scope.pathDate)
                console.log($scope.endDate)
//                    if ($stateParams.keyword == ':keyword' || $stateParams.keyword == null) {
//                        $scope.pageSearch = false;
//                    } else {
//                        $scope.pageSearch = true;
//                    }

                $scope.composeEmail = function () {
                    $rootScope.$broadcast('composeEmail', {});
                };

                $scope.isNextPage = function (url) {
//                        console.log(url)
//                        alert(url)
                    if (url === 'dashboard') {
                        $state.go("app.dashboard.v1.summary", {searchId: ($stateParams.searchId ? $stateParams.searchId : 0), startDate: $('#startDate').val(), endDate: $('#endDate').val()});
                    } else if (url === 'dealer') {
                        $state.go("app.admin.dealer", {searchId: ($stateParams.searchId ? $stateParams.searchId : 0), startDate: $('#startDate').val(), endDate: $('#endDate').val()});
                    } else if (url === 'report') {
                        $state.go("app.report.reports", {searchId: ($stateParams.searchId ? $stateParams.searchId : 0), startDate: $('#startDate').val(), endDate: $('#endDate').val()});
                    } else if (url === 'conversion') {
                        $state.go("app.conversion.conversion", {searchId: ($stateParams.searchId ? $stateParams.searchId : 0), startDate: $('#startDate').val(), endDate: $('#endDate').val()});
                    } else {
                        $location.path("/" + "?startDate=" + $('#startDate').val() + "&endDate=" + $('#endDate').val());
                    }
                }


                $scope.isAdmin = $cookies.getObject("isAdmin");

            }]);
})();
