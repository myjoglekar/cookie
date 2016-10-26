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
                }]);

})();
