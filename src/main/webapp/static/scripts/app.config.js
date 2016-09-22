(function () {
    'use strict';

    angular.module('app')
            .config(['$ocLazyLoadProvider', '$translateProvider', '_MODULES', '_CONTROLLERS',
                function ($ocLazyLoadProvider, $translateProvider, _MODULES, _CONTROLLERS) {

                    $ocLazyLoadProvider.config({
                        debug: true,
                        events: true,
                        loadedModules: ['app'],
                        modules: _MODULES.concat(_CONTROLLERS)
                    });

                    $translateProvider.useStaticFilesLoader({
                        prefix: 'l10n/locale-',
                        suffix: '.json'
                    });

                    $translateProvider.preferredLanguage('en_US');
                }]);
})(); 