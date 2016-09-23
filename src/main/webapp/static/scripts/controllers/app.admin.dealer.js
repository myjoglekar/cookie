(function () {
    'use strict';
    angular.module('app.admin.dealer', [])
            .controller('DealerController', ['$scope', '$http', function ($scope, $http) {
                    $http.get('../admin/dealer').success(function(response){
                        $scope.dealers = response;
                    })

                    /*Header Sortable*/
                    $scope.sort = {
                        column: '',
                        descending: false
                    };
                    $scope.changeSorting = function (column) {
                        var sort = $scope.sort;
                        if (sort.column === column) {
                            sort.descending = !sort.descending;
                        } else {
                            sort.column = column;
                            sort.descending = false;
                        }
                    };
                    
                }])            
})();
