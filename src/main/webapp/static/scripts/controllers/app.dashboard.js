(function () {
    'use strict';
    angular.module('app.dashboard', ['nsPopover'])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.path = $stateParams.searchId;

                    $scope.startDate = $stateParams.startDate;
                    $scope.endDate = $stateParams.endDate;
                    
                    console.log($stateParams.tab)

//                    var url = window.location.href;
//                    $scope.activeTabUrl = ((url.substring(url.indexOf('app')).split("?startDate", 1)) + "?").split("?", 1);
//                    console.log("active Tab : " + $scope.activeTabUrl)
//                    
//                    console.log("Check : "+$scope.activeTabUrl == "app/dashboard/v1/" + $stateParams.searchId + "/summary/" + $stateParams.searchId)
                    
//                    $scope.isActive = function (activeMessage) {
//                        if (activeMessage == 'isSummary' || $scope.activeTabUrl == "app/dashboard/v1/" + $stateParams.searchId + "/summary/" + $stateParams.searchId) {
//                            $scope.activeSummary = 'active';
//                            alert("summary")
//                        } else if (activeMessage == 'isMedia' || $scope.activeTabUrl == "app/dashboard/v1/" + $stateParams.searchId + "/media/" + $stateParams.searchId) {
//                            $scope.activeMedia = 'active';
//                            alert("media")
//                        } else if (activeMessage == 'isUrl' || $scope.activeTabUrl == "app/dashboard/v1/" + $stateParams.searchId + "/url/" + $stateParams.searchId) {
//                            $scope.activeUrl = 'active';
//                            alert("url")
//                        }
//                    }


                    $(document).ready(function (e) {

                        var url = window.location.href;
                            var hash = url.substring(url.indexOf('#') + 1);
                            //alert(hash)
                        if (hash == "/app/dashboard/v1/" + $stateParams.searchId + "/summary/" + $stateParams.searchId)
                        {
                            $('.nav-tabs li:eq(0)').addClass('active');
                            $('.nav-tabs li:eq(1)').removeClass('active');
                            $('.nav-tabs li:eq(2)').removeClass('active');
                        }
                        if (hash == "/app/dashboard/v1/" + $stateParams.searchId + "/media/" + $stateParams.searchId)
                        {
                            $('.nav-tabs li:eq(0)').removeClass('active');
                            $('.nav-tabs li:eq(1)').addClass('active');
                            $('.nav-tabs li:eq(2)').removeClass('active');
                        }
                        if (hash == "/app/dashboard/v1/" + $stateParams.searchId + "/url/" + $stateParams.searchId)
                        {
                            $('.nav-tabs li:eq(0)').removeClass('active');
                            $('.nav-tabs li:eq(1)').removeClass('active');
                            $('.nav-tabs li:eq(2)').addClass('active');
                        }



                    });

                }])
})();