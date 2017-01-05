(function () {
    'use strict';
    angular.module('app.admin.dealer', ['nsPopover', 'angularUtils.directives.dirPagination'])
            .controller('DealerController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams) {
                    $scope.count = 50;
                    $scope.total_count = 0;
                    $scope.num = 1; 
                    $scope.urlPath = window.location.host + window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));
                    $scope.selectedFilter = "all";
                    var data = {count: $scope.count, page: $scope.page ? $scope.page : 1}

                     

                    //Dir Pagination
                    $scope.pageChangeHandler = function (num, status) {
                        data.count = 50;
                        data.page = num;
                        data.status = status;
                        console.log('reports page changed to ' + num);
                        console.log($scope.total_count + " " + data.page)
                       // $scope.total = parseFloat($scope.active) + parseFloat($scope.inActive);
                        console.log($scope.totalNumber);
                        $http({method: 'GET', url: '../admin/dealer/' + $stateParams.searchId, params: data}).success(function (response) {
                            $scope.dealers = response.data;
                            $scope.allDealer = response.total;
                            $scope.total_count = response.total;
                            //console.log($scope.allDealer)
                            $scope.active = response.activeDealers;
                            $scope.inActive = response.inActiveDealers;
                           
                        });
                    };
                    $scope.pageChangeHandler($scope.num);
                    $scope.isActive = function (num, status) {
                        $scope.pageChangeHandler(num, status);
                    }

                    $scope.isAllDealer = function (num) {
                        $scope.pageChangeHandler(num);
                    }

                    //Search
                    $scope.startsWith = function (actual, expected) {
                        var lowerStr = (actual + "").toLowerCase();
                        return lowerStr.indexOf(expected.toLowerCase()) === 0;
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
                    $scope.copyScript = function (dealer) {
                        var textBox = $('#copyText' + dealer.id);
                        textBox.select();
                        document.execCommand('copy');
                    }
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
                                //document.execCommand('copy');
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
//            .filter('myfilter', function () {
//                return function (input, text) {
//                    return input.filter(function (dealer) {
//                        return dealer.startsWith(text);
//                    });
//                };
//            });
})();
