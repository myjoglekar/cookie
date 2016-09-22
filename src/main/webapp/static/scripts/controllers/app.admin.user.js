(function () {
    'use strict';
    angular.module('app.admin.user', ['ui.select', 'ngSanitize'])
            .controller('UserController', ['$scope', '$filter', 'ngTableParams', '$http', '$templateCache', '$uibModal',
                function ($scope, $filter, ngTableParams, $http, $templateCache, $uibModal) {
                    /*Save Users*/
                    $scope.users = [];
                    $scope.save = function (user) {
                        console.log(user);
                        delete(user.verifyPassword);
                        $http({method: user.id ? 'PUT' : 'POST', url: '../admin/user', data: user}).success(function (response) {
                            $scope.getList();
                        });
                        $scope.user = "";
                    };

                    /*Edit User*/
                    $scope.edit = function (user) {
                        $scope.user = user;
                        var data = {
                            id: user.id,
                            userName: user.userName,
                            firstName: user.firstName,
                            lastName: user.lastName,
                            password: user.password,
                            email: user.email,
                            gender: user.gender,
                            isAdmin: user.isAdmin,
                            primaryPhone: user.primaryPhone,
                            secondaryPhone: user.secondaryPhone
                        };
                        $scope.user = data;
                    };

                    /*Delete User*/
                    $scope.delete = function (user) {
                        bootbox.dialog({
                            title: "Delete",
                            message: '<span>Are you want to delete User <i>"' + user.userName + '"</i> </span>',
                            buttons: {
                                success: {
                                    label: "Confirm",
                                    className: "btn-danger",
                                    callback: function () {
                                        $http({method: 'DELETE', url: '../admin/user/' + user.id}).success(function (response) {
                                            $scope.getList();
                                        });
                                    }
                                }
                            }
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
                    
                    $scope.searchStr = '';
                    $scope.filters = {
                        str: ''
                    };
                    $scope.$watch('searchStr',
                            function (value) {
                                $scope.filters.str = value;
                            });

                    var data = [];
                    $scope.loading = true;
                    $scope.getList = function () {
                        $http.get('../admin/user').success(function (response) {

                            $scope.loading = false;
                            data = response;
                            $scope.users = response;
//                            for (var i = 0; i < data.length; i++) {
//                                $scope.d = data[i].groups;  //set the data from nested obj into new property
//                            }
//                            console.log($scope.d);

                            $scope.tableParams = new ngTableParams({
                                page: 1, // show first page
                                count: 50, // count per page
                                sorting: {
                                    name: 'asc'     // initial sorting
                                },
                                filter: $scope.filters
                            }, {
                                total: data.length, // length of data
                                getData: function ($defer, params) {
                                    //vmiert a binding nem mukodik a {$:'str'}-re... így nekunk kell ezt az objektumot eloallitani
                                    var hack = params.filter().str;

                                    var orderedData = params.filter() ? $filter('filter')(data, {$: hack}) : data;
                                    orderedData = params.sorting() ? $filter('orderBy')(orderedData, params.orderBy()) : data;

                                    $scope.users = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());

                                    params.total(orderedData.length); // set total for recalc pagination

                                    $scope.pages = params.generatePagesArray(params.page(), params.total(), params.count());
                                    $defer.resolve($scope.users);
                                }
                            });
                        });
                    };

                    $templateCache.put('ng-table/pager.html', '');
                    $scope.getList();
                    $scope.setAll = function (bool) {
                        for (var i = 0; i < $scope.users.length; i++) {
                            $scope.users[i].$selected = bool;
                        }
                        ;
                    };
                }])
            
            //Password Compressions
            .directive('userForm', function () {
                return {
                    require: 'ngModel',
                    link: function (scope, elem, attrs, model) {
                        if (!attrs.nxEqual) {
                            console.error('nxEqual expects a model as an argument!');
                            return;
                        }
                        scope.$watch(attrs.nxEqual, function (value) {
                            model.$setValidity('nxEqual', value === model.$viewValue);
                        });
                        model.$parsers.push(function (value) {
                            var isValid = value === scope.$eval(attrs.nxEqual);
                            model.$setValidity('nxEqual', isValid);
                            return isValid ? value : undefined;
                        });
                    }
                };
            })
            .directive('nxEqualEx', function () {
                return {
                    require: 'ngModel',
                    link: function (scope, elem, attrs, model) {
                        if (!attrs.nxEqualEx) {
                            console.error('nxEqualEx expects a model as an argument!');
                            return;
                        }
                        scope.$watch(attrs.nxEqualEx, function (value) {
                            // Only compare values if the second ctrl has a value.
                            if (model.$viewValue !== undefined && model.$viewValue !== '') {
                                model.$setValidity('nxEqualEx', value === model.$viewValue);
                            }
                        });
                        model.$parsers.push(function (value) {
                            // Mute the nxEqual error if the second ctrl is empty.
                            if (value === undefined || value === '') {
                                model.$setValidity('nxEqualEx', true);
                                return value;
                            }
                            var isValid = value === scope.$eval(attrs.nxEqualEx);
                            model.$setValidity('nxEqualEx', isValid);
                            return isValid ? value : undefined;
                        });
                    }
                };
            });

    function Ctrl($scope) {
    }
})();
