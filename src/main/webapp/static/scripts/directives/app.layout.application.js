(function () {
    'use strict';

    angular.module('app')
            .directive('appLayoutApplication', ['$rootScope', function ($rootScope) {
                    return {
                        restrict: 'AC',
                        link: function ($scope, el, attr) {
                            angular.element(el).addClass("layout-fixed");
                            $rootScope.setApplicationLayout(true);
                            $scope.footer = $scope.footer != undefined ? true : false;
                            $scope.$on("$destroy", function handler() {
                                $rootScope.setApplicationLayout(false);
                            });
                        }};
                }]);
})();

