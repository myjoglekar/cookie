(function () {
    'use strict';

    angular.module('app')
            .directive('vectorMap', function () {
                return {
                    restrict: 'EAC',
                    scope: {
                        options: '=',
                        width: '=',
                        height: "="
                    },
                    link: function (scope, element, attrs) {
                        if (scope.options !== undefined) {
                            scope.$watch("scope.options", function (n, o) {
                                element.empty();
                                element.show();
                                element.width(scope.width);
                                element.height(scope.height);
                                element.vectorMap(scope.options);
                            });
                        }
                        else {
                            alert('No vectormap options!');
                        }
                    }
                };
            });
})();