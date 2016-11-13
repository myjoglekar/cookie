(function () {
    'use strict';
    angular.module('app.admin.dealer', ['angularUtils.directives.dirPagination'])
            .controller('DealerController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {
//                    $scope.currentPage = 1;
//                    $scope.pageSize = 10;
//                    $scope.reports = [];
//                    console.log("Dealer : " + $stateParams.searchId)
//                    if (!$stateParams.searchId) {
//                        $stateParams.searchId = 0;
//                    }
                    
                    
                    $scope.count = 50;
                    $scope.total_count = 0;
                    $scope.num = 1;
                    var data = {count: $scope.count, page: $scope.page ? $scope.page : 1};
                    $scope.pageChangeHandler = function (num) {
                        data.count = 50;
                        data.page = num;
                        $http({method: 'GET', url: '../admin/dealer/' + $stateParams.searchId, params: data}).success(function (response) {
                            $scope.dealers = response.data;
                            $scope.total_count = response.count;
                            console.log("Data : "+$scope.visitReports)
                            console.log("Count : "+$scope.total_count)
                        });
                        console.log('reports page changed to ' + num);
                    };
                    $scope.pageChangeHandler($scope.num);

                    //Dir Pagination       
                    $scope.pageChangeHandler = function (num) {
                        console.log('reports page changed to ' + num);
                    };

//                    $http.get("../admin/dealer").success(function (response) {
//                        $scope.dealers = response;
//                    });

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

                    //Copy Text code
                    document.body.addEventListener('click', copy, true);
                    function copy(e) {
                        var
                                t = e.target,
                                c = t.dataset.copytarget,
                                inp = (c ? document.querySelector(c) : null);
                        if (inp && inp.select) {
                            inp.select();
                            try {
                                document.execCommand('copy');
                                inp.blur();
                                t.classList.add('copied');
                                setTimeout(function () {
                                    t.classList.remove('copied');
                                }, 1500);
                            } catch (err) {
                                alert('please press Ctrl/Cmd+C to copy');
                            }
                        }
                    }

                }]);
})();
