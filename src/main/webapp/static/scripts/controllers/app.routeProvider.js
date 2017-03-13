app.config(function ($stateProvider, $urlRouterProvider) {

    $stateProvider
            .state('app', {
                url: '/app',
                templateUrl: 'views/app.html'
            })
            
            .state('app.dashboard', {
                url: '/dashboard',
                template: '<div data-ui-view></div>'
            })
            
            .state('app.dashboard.v1', {
                url: '/v1/:searchId?startDate/:endDate',
                templateUrl: 'views/dashboard/dashboard.html'
            })

            .state('app.header', {
                url: '/app.header',
                templateUrl: 'views/app.header.html'
            })

            .state('app.sidebar', {
                url: '/app.sidebar',
                templateUrl: 'views/app.sidebar.html'
            })

            .state('app.dashboard.v1.summary', {
                url: '/summary/:searchId',
                templateUrl: 'views/dashboard/summary.html'
            })

            .state('app.dashboard.v1.media', {
                url: '/media/:searchId',
                templateUrl: 'views/dashboard/media.html'
            })

            .state('app.dashboard.v1.url', {
                url: '/url/:searchId',
                templateUrl: 'views/dashboard/url.html'
            })
            
            .state('app.admin', {
                url: '/admin',
                templateUrl: 'views/admin/dealer.html'
            })

            .state('app.admin.dealer', {
                url: '/dealer/:searchId?startDate/:endDate',
                templateUrl: 'views/admin/dealer.html'
            })
            
            .state('app.report', {
                url: '/report',
                template: '<div data-ui-view></div>'
            })

            .state('app.report.reports', {
                url: '/app/report/reports',
                templateUrl: 'views/reports/report.html'
            })
            
            .state('app.conversion', {
                url: '/conversion',
                template: '<div data-ui-view></div>'
            })
            
            .state('app.conversion.conversion', {
                url: '/app/conversion/conversion',
                templateUrl: 'views/reports/conversions.html'
            });
            
            $urlRouterProvider.otherwise("/app/dashboard");
    /*$urlRouterProvider.otherwise("/app/dashboard/v1/:searchId/summary/");*/
})
