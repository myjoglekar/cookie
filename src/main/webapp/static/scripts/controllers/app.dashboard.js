(function () {
    'use strict';
    angular.module('app.dashboard', ['nsPopover'])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.path = $stateParams.searchId;

                    $scope.startDate = $stateParams.startDate;
                    $scope.endDate = $stateParams.endDate;
                    $(document).ready(function (e) {
                        
                        var url = window.location.href;
                        var hash = url.substring(url.indexOf('#') + 1);

//                        if (hash == "/app/dashboard/v1/" + $stateParams.searchId + "/summary/" + $stateParams.searchId)
//                        {
//                            $('.nav-tabs li:eq(1)').removeClass('active');
//                            $('.nav-tabs li:eq(0)').addClass('active');
//                            $('.nav-tabs li:eq(2)').removeClass('active');
//                        }
//                        if (hash == "/app/dashboard/v1/" + $stateParams.searchId + "/media/" + $stateParams.searchId)
//                        {
//                            $('.nav-tabs li:eq(1)').addClass('active');
//                            $('.nav-tabs li:eq(0)').removeClass('active');
//                            $('.nav-tabs li:eq(2)').removeClass('active');
//                        }
//                        if (hash == "/app/dashboard/v1/" + $stateParams.searchId + "/url/" + $stateParams.searchId)
//                        {
//                            $('.nav-tabs li:eq(1)').removeClass('active');
//                            $('.nav-tabs li:eq(0)').removeClass('active');
//                            $('.nav-tabs li:eq(2)').addClass('active');
//                        }


                        $('.nav-tabs li').on('click', function (e) {
//                            $(this).siblings().removeClass('active')
//                            $(this).addClass('active')
                            var url = window.location.href;
                            var hash = url.substring(url.indexOf('#') + 1);
                            //alert(hash)
                            if (hash == "/app/dashboard/v1/" + $stateParams.searchId + "/summary/" + $stateParams.searchId)
                            {
                                $('.nav-tabs li:eq(1)').removeClass('active');
                                $('.nav-tabs li:eq(0)').addClass('active');
                                $('.nav-tabs li:eq(2)').removeClass('active');
                            }
                            if (hash == "/app/dashboard/v1/" + $stateParams.searchId + "/media/" + $stateParams.searchId)
                            {
                                $('.nav-tabs li:eq(1)').addClass('active');
                                $('.nav-tabs li:eq(0)').removeClass('active');
                                $('.nav-tabs li:eq(2)').removeClass('active');
                            }
                            if (hash == "/app/dashboard/v1/" + $stateParams.searchId + "/url/" + $stateParams.searchId)
                            {
                                $('.nav-tabs li:eq(1)').removeClass('active');
                                $('.nav-tabs li:eq(0)').removeClass('active');
                                $('.nav-tabs li:eq(2)').addClass('active');
                            }

                        })


                    });

                }])
})();