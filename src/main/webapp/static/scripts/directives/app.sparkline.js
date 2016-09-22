(function () {
    'use strict';

    angular.module('app')
            .directive('sparkLine', function () {
                return {
                    restrict: 'E',
                    scope: {
                        data: '=',
                        options: '='
                    },
                    link: function (scope, element, attrs) {
                        scope.$watch(scope.data, function () {
                            render();
                        });
                        scope.$watch(scope.options, function () {
                            render();
                        });
                        scope.$on('resizeBrowser', function (e) {
                            render();
                        });
                        var render = function () {
                            $(element).sparkline(scope.data, scope.options);
                        };
                    }
                };
            });
})();

