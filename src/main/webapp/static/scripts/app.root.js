(function () {
    'use strict';

    angular.module('app')
            .controller('RootCtrl', RootCtrl);

    RootCtrl.$inject = ['$scope', '$rootScope', '$localStorage', '$timeout', '$translate', 'Fullscreen', '$q'];

    function RootCtrl($scope, $rootScope, $localStorage, $timeout, $translate, Fullscreen, $q) {

        //mobile xs view flag
        $rootScope.mobileLayout = false;
        //app fullscreen flag
        $rootScope.inFullScreen = false;
        //saved settings
        $scope.saveApp = {};

        //return the breakpoint type (screen-xs,screen-md...)
        $rootScope.getLayoutType = function () {
            return $scope.breakpoint;
        };

        //aside fold/open
//        $rootScope.toggleSideBarMobile = function () {
//            $rootScope.app.settings.asideFolded = !$rootScope.app.settings.asideFolded;
//            if ($rootScope.app.settings.asideHover) {
//                $rootScope.app.settings.asideHover = false;
//                $rootScope.app.settings.asideFolded = false;
//            }
//        };
        
        
        $rootScope.toggleSideBar = function () {
            $rootScope.app.settings.asideFolded = !$rootScope.app.settings.asideFolded;
            if ($rootScope.app.settings.asideHover) {
                $rootScope.app.settings.asideHover = false;
                $rootScope.app.settings.asideFolded = false;
            }
        };

        //default settings structure
        $rootScope.getDefaultSettings = function () {
            return {
                showSettingsBtn: true,
                settings: {
                    theme: 'themeBlue',
                    asideFolded: true,
                    showApps: false,
                    asideDocked: false,
                    layoutBoxed: false,
                    headerFixed: true,
                    footerFixed: true,
                    asideFixed: false,
                    asideHover: false,
                    layoutApplication: false,
                    lang: 'en_US'
                },
                themes: [
                    {
                        name: 'blue theme',
                        theme: 'themeBlue'
                    },
                    {
                        name: 'grey theme',
                        theme: 'themeGrey'
                    },
                    {
                        name: 'white theme',
                        theme: 'themeWhite'
                    },
                    {
                        name: 'dark theme',
                        theme: 'themeDark'
                    },
                    {
                        name: 'brown theme',
                        theme: 'themeBrown'
                    },
                    {
                        name: 'pink theme',
                        theme: 'themePink'
                    }
                ]
            };
        };
        //set application/desktop layout 
        $rootScope.setApplicationLayout = function (bool, footer) {
            if (bool) {
                $scope.saveSettings();
                $rootScope.app.settings.asideFixed = true;
                $rootScope.app.settings.headerFixed = true;
                $rootScope.app.settings.layoutApplication = true;
                $rootScope.app.settings.footerFixed = footer;
            } else {
                if ($scope.saveApp) {
                    $rootScope.app.settings.asideFixed = $scope.saveApp.settings.asideFixed;
                    $rootScope.app.settings.headerFixed = $scope.saveApp.settings.headerFixed;
                    $rootScope.app.settings.footerFixed = $scope.saveApp.settings.footerFixed;
                }
                $rootScope.app.settings.layoutApplication = false;
            }
            $localStorage.app = $rootScope.app;
        };

        //save
        $scope.saveSettings = function () {
            $scope.saveApp = angular.copy($rootScope.app);
        };

        //change lang
        $rootScope.changeLanguage = function (key) {
            $translate.use(key);
        };

        //clear localstorage
        $rootScope.clearLocalStorage = function () {
            var settings = $rootScope.getDefaultSettings();
            if ($rootScope.app.settings.layoutApplication) {
                settings.settings.layoutApplication = true;
            }
            $rootScope.app = settings;
            $scope.saveSettings();
        };

        //set fullscreen
        $rootScope.goFullscreen = function () {
            // Fullscreen
            if (Fullscreen.isEnabled()) {
                Fullscreen.cancel();
                $rootScope.inFullScreen = false;
            } else {
                Fullscreen.all();
                $rootScope.inFullScreen = true;
            }
        };

        //browser-resize
        $scope.$on('breakpointChange', function (event, breakpoint, oldClass) {
            if (breakpoint.class === 'screen-xs' || breakpoint.class === 'screen-sm') {
                $scope.saveSettings();
                $rootScope.app.settings.asideFolded = false;
                $rootScope.app.settings.asideDocked = false;
                $rootScope.app.settings.layoutBoxed = false;
                $rootScope.app.settings.asideHover = false;
                $rootScope.app.settings.headerFixed = true;
                breakpoint.class === 'screen-xs' ? $rootScope.mobileLayout = true : "";
            } else {
                if ($scope.saveApp) {
                    $rootScope.app.settings.asideHover = $scope.saveApp.settings.asideHover;
                    $rootScope.app.settings.asideFolded = $scope.saveApp.settings.asideFolded;
                    $rootScope.app.settings.asideDocked = $scope.saveApp.settings.asideDocked;
                    $rootScope.app.settings.headerFixed = $scope.saveApp.settings.headerFixed;
                }
                $rootScope.mobileLayout = false;
            }
        });

        //set the rootscope
        $rootScope.app = $rootScope.getDefaultSettings();

        //watching for changes
        $scope.$watch('app', function () {
            //in case of asideHover
            if ($rootScope.app.settings.asideHover) {
                $rootScope.app.settings.asideFolded = true;
                $rootScope.app.settings.asideDocked = false;
                $rootScope.app.settings.asideFixed = false;
                $rootScope.app.settings.headerFixed = true;
            }
            //save to localstorage
            $localStorage.app = $rootScope.app;
            $scope.$broadcast('appLayoutChanged');
        }, true);

        //load localstorage if is defined
        if (angular.isDefined($localStorage.app) && angular.isDefined($localStorage.app.settings)) {
            $rootScope.app = $localStorage.app;
            $rootScope.app.settings.layoutApplication = false;
            $rootScope.app.settings.lang && $rootScope.app.settings.lang !== 'en_US' ? $rootScope.changeLanguage($rootScope.app.settings.lang) : "";
        }

        $timeout(function () {
            if ($scope.breakpoint.class === 'screen-xs') {
                $rootScope.mobileLayout = true;
            }
        }, 1000);

        //dummy promise simulation for refresh of the panels
        $rootScope.getDummyPromise = function () {
            var defer = $q.defer();
            // simulated async function
            $timeout(function () {
                defer.resolve({})
            }, 3000);
            return defer.promise;
        };

    }

})();