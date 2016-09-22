(function () {
    'use strict';

    angular.module('app')
            .directive('appPanel', ['$timeout', function ($timeout) {
                    return {
                        restrict: 'AE',
                        scope: true,
                        transclude: true,
                        templateUrl: 'views/common/app.panel.html',
                        link: function ($scope, $element, $attributes) {
                            var panel = angular.element($element.closest('div.panel'));
                            var panelBody = angular.element($element.closest('div.panel-body'));
                            $element.addClass('panel-settings');
                            $scope.collapse = function () {
                                if (panel.hasClass('panel-collapse')) {
                                    panel.removeClass('panel-collapse');
                                }
                                else {
                                    panel.addClass('panel-collapse');
                                }
                                panel.find('.panel-collapse').children('i').toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
                            };
                            $scope.refresh = function () {
                                panel.addClass('panel-progress');
                                var progressHtml = '<div class="panel-progress"><div class="app-progress-bar"></div></div>';
                                panel.append(progressHtml);
                                if ($attributes.panelRefresh !== undefined) {
                                    var promise = $scope.$parent.$eval($attributes.panelRefresh);
                                    promise.then(function (response) {
                                        panel.removeClass('panel-progress');
                                        panel.find('.panel-progress').remove();
                                    });
                                }
                                else {
                                    //for demo
                                    $timeout(function () {
                                        panel.removeClass('panel-progress');
                                        panel.find('.panel-progress').remove();
                                    }, 2000);
                                }
                            };
                            $scope.remove = function () {
                                panel.remove();
                            };
                            $scope.expand = function () {
                                if (panel.hasClass('panel-expand')) {
                                    angular.element('body').removeClass('panel-open');
                                    panel.removeClass('panel-expand');
                                }
                                else {
                                    angular.element('body').addClass('panel-open');
                                    panel.addClass('panel-expand');
                                }
                                panel.find('.panel-expand').children('i').toggleClass('fa-compress').toggleClass('fa-expand');
                            };
                        }
                    };
                }]);
})();





