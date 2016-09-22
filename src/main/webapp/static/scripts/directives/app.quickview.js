(function () {
    'use strict';

    angular.module('app')
            .directive('appQuickview', ['$rootScope', function ($rootScope) {
                    return {
                        restrict: 'AC',
                        link: function (scope, el, attr) {
                            
                            function hide() {
                                angular.element(el).removeClass('app-quickview-show').addClass('app-quickview-hide');
                                angular.element('body').removeClass('quickview-modal');
                            }

                            function show() {
                                angular.element(el).removeClass('app-quickview-hide').addClass('app-quickview-show');
                                angular.element('body').addClass('quickview-modal');
                            }

                            $rootScope.toggleQuickview = function () {
                                if (angular.element(el).hasClass('show')) {
                                    hide();
                                }
                                else {
                                    show();
                                }
                            };

                            el.on('click', function (e) {
                                var clickedEl = angular.element(e.target);
                                if (clickedEl.hasClass('app-quickview')) {
                                    hide();
                                }
                            });

                            el.on('click', '.quickview-close', function (e) {
                                hide();
                            });
                        }};
                }]);
})();


