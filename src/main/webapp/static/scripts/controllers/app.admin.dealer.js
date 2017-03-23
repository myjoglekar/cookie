app.controller('DealerController', ['$scope', '$http', '$stateParams', function ($scope, $http, $stateParams,DTOptionsBuilder) {
                    //  $scope.count = 50;
                    //$scope.count = 2000;
                    $scope.total_count = 0;
                    $scope.num = 1;
                    $scope.urlPath = window.location.host + window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));
                    $scope.selectedFilter = "all";
                    var data = {count: $scope.count, page: $scope.page ? $scope.page : 1}

                        $scope.testingClick = function(dealer)
                    {
                        console.log(dealer);
                    };
                    
                    $scope.dtOptions = DTOptionsBuilder.newOptions().withOption('aaSorting', [[4, 'desc']])
                

//                     $scope.orderByField = 'status';
//                    $scope.reverseSort = true;

                    $scope.sortType = 'status';
                    $scope.sortReverse = false;
                    $scope.getUrl = function (dealer) {
                        var returnString = "http://" + $scope.urlPath;
                        if (dealer.website.startsWith("https")) {
                            returnString = returnString.replace("http:", "https:");
                            returnString = returnString.replace("8080", "8443");
                        } else {
                            returnString = returnString.replace("https:", "http:");
                            returnString = returnString.replace("8443", "8080");
                        }
                        return returnString;
                    }
                    //Dir Pagination
                    $scope.pageChangeHandler = function (num, status) {
                        // data.count = 50;
                      //  data.count = 2000;
                        data.page = num;
                        data.status = status;
                        console.log('reports page changed to ' + num);
                        console.log($scope.total_count + " " + data.page)
                        // $scope.total = parseFloat($scope.active) + parseFloat($scope.inActive);
                        console.log($scope.totalNumber);
                        $scope.dealerData = true;
                        //$http({method: 'GET', url: 'http://ec2-35-163-41-230.us-west-2.compute.amazonaws.com:8080/cookie/admin/dealer'}).success(function (response) {
                        //$http({method: 'GET', url: '../admin/dealer/' + $stateParams.searchId, params: data}).success(function (response) {
                        $http({method: 'GET', url: '../admin/dealer/0', params: data}).success(function (response) {
                            $scope.dealerData = false;
                            if (response.length == 0) {
                                $scope.dealerEmptyMessage = true
                                $scope.dealerErrorMessage = "No Data Found";
                            } else {
                                $scope.dealers = response.data;
                                $scope.allDealer = response.total;
                                $scope.total_count = response.total;
                                //console.log($scope.allDealer)
                                $scope.active = response.activeDealers;
                                $scope.inActive = response.inActiveDealers;
                            }

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
                        ascending: false
                    };
                    $scope.changeSorting = function (column) {
                        var sort = $scope.sort;
                        if (sort.column === column) {
                            sort.descending = !sort.descending;
                        } else {
                            sort.column = column;
                            sort.descending = true;
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

