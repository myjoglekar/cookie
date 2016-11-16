(function () {
    'use strict';

    angular.module('app.sidebar', [])
            .controller('AppsidebarCtrl', ['$scope', '$rootScope', '$cookies', '$stateParams', function ($scope, $rootScope, $cookies, $stateParams) {
                    console.log("Aside : " + $stateParams.searchId)
                    $scope.path = $stateParams.searchId;
                    console.log($stateParams.startDate)
//                    if ($stateParams.keyword == ':keyword' || $stateParams.keyword == null) {
//                        $scope.pageSearch = false;
//                    } else {
//                        $scope.pageSearch = true;
//                    }

                    $scope.composeEmail = function () {
                        $rootScope.$broadcast('composeEmail', {});
                    };

                    $scope.isAdmin = $cookies.getObject("isAdmin");

                }]);
})();
