(function () {
    'use strict';
    angular.module('app.dashboard', ['nsPopover'])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.path = $stateParams.searchId;

                    $scope.startDate = $stateParams.startDate;
                    $scope.endDate = $stateParams.endDate;
                    var url = window.location.href;
                    var hash = url.substring(url.indexOf('#') + 1);
                    if (hash == "/app/dashboard/v1/"+$stateParams.searchId+"/media/0")
                    {
                        $('.nav-tabs li:eq(1)').addClass('active');
                        $('.nav-tabs li:eq(0)').removeClass('active');
                        $('.nav-tabs li:eq(2)').removeClass('active');
                    }
                    if (hash == "/app/dashboard/v1/"+$stateParams.searchId+"/url/0")
                    {
                        $('.nav-tabs li:eq(1)').removeClass('active');
                        $('.nav-tabs li:eq(0)').removeClass('active');
                        $('.nav-tabs li:eq(2)').addClass('active');
                    }
                    if (hash == "/app/dashboard/v1/"+$stateParams.searchId+"/summary/0")
                    {
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