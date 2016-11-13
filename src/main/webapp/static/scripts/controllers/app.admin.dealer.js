(function () {
    'use strict';
    angular.module('app.admin.dealer', ['nsPopover','angularUtils.directives.dirPagination'])
            .controller('DealerController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {
<<<<<<< HEAD
                    $scope.count = 50;
                    $scope.total_count = 0;
                    $scope.num = 1;

                    var data = {count: $scope.count, page: $scope.page ? $scope.page : 1}

                    //Dir Pagination
                    $scope.pageChangeHandler = function (num, status) {
                        data.count = 50;
                        data.page = num;
                        data.status = status;
                        console.log('reports page changed to ' + num);
                        console.log(data.count + " " + data.page)
                        $http({method: 'GET', url: '../admin/dealer', params: data}).success(function (response) {
                            $scope.dealers = response.data;
                            $scope.total_count = response.total;
                            $scope.active = response.activeDealers;
                            $scope.inActive = response.inActiveDealers;
                        });
                    };
                    $scope.pageChangeHandler($scope.num);
                    $scope.isActive = function (num,status) {
                        $scope.pageChangeHandler(num, status);
                    }

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
