(function () {
    'use strict';

    angular.module('app')
            .directive('appSearch', ['$rootScope', function ($rootScope) {
                    return {
                        restrict: 'AC',
                        link: function (scope, el, attr) {
                            function hide() {
                                angular.element(el).removeClass('app-search-show').addClass('app-search-hide');
                                angular.element('body').removeClass('search-modal');
                            }

                            function show() {
                                angular.element(el).removeClass('app-search-hide').addClass('app-search-show');
                                angular.element('body').addClass('search-modal');
                            }

                            $rootScope.toggleSearch = function () {
                                if (angular.element(el).hasClass('app-search-show')) {
                                    hide();
                                }
                                else {
                                    show();
                                }
                            };

                            el.on('click', function (e) {
                                var clickedEl = angular.element(e.target);
                                if (clickedEl.hasClass('app-search')) {
                                    hide();
                                }
                            });

                            el.on('click', '.search-close', function (e) {
                                hide();
                            });
                        }};
                }]);
})();


