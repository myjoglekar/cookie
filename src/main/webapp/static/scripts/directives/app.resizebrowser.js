(function () {
    'use strict';

    angular.module('app')
            .directive('resizeBrowser', ['$window', function ($window) {
                    return {
                        link: function (scope) {
                            angular.element($window).on('resize', function (e) {
                                scope.$broadcast('resizeBrowser');
                            });
                            angular.element('.app').on('resize', function (e) {
                                scope.$broadcast('resizeBrowser');
                            });
                        }
                    };
                }]);
})();