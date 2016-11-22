(function () {
    'use strict';
    angular.module('app.dashboard', ['nsPopover'])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.path = $stateParams.searchId;
                    
                    var url=window.location.href;
                    var hash = url.substring(url.indexOf('#')+1);
                    if(hash=="/app/dashboard/v1/0/media/0")
                    {
                         $scope.searchUrl="app.dashboard.v1.media";
                         $('.nav-tabs li:eq(1)').addClass('active');
                         $('.nav-tabs li:eq(0)').removeClass('active');
                          $('.nav-tabs li:eq(2)').removeClass('active');
                    }
                    if(hash=="/app/dashboard/v1/0/url/0")
                    {
                         $scope.searchUrl="app.dashboard.v1.url";
                        $('.nav-tabs li:eq(1)').removeClass('active');
                         $('.nav-tabs li:eq(0)').removeClass('active');
                          $('.nav-tabs li:eq(2)').addClass('active');
                    }
                    if(hash=="/app/dashboard/v1/0/summary/0")
                    {
                         $scope.searchUrl="app.dashboard.v1.summary";
                         $('.nav-tabs li:eq(1)').removeClass('active');
                         $('.nav-tabs li:eq(0)').addClass('active');
                          $('.nav-tabs li:eq(2)').removeClass('active');
                    }
                    $('.nav-tabs li').click(function (e) {
                        $(this).siblings().removeClass('active')
                        $(this).addClass('active')
                    })
                }])

})();
