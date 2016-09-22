(function () {
    'use strict';
    angular.module('app.admin.settings', [])
            .controller('SettingsController', function ($scope, $http) {

                $scope.getItems = function () {
                    $http.get('../admin/settings').success(function (response) {
                        $scope.settings = response;
                    });
                };
                $scope.getItems();

                $scope.save = function (setting) {
                    console.log(setting)
                    $http({method: setting.id ? 'PUT' : 'POST', url: '../admin/settings', data: setting}).success(function (response) {
                        $scope.getItems();
                    });
                };
            });
})();
