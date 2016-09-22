(function () {
    'use strict';
    angular.module('app.admin.url', ['ngSanitize', 'ui.select', 'selectize'])
            .controller('UrlController', function ($scope, $filter, $http, $templateCache, $timeout, $interval) {
                $scope.getList = function (urlItem) {
                    $http({method: 'GET', url: '../admin/urlConfig'}).success(function (response) {
                        $scope.urlItems = response;
                    });
                };
                $scope.getList();

                $scope.add = function () {
                    $scope.urlItems.unshift({isEdit: true});
                };

                $scope.save = function (urlItem) {
                    var saveUrlItem = {
                        id: urlItem["id"],
                        url: urlItem["url"]
                    };

                    $http({method: urlItem.id ? 'PUT' : 'POST', url: '../admin/urlConfig', data: saveUrlItem}).success(function (response) {
                        $scope.getList();
                    });
                    $scope.urlItem = "";
                };
                $scope.remove = function (urlItem) {
                    $http({method: 'DELETE', url: '../admin/urlConfig/' + urlItem.id}).success(function (response) {
                        $scope.getList();
                    });
                };

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

                $scope.fileUpload = function (element) {
                    var fd = new FormData();
                    //Take the first selected file
                    fd.append("files", element.files[0]);

                    $http.post("../admin/fileupload/urlConfig", fd, {
                        withCredentials: true,
                        headers: {'Content-Type': undefined},
                        transformRequest: angular.identity
                    }).success(function () {
                        bootbox.dialog({
                            title: "Url Config",
                            message: 'Url Data uploaded successfully',
                            buttons: {
                                success: {
                                    label: "Ok",
                                    className: "btn-success"
                                }
                            }
                        });
                        $scope.getList();
                    }).error(function () {

                    });
                    return;
                };
            })
            .directive('stPaginationScroll', ['$timeout', function (timeout) {
                    return{
                        require: 'stTable',
                        link: function (scope, element, attr, ctrl) {
                            var itemByPage = 20;
                            var pagination = ctrl.tableState().pagination;
                            var lengthThreshold = 50;
                            var timeThreshold = 400;
                            var handler = function () {
                                //call next page
                                ctrl.slice(pagination.start + itemByPage, itemByPage);
                            };
                            var promise = null;
                            var lastRemaining = 9999;
                            var container = angular.element(element.parent());

                            container.bind('scroll', function () {
                                var remaining = container[0].scrollHeight - (container[0].clientHeight + container[0].scrollTop);

                                //if we have reached the threshold and we scroll down
                                if (remaining < lengthThreshold && (remaining - lastRemaining) < 0) {

                                    //if there is already a timer running which has no expired yet we have to cancel it and restart the timer
                                    if (promise !== null) {
                                        timeout.cancel(promise);
                                    }
                                    promise = timeout(function () {
                                        handler();

                                        //scroll a bit up
                                        container[0].scrollTop -= 500;

                                        promise = null;
                                    }, timeThreshold);
                                }
                                lastRemaining = remaining;
                            });
                        }

                    };
                }])
            .directive('pageSelect', function () {
                return {
                    restrict: 'E',
                    template: '<input type="text" class="select-page" ng-model="inputPage" ng-change="selectPage(inputPage)">',
                    link: function (scope, element, attrs) {
                        scope.$watch('currentPage', function (c) {
                            scope.inputPage = c;
                        });
                    }
                }
            })
            .filter('propsFilter', function () {
                return function (items, props) {
                    var out = [];

                    if (angular.isArray(items)) {
                        var keys = Object.keys(props);

                        items.forEach(function (item) {
                            var itemMatches = false;

                            for (var i = 0; i < keys.length; i++) {
                                var prop = keys[i];
                                var text = props[prop].toLowerCase();
                                if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                                    itemMatches = true;
                                    break;
                                }
                            }

                            if (itemMatches) {
                                out.push(item);
                            }
                        });
                    } else {
                        // Let the output be the input untouched
                        out = items;
                    }

                    return out;
                };
            });

})();
