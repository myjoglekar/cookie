(function () {
    'use strict';

    angular.module('app')
            .directive('morris', function () {
                return {
                    restrict: 'A',
                    scope: {
                        options: '=',
                        type: "="
                    },
                    link: function (scope, element, attrs) {
                        var chartDetail = scope.options;
                        chartDetail.element = attrs.id;
                        switch (scope.type) {
                            case 'area':
                                return new Morris.Area(chartDetail);
                                break;
                            case 'bar':
                                return new Morris.Bar(chartDetail);
                                break;
                            case 'line':
                                return new Morris.Line(chartDetail);
                                break;
                            case 'donut':
                                return new Morris.Donut(chartDetail);
                                break;
                        }
                        return 0;
                    }
                };
            });
})();

