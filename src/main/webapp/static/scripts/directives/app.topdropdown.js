(function () {
    'use strict';

    angular.module('app')
            .directive('appTopDropDown', ['$rootScope', function ($rootScope) {
                    return {
                        restrict: 'AC',
                        link: function (scope, el, attr) {
                            function hide() {
                                angular.element(el).removeClass('app-top-dropdown-show').addClass('app-top-dropdown-hide');
                            }

                            function show() {
                                angular.element(el).removeClass('app-top-dropdown-hide').addClass('app-top-dropdown-show');
                            }

                            $rootScope.toggleTopDropDown = function () {
                                if (angular.element(el).hasClass('app-top-dropdown-show')) {
                                    hide();
                                }
                                else {
                                    show();
                                }
                            };

                            el.on('click', function (e) {
                                var clickedEl = angular.element(e.target);
                                if (clickedEl.hasClass('app-top-dropdown')) {
                                    hide();
                                }
                            });

                            el.on('click', 'a', function (e) {
                                hide();
                            });
                        }};
                }]);
})();


