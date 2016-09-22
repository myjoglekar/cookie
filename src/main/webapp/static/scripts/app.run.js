(function () {
    'use strict';

    angular.module('app')
            .run(['$rootScope', '$state', '$stateParams', '$log', 'editableOptions', 'cfpLoadingBar', 'ScrollTo','$timeout',
                function ($rootScope, $state, $stateParams, $log, editableOptions, cfpLoadingBar, ScrollTo,$timeout) {

                    $rootScope.$state = $state;
                    $rootScope.$stateParams = $stateParams;

                    editableOptions.theme = 'bs3';

                    $rootScope.$on('$stateChangeStart', function () {
                        cfpLoadingBar.start();
                    });

                    $rootScope.$on('$stateChangeSuccess', function () {
                        ScrollTo.idOrName();
                        cfpLoadingBar.complete();
                        if(!$rootScope.systemLoaded){
                            //first init
                            $timeout(function () {
                                $rootScope.systemLoaded = true;
                            }, 1000);
                        }
                    });

                    $rootScope.$on('$stateChangeError', function (event, params) {
                        $log.error(event);
                        $log.error(params);
                        cfpLoadingBar.complete();
                    });
                }]);
})();