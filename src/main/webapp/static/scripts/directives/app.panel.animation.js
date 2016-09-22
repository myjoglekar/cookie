(function () {
    'use strict';

    angular.module('app')
            .directive('appPanelAnimation', ['$timeout', function ($timeout) {
                    return {
                        restrict: 'A',
                        scope: true,
                        controller: function ($scope, $element) {
                            var panels = angular.element($element).find('.panel');
                            var offset = 0.05;
                            var delay = 0.2;
                            angular.forEach(panels, function (panel) {
                                delay += offset;
                                var panel = angular.element(panel);
                                panel.css('animation-delay', delay + "s");
                                panel.addClass("animated zoomIn");
                            });
                        }
                    };
                }]);
})();

