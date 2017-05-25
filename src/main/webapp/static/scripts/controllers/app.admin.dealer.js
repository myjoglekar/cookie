app.controller('DealerController', ['$scope', '$http', '$filter','$stateParams','DTOptionsBuilder','DTColumnDefBuilder', function ($scope, $http, $filter, $stateParams,DTOptionsBuilder,DTColumnDefBuilder) {
                    //  $scope.count = 50;
                    $scope.count = 2000;
                    $scope.total_count = 0;
                    $scope.num = 1;
                    $scope.urlPath = window.location.host + window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));
                    $scope.selectedFilter = "all";
                    $scope.filename = "Cookie Dealer List";
                    var data = {count: $scope.count, page: $scope.page ? $scope.page : 1}

                        $scope.testingClick = function(dealer)
                    {
                        console.log(dealer);
                    };
                    
                    $scope.dtOptions = DTOptionsBuilder.newOptions().withOption('aaSorting', [[4, 'asc']])
                
                
                $scope.dtColumnDefs = [
   DTColumnDefBuilder.newColumnDef(5).notSortable()
];

                $scope.getHeader = function () {
                    return["Site Id", "Dealer Name", "Email", "Website", "Dealer Address", "City", "State", "Reference ID", "OEM Name", "Last Site Visit", "Status", "Segment Name", "Timezone"];
                }

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
                        data.count = 2000;
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
                            $scope.dealerlistcsv = [];
                            if (response.length == 0) {
                                $scope.dealerEmptyMessage = true
                                $scope.dealerErrorMessage = "No Data Found";
                            } else {
                                $scope.dealers = response.data;
                                $scope.allDealer = response.total;
                                $scope.total_count = response.total;
                                //console.log($scope.allDealer)
                                $scope.active = response.activeDealers;
                                console.log($scope.active)
                                $scope.inActive = response.inActiveDealers;
                                console.log($scope.inActive)
                                $scope.duplicateActive = response.duplicateDealers;
                                console.log($scope.duplicateActive);
                                $scope.cancelledActive = response.cancelledDealers;
                                console.log($scope.cancelledActive);
                                $scope.noBudget = response.noBudgetDealers;
                                console.log($scope.noBudget);
                            }
                            
                            angular.forEach($scope.dealers, function (value, key) {
                                var lastVisitDate;
                                if (value.lastSiteVisit) {
                                    lastVisitDate = $filter('date')(new Date(value.lastSiteVisit), 'MM/dd/yyyy');
                                } else {
                                    lastVisitDate = "";
                                }
                                
                                $scope.dealerlistcsv.push({site_id: value.siteId, dealer_name: value.dealerName, email: value.accountManagerEmail, website: value.website, address: value.dealerAddress, city: value.dealerCity,
                                    state: value.dealerState, reference_id: value.dealerRefId, oem_name: value.oemName, last_site_visit: lastVisitDate,
                                    status: $scope.getFinalStatus(value), segment_name: value.segmentName, timezone: value.timezoneName});
                            })

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
                    $scope.updateCustomStatus = function (dealer, status) {
                        if (dealer.customStatus == 'Cancelled') {
                            $scope.cancelledActive --;
                        } else if (dealer.duplicateStatus == 'Duplicate') {
                            $scope.duplicateActive --;
                        } else if (dealer.mapStatus == 'Inactive') {
                            $scope.noBudget --;
                        } else if (dealer.status == 'Active') {
                            $scope.active --;
                        } else {
                            $scope.inActive --;
                        }
                        
                        dealer.customStatus = status;
                        
                        if (dealer.customStatus == 'Cancelled') {
                            $scope.cancelledActive ++;
                        } else if (dealer.duplicateStatus == 'Duplicate') {
                            $scope.duplicateActive ++;
                        } else if (dealer.mapStatus == 'Inactive') {
                            $scope.noBudget ++;
                        } else if (dealer.status == 'Active') {
                            $scope.active ++;
                        } else {
                            $scope.inActive ++;
                        }
                        dealer.customComment = $('textarea.dealer' + dealer.id).val();
                        $http({method: 'POST', 
                            url: '../admin/dealer/updateCustomStatus', 
                            data: dealer,
                            headers: {'Content-Type': 'application/json'}
                        }).success(function (response) {
                            console.log("successfully updated dealer");
                        });
                    }
                    $scope.getFinalStatus = function (dealer) {
                        var output = dealer.status;
                        if (dealer.customStatus === 'Cancelled') {
                            output = "Marked Void";
                        } else if (dealer.mapStatus === 'Inactive' && dealer.status === 'InActive') {
                            output = "Cancelled";
                        } else if (dealer.duplicateStatus === 'Duplicate') {
                            output = "Duplicate";
                        }
                        return output;
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

