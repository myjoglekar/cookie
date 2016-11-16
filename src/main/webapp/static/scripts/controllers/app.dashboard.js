(function () {
    'use strict';
    angular.module('app.dashboard', ['nsPopover'])
            .controller('DashboardCtrl', ['$scope', '$location', 'toaster', '$http', '$stateParams',
                function ($scope, $location, toaster, $http, $stateParams) {
                    $scope.path = $stateParams.searchId;
                    
                    $scope.startDate = $stateParams.startDate;
                    $scope.endDate = $stateParams.endDate;
                    
                    $('.nav-tabs li').click(function (e) {
                        $(this).siblings().removeClass('active')
                        $(this).addClass('active')
                    })
                }])
})();