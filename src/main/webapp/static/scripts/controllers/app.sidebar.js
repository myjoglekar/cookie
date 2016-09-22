(function () {
    'use strict';

    angular.module('app.sidebar', [])
            .controller('AppsidebarCtrl', ['$scope', '$rootScope', '$cookies', '$stateParams', function ($scope, $rootScope, $cookies, $stateParams) {
                    
                    if ($stateParams.keyword == ':keyword' || $stateParams.keyword == null) {
                        $scope.pageSearch = false;
                    } else {
                        $scope.pageSearch = true;
                    }

                    $scope.composeEmail = function () {
                        $rootScope.$broadcast('composeEmail', {});
                    };

                    $scope.isAdmin = $cookies.getObject("isAdmin");

                }]);
})();
