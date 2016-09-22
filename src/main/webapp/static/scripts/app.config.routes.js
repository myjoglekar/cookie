(function () {
    'use strict';

    angular.module('app').config(['$stateProvider', '$urlRouterProvider', '$breadcrumbProvider', '_REQUIRES',
        function ($stateProvider, $urlRouterProvider, $breadcrumbProvider, _REQUIRES) {

            $breadcrumbProvider.setOptions({
                prefixStateName: 'app.components.breadcrumb',
                template: 'bootstrap3'
            });

            $urlRouterProvider.otherwise("/app/dashboard/v1");

            $stateProvider
                    .state('app', {
                        abstract: true,
                        url: "/app",
                        templateUrl: "views/app.html",
                        resolve: resolveRoute(['app.header', 'app.sidebar', 'app.others'])
                    })
                    .state('app.dashboard', {
                        abstract: true,
                        url: "/dashboard",
                        template: "<div data-ui-view></div>"
                    })
                    .state('app.dashboard.v1', {
                        url: "/v1",
                        templateUrl: "views/dashboard/dashboard.html",
                        resolve: resolveRoute(['angular-flot', 'easypiechart', 'app.dashboard', 'ui.bootstrap.datetimepicker','angular-rickshaw'],
                            ['flot', 'flot.resize', 'flot.pie', 'flot.time', 'flot.categories', 'flot.spline', 'sparkline','d3', 'rickshaw'])   
                    })
                    .state('app.dashboard.v2', {
                        url: "/v2",
                        templateUrl: "views/dashboard/dashboard.v2.html",
                        resolve: resolveRoute(['angular-rickshaw','angular-flot','app.dashboard.v2'], ['d3', 'rickshaw','sparkline','flot','flot.colorhelpers','flot.tooltip','flot.resize','flot.stack', 'flot.time'])
                    })
                    .state('app.dashboard.v3', {
                        url: "/v3",
                        templateUrl: "views/dashboard/dashboard.v3.html",
                        resolve:{
                            googlemaps: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
                                if (window.gmapDeferred === undefined) {
                                    window.gmapDeferred = $q.defer();
                                }
                                window.gMapsCallback = function () {
                                    window.gmapDeferred.resolve({status: 'success'});
                                };
                                $ocLazyLoad.load({files: ['https://maps.googleapis.com/maps/api/js?sensor=false&callback=gMapsCallback']});
                                return window.gmapDeferred.promise;
                            }],
                            controller: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
                                return $ocLazyLoad.load('app.dashboard.v3');
                            }],
                            gmaps: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
                                return $ocLazyLoad.load(['AngularGM'],{cache: true,rerun:false,reconfig: false});
                            }],
                            sparkline: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
                                return $ocLazyLoad.load(_REQUIRES['sparkline']);
                            }]

                        }
                    })
                    .state('app.ui', {
                        abstract: true,
                        url: "/ui",
                        template: "<div data-ui-view></div>"
                    })
                    .state('app.ui.barchart', {
                        url: "/barchart",
                        templateUrl: "views/ui/sampleBarChart.html"
                    })
                    .state('app.ui.general', {
                        url: "/general",
                        templateUrl: "views/ui/general.html"
                    })
                    .state('app.ui.buttons', {
                        url: "/buttons",
                        templateUrl: "views/ui/buttons.html"
                    })
                    .state('app.ui.typography', {
                        url: "/typography",
                        templateUrl: "views/ui/typography.html"
                    })
                    .state('app.ui.grid', {
                        url: "/grid",
                        templateUrl: "views/ui/grid.html"
                    })
                    .state('app.ui.panels', {
                        url: "/panels",
                        templateUrl: "views/ui/panels.html"
                    })
                    .state('app.ui.navs', {
                        url: "/navs",
                        templateUrl: "views/ui/navs.html"
                    })
                    .state('app.ui.tabs-accordions', {
                        url: "/tabs-accordions",
                        templateUrl: "views/ui/tabs-accordions.html"
                    })
                    .state('app.ui.icons', {
                        url: "/icons",
                        templateUrl: "views/ui/icons.html"
                    })
                    .state('app.components', {
                        abstract: true,
                        url: "/components",
                        template: "<div data-ui-view></div>"
                    })
                    .state('app.components.aside', {
                        url: "/aside",
                        templateUrl: "views/ui-components/aside.html"
                    })
                    .state('app.components.modal', {
                        url: "/modal",
                        templateUrl: "views/ui-components/modal.html"
                    })
                    .state('app.components.tree', {
                        url: "/tree",
                        templateUrl: "views/ui-components/tree.html",
                        resolve: resolveRoute(['angularBootstrapNavTree', 'app.tree'])
                    })
                    .state('app.components.sortable', {
                        url: "/sortable",
                        templateUrl: "views/ui-components/sortable.html",
                        resolve: resolveRoute(['ng-nestable', 'ui.sortable', 'app.components.sortable'], ['jquery.nestable', 'jquery-ui'])
                    })
                    .state('app.components.alerts-notifications', {
                        url: "/alerts-notifications",
                        templateUrl: "views/ui-components/alerts-notifications.html",
                        resolve: resolveRoute(['ngBootbox', 'oitozero.ngSweetAlert', 'app.notifications'], ['bootbox', 'sweetalert'])
                    })
                    .state('app.components.sliders', {
                        url: "/sliders",
                        templateUrl: "views/ui-components/sliders.html",
                        resolve: resolveRoute(['rzModule'])
                    })
                    .state('app.components.carousel', {
                        url: "/carousel",
                        templateUrl: "views/ui-components/carousel.html",
                        resolve: resolveRoute(['app.carousel'])
                    })
                    .state('app.components.list-wells', {
                        url: "/list-wells",
                        templateUrl: "views/ui-components/list-wells.html"
                    })
                    .state('app.components.portlet', {
                        url: "/portlet",
                        templateUrl: "views/ui-components/portlet.html",
                        resolve: resolveRoute(['ui.sortable'], ['jquery-ui'])
                    })
                    .state('app.components.breadcrumb', {
                        url: "/breadcrumb",
                        templateUrl: "views/breadcrumb/sample.html",
                        resolve: resolveRoute(['app.breadcrumb'], ['underscore']),
                        ncyBreadcrumb: {
                            label: 'Home'
                        }
                    })
                    .state('app.components.breadcrumb.booking', {
                        url: '/booking',
                        views: {
                            "bcView": {
                                templateUrl: 'views/breadcrumb/booking_list.html',
                                controller: 'BookingListCtrl'
                            }
                        },
                        ncyBreadcrumb: {
                            label: 'Reservations',
                            parent: 'app.components.breadcrumb'
                        }
                    })
                    .state('app.components.breadcrumb.booking.day', {
                        url: '/:year-:month-:day',
                        views: {
                            "bView": {
                                templateUrl: 'views/breadcrumb/booking_day.html',
                                controller: 'BookingDayCtrl'
                            }
                        },
                        onExit: function ($rootScope) {
                            $rootScope.reservationDate = undefined;
                        },
                        ncyBreadcrumb: {
                            label: 'Reservations for {{reservationDate | date:\'mediumDate\'}}'
                        }
                    })
                    .state('app.components.breadcrumb.booking.day.detail', {
                        url: '/{reservationId}',
                        onEnter: function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: "views/breadcrumb/booking_detail.html",
                                controller: 'BookingDetailCtrl'
                            }).result.then(function (result) {
                                return $state.go("^");
                            }, function (result) {
                                return $state.go("^");
                            });
                        },
                        ncyBreadcrumb: {
                            skip: true
                        }
                    })
                    .state('app.components.breadcrumb.room', {
                        url: '/room',
                        views: {
                            "bcView": {
                                templateUrl: 'views/breadcrumb/room_list.html',
                                controller: 'RoomListCtrl'
                            }
                        },
                        ncyBreadcrumb: {
                            label: 'Rooms',
                            parent: 'app.components.breadcrumb'
                        }
                    })
                    .state('app.components.breadcrumb.room.new', {
                        url: '/new',
                        views: {
                            "bView": {
                                templateUrl: 'views/breadcrumb/room_form.html',
                                controller: 'RoomDetailCtrl'
                            }
                        },
                        ncyBreadcrumb: {
                            label: 'New room'
                        }
                    })
                    .state('app.components.breadcrumb.room.detail', {
                        url: '/{roomId}?from',
                        views: {
                            "bView": {
                                templateUrl: 'views/breadcrumb/room_detail.html',
                                controller: 'RoomDetailCtrl'
                            }
                        },
                        ncyBreadcrumb: {
                            label: 'Room {{room.roomNumber}}',
                            parent: function ($scope) {
                                return $scope.from || 'app.components.breadcrumb.room';
                            }
                        }
                    })
                    .state('app.components.breadcrumb.room.detail.edit', {
                        url: '/edit',
                        views: {
                            "cView": {
                                templateUrl: 'views/breadcrumb/room_form.html',
                                controller: 'RoomDetailCtrl'
                            }
                        },
                        ncyBreadcrumb: {
                            label: 'Editing'
                        }
                    })
                    .state('app.maps', {
                        abstract: true,
                        url: "/maps",
                        template: "<div data-ui-view></div>",
                        resolve: {
                            vectormaps: ['$ocLazyLoad', '_REQUIRES', function ($ocLazyLoad, _REQUIRES) {
                                    return $ocLazyLoad.load({files: _REQUIRES['jvectormap']});
                                }],
                            googlemaps: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
                                    if (window.gmapDeferred === undefined) {
                                        window.gmapDeferred = $q.defer();
                                    }
                                    window.gMapsCallback = function () {
                                        window.gmapDeferred.resolve({status: 'success'});
                                    };
                                    $ocLazyLoad.load({files: ['https://maps.googleapis.com/maps/api/js?sensor=false&callback=gMapsCallback']});
                                    return window.gmapDeferred.promise;
                                }]
                        }
                    })
                    .state('app.maps.googlemaps', {
                        url: "/googlemaps",
                        templateUrl: "views/maps/google-maps.html",
                        resolve: resolveRoute(['AngularGM', 'app.maps'])
                    })
                    .state('app.maps.vector', {
                        url: "/vectormap",
                        templateUrl: "views/maps/vector.html",
                        resolve: resolveRoute(['app.maps'], ['jvectormap_maps'])
                    })
                    .state('app.forms', {
                        abstract: true,
                        url: "/forms",
                        template: "<div data-ui-view></div>",
                        resolve: resolveRoute([], ['daterangepicker'])
                    })
                    .state('app.forms.elements', {
                        url: "/elements",
                        templateUrl: "views/forms/elements.html",
                        resolve: resolveRoute(['app.form.elements'], ['moment', 'icheck'])
                    })
                    .state('app.forms.advanced', {
                        url: "/advanced-elements",
                        templateUrl: "views/forms/advanced.html",
                        resolve: {
                            modules: ['$ocLazyLoad', function ($ocLazyLoad) {
                                    return $ocLazyLoad.load(['ui.select', 'angular-datepicker', 'ui.bootstrap.datetimepicker', 'ngAutocomplete', 'ngColorPicker', 'app.form.elements', 'daterangepicker', 'datePicker']);
                                }],
                            googleplaces: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
                                    if (window.gplacesDeferred === undefined) {
                                        window.gplacesDeferred = $q.defer();
                                        $ocLazyLoad.load({files: ['https://maps.googleapis.com/maps/api/js?libraries=places&sensor=false&callback=gplacesCallback']});
                                    }
                                    window.gplacesCallback = function () {
                                        window.gplacesDeferred.resolve({status: 'success'});
                                    };
                                    return window.gplacesDeferred.promise;
                                }]
                        }
                    })
                    .state('app.forms.layouts', {
                        url: "/layouts",
                        templateUrl: "views/forms/layout.html"
                    })
                    .state('app.forms.validation', {
                        url: "/validation",
                        templateUrl: "views/forms/validation.html",
                        resolve: resolveRoute(['app.form.validation', 'validator', 'validator.rules', 'ui.utils'])
                    })
                    .state('app.forms.wizard', {
                        url: "/wizard",
                        templateUrl: "views/forms/wizard.html",
                        resolve: resolveRoute(['app.form.wizard'], ['underscore'])
                    })
                    .state('app.forms.wizard-alt', {
                        url: "/wizard-alt",
                        templateUrl: "views/forms/wizard-alt.html",
                        resolve: resolveRoute(['app.form.wizard'], ['underscore'])
                    })
                    .state('app.forms.editable', {
                        url: "/editable",
                        templateUrl: "views/forms/editable.html",
                        resolve: resolveRoute(['app.form.editable'])
                    })
                    .state('app.forms.editors', {
                        url: "/editors",
                        templateUrl: "views/forms/editors.html"
                    })
                    .state('app.forms.upload', {
                        url: "/upload",
                        templateUrl: "views/forms/upload.html",
                        resolve: resolveRoute(['angularFileUpload', 'app.form.fileupload'])
                    })
                    .state('app.forms.imagecrop', {
                        url: "/imagecrop",
                        templateUrl: "views/forms/image-crop.html",
                        resolve: resolveRoute(['app.form.imagecrop', 'ngImgCrop'])
                    })
                    .state('app.tables', {
                        abstract: true,
                        url: "/tables",
                        template: "<div data-ui-view></div>"
                    })
                    .state('app.tables.basic', {
                        url: "/basic",
                        templateUrl: "views/tables/basic.html"
                    })
                    .state('app.tables.responsive', {
                        url: "/responsive",
                        templateUrl: "views/tables/responsive.html"
                    })
                    .state('app.tables.data-table', {
                        url: "/data-table",
                        templateUrl: "views/tables/datatable.html",
                        resolve: resolveRoute(['ngTable', 'app.table.data'])
                    })
                    .state('app.tables.grid', {
                        url: "/grid",
                        templateUrl: "views/tables/grid.html",
                        resolve: resolveRoute(['ui.grid', 'app.ui.grid'], ['vfs_fonts', 'pdfmake', 'csv'])
                    })
                    .state('app.tables.footable', {
                        url: "/footable",
                        templateUrl: "views/tables/footable.html",
                        resolve: resolveRoute(['ui.footable', 'app.table.footable'], ['footable'])
                    })
                    .state('app.tables.smart-table', {
                        url: "/smart-table",
                        templateUrl: "views/tables/smart-table.html",
                        resolve: resolveRoute(['smart-table', 'lrDragNDrop', 'app.table.smart'])
                    })
                    .state('app.tables.editable', {
                        url: "/editable",
                        templateUrl: "views/tables/editable.html",
                        resolve: resolveRoute(['app.table.editable'])
                    })
                    .state('app.pages', {
                        abstract: true,
                        url: "/pages",
                        template: "<div data-ui-view></div>"
                    })
                    .state('app.pages.profile', {
                        url: "/profile",
                        templateUrl: "views/pages/profile.html",
                        resolve: resolveRoute(['easypiechart'])
                    })
                    .state('app.pages.invoice', {
                        url: "/invoice",
                        templateUrl: "views/pages/invoice.html"
                    })
                    .state('app.pages.timeline', {
                        url: "/timeline",
                        templateUrl: "views/pages/timeline.html"
                    })
                    .state('app.pages.search', {
                        url: "/search",
                        templateUrl: "views/pages/search.html"
                    })

                    .state('app.extra', {
                        abstract: true,
                        url: "/extra",
                        template: "<div data-ui-view></div>"
                    })
                    .state('app.extra.forum', {
                        url: "/forum",
                        templateUrl: "views/extra/forum.html"
                    })
                    .state('app.extra.post', {
                        url: "/post",
                        templateUrl: "views/extra/post.html"
                    })
                    .state('app.extra.project-list', {
                        url: "/project-list",
                        templateUrl: "views/extra/project-list.html",
                        resolve: resolveRoute([], ['sparkline'])
                    })
                    .state('app.extra.chat-room', {
                        url: "/chat-room",
                        templateUrl: "views/extra/chat-room.html"
                    })
                    .state('app.extra.people', {
                        url: "/people",
                        templateUrl: "views/extra/people.html"
                    })
                    .state('app.extra.gallery', {
                        url: "/gallery",
                        templateUrl: "views/extra/gallery.html",
                        resolve: resolveRoute(['ngGridPanel'])
                    })
                    .state('app.extra.feed', {
                        url: "/feed",
                        templateUrl: "views/extra/feed.html",
                        resolve: resolveRoute(['wu.masonry'], ['masonry', 'imagesloaded'])
                    })
                    .state('app.starters', {
                        abstract: true,
                        url: "/starters",
                        template: "<div data-ui-view></div>"
                    })
                    .state('app.starters.empty', {
                        url: "/empty",
                        templateUrl: "views/starters/empty.html"
                    })
                    .state('app.starters.title', {
                        url: "/with-title",
                        templateUrl: "views/starters/title.html"
                    })
                    .state('app.starters.boxed', {
                        url: "/boxed",
                        templateUrl: "views/starters/boxed.html"
                    })
                    .state('app.starters.column_3_9', {
                        url: "/column-3-9",
                        templateUrl: "views/starters/column_3_9.html"
                    })
                    .state('app.starters.column_9_3', {
                        url: "/column-9-3",
                        templateUrl: "views/starters/column_9_3.html"
                    })
                    .state('app.starters.column_6_6', {
                        url: "/column-6-6",
                        templateUrl: "views/starters/column_6_6.html"
                    })
                    .state('app.starters.column_2_8_2', {
                        url: "/column-2-8-2",
                        templateUrl: "views/starters/column_2_8_2.html"
                    })
                    .state('app.starters.column_fix_fluid', {
                        url: "/column-fix-fluid",
                        templateUrl: "views/starters/column_fix_fluid.html"
                    })
                    .state('app.starters.column_fluid_fix', {
                        url: "/column-fluid-fix",
                        templateUrl: "views/starters/column_fluid_fix.html"
                    })
                    .state('app.starters.column_complex', {
                        url: "/column-complex",
                        templateUrl: "views/starters/column_complex.html"
                    })
                    .state('app.charts', {
                        abstract: true,
                        url: "/charts",
                        template: "<div data-ui-view></div>",
                        resolve: resolveRoute(['app.charts'])
                    })
                    .state('app.charts.sparkline', {
                        url: "/sparkline",
                        templateUrl: "views/charts/sparkline.html",
                        resolve: resolveRoute([], ['sparkline'])
                    })
                    .state('app.charts.flot', {
                        url: "/flot",
                        templateUrl: "views/charts/flot.html",
                        resolve: resolveRoute(['angular-flot'],
                                ['flot', 'flot.colorhelpers', 'flot.tooltip', 'flot.spline', 'flot.pie', 'flot.resize', 'flot.stack', 'flot.time'])
                    })
                    .state('app.charts.peity', {
                        url: "/peity",
                        templateUrl: "views/charts/peity.html"
                    })
                    .state('app.charts.chartjs', {
                        url: "/chartjs",
                        templateUrl: "views/charts/chartjs.html",
                        resolve: resolveRoute(['chartjs'], ['chart-js'])
                    })
                    .state('app.charts.rickshaw', {
                        url: "/rickshaw",
                        templateUrl: "views/charts/rickshaw.html",
                        resolve: resolveRoute(['angular-rickshaw'], ['d3', 'rickshaw'])
                    })
                    .state('app.charts.morris', {
                        url: "/morris",
                        templateUrl: "views/charts/morris.html",
                        resolve: resolveRoute([], ['raphael', 'morris'])
                    })
                    .state('app.charts.nvd', {
                        url: "/nvd3",
                        templateUrl: "views/charts/nvd3.html",
                        resolve: resolveRoute(['ng-nvd3'], ['d3', 'nvd3'])
                    })
                    .state('app.calendar', {
                        url: "/calendar",
                        templateUrl: "views/calendar/calendar.html",
                        resolve: resolveRoute(['ui.calendar', 'angularMoment', 'calendar.controller'], ['moment', 'fullcalendar'])
                    })
                    .state('app.widgets', {
                        url: "/widgets",
                        templateUrl: "views/ui-components/widgets.html",
                        resolve: resolveRoute(['easypiechart'])
                    })
                    .state('mail', {
                        abstract: true,
                        url: "/mail",
                        templateUrl: "views/mail/layout.html",
                        resolve: resolveRoute(['app.header', 'app.sidebar', 'ui.select', 'mail.service', 'mail.controller'])
                    })
                    .state('mail.inbox', {
                        url: "/inbox",
                        views: {
                            "contentView": {templateUrl: 'views/mail/overview.html'}
                        },
                        resolve: resolveRoute(['angularMoment'], ['moment'])
                    })
                    .state('mail.view', {
                        url: "/view/:emailId",
                        views: {
                            "contentView": {templateUrl: 'views/mail/view.html'}
                        }
                    })
                    .state('mail.edit', {
                        url: "/edit",
                        views: {
                            "contentView": {templateUrl: 'views/mail/edit.html'}
                        }
                    })
                    .state('app.documents', {
                        url: "/documents",
                        templateUrl: "views/documents/layout.html",
                        resolve: resolveRoute(['angularMoment', 'documents.controller', 'documents.service'], ['moment']),
                        "abstract": true
                    })
                    .state('app.documents.all', {
                        url: "",
                        views: {
                            "contentView": {templateUrl: 'views/documents/overview.html'}
                        }
                    })
                    .state('app.documents.view', {
                        url: "/view/:docId",
                        views: {
                            "contentView": {templateUrl: 'views/documents/view.html'}
                        }
                    })
                    .state('app.tasks', {
                        url: "/tasks",
                        templateUrl: "views/tasks/tasks.html",
                        resolve: resolveRoute(['ui.sortable', 'angularMoment', 'tasks.service', 'tasks.controller'], ['jquery-ui']),
                        "abstract": true
                    })
                    .state('app.tasks.all', {
                        url: "",
                        views: {
                            "contentView": {templateUrl: 'views/tasks/list.html'}
                        }
                    })
                    .state('app.tasks.view', {
                        url: "/view/:taskId",
                        views: {
                            "contentView": {templateUrl: 'views/tasks/view.html'}
                        }
                    })
                    .state('access', {
                        abstract: true,
                        url: "/access",
                        template: "<div ui-view class='h-100'></div>"
                    })
                    .state('access.login', {
                        url: "/login",
                        templateUrl: "views/access/login.html"
                    })
                    .state('access.signup', {
                        url: "/signup",
                        templateUrl: "views/access/sign_up.html"
                    })
                    .state('access.forget', {
                        url: "/forget",
                        templateUrl: "views/access/forgot_password.html"
                    })
                    .state('access.lock', {
                        url: "/lock",
                        templateUrl: "views/access/lock_screen.html"
                    })
                    .state('access.404', {
                        url: "/404",
                        templateUrl: "views/pages/404.html"
                    })
                    .state('access.500', {
                        url: "/500",
                        templateUrl: "views/pages/500.html"
                    })
                    .state('app.ecommerce', {
                        abstract: true,
                        url: "/ecommerce",
                        template: "<div data-ui-view></div>"
                    })
                    .state('app.ecommerce.procucts_grid', {
                        url: "/products_grid",
                        templateUrl: "views/ecommerce/products_grid.html",
                        resolve: resolveRoute([],[])   
                    })
                    .state('app.ecommerce.procucts_list', {
                        url: "/products_list",
                        templateUrl: "views/ecommerce/products_list.html",
                        resolve: resolveRoute([],[])   
                    })
                    .state('app.ecommerce.procuct_edit', {
                        url: "/product_edit",
                        templateUrl: "views/ecommerce/product_edit.html",
                        resolve: resolveRoute([],[])   
                    })
                    .state('app.ecommerce.procuct_detail', {
                        url: "/product_detail",
                        templateUrl: "views/ecommerce/product_detail.html",
                        resolve: resolveRoute([],[])   
                    })
                    .state('app.ecommerce.orders', {
                        url: "/orders",
                        templateUrl: "views/ecommerce/orders.html",
                        resolve: resolveRoute(['ui.footable', 'app.table.footable'], ['footable'])   
                    });

            function resolveRoute(modules, files) {
                var depsObj = {};
                if (typeof files !== 'undefined') {
                    var collect = [];
                    for (var key in files) {
                        for (var inKey in _REQUIRES[files[key]]) {
                            collect.push(_REQUIRES[files[key]][inKey]);
                        }
                    }
                    setObject('files', collect);
                }
                if (modules.length) {
                    setObject('modules', modules);
                }
                function setObject(name, items, then) {
                    depsObj[name] = ['$ocLazyLoad', function ($ocLazyLoad) {
                            if (name === "files" && items.length) {
                                return $ocLazyLoad.load({serie: true, files: items});
                            }
                            else {
                                return $ocLazyLoad.load(items);
                            }
                        }];
                }
                return depsObj;
            }
        }]);
})();