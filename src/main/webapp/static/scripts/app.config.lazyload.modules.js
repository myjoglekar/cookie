(function () {
    'use strict';

    angular.module('app')
            .constant('_MODULES', [
                {
                    name: "angularMoment",
                    files: ['bower_components/angular-moment/angular-moment.min.js']
                },
                {
                    name: "NgSwitchery",
                    files: ['bower_components/ng-switchery/src/ng-switchery.js']
                },
                {
                    name: "keyboard",
                    files: ['bower_components/ng-keyboard/src/ng-keyboard.js']
                },
                {
                    name: "ui.sortable",
                    files: ['bower_components/angular-ui-sortable/sortable.min.js']
                },
                {
                    name: "ui.select",
                    files: ['bower_components/angular-ui-select/dist/select.js', 'bower_components/angular-ui-select/dist/select.min.css']
                },
                {
                    name: "textAngular",
                    files: ['bower_components/textAngular/dist/textAngular-rangy.min.js','bower_components/textAngular/dist/textAngular-sanitize.min.js', 'bower_components/textAngular/dist/textAngular.min.js']
                },
                {
                    name: "angularUnderscoe",
                    files: ['bower_components/angular-underscore-module/angular-underscore-module.js']
                },
                {
                    name: 'chartjs',
                    files: ['bower_components/ng-chartjs/dist/angular-chartjs.js']
                },
                {
                    name: "angular-rickshaw",
                    files: ['bower_components/angular-rickshaw/rickshaw.js']
                },
                {
                    name: 'angular-flot',
                    files: ['bower_components/angular-flot/angular-flot.js']
                },
                {
                    name: 'easypiechart',
                    files: ['bower_components/jquery.easy-pie-chart/dist/angular.easypiechart.min.js']
                },
                {
                    name: 'ui.calendar',
                    files: ['bower_components/angular-ui-calendar/src/calendar.js']
                },
                {
                    name: 'validator',
                    files: ['bower_components/angular-validator/dist/angular-validator.min.js']
                },
                {
                    name: 'validator.rules',
                    files: ['bower_components/angular-validator/dist/angular-validator-rules.min.js']
                },
                {
                    name: 'angularFileUpload',
                    files: ['bower_components/angular-file-upload/angular-file-upload.min.js']
                },
                {
                    name: 'mgo-angular-wizard',
                    files: ['bower_components/angular-wizard/dist/angular-wizard.min.js']
                },
                {
                    name: 'xeditable',
                    files: ['bower_components/angular-xeditable/dist/js/xeditable.js', 'bower_components/angular-xeditable/dist/css/xeditable.css']
                },
                {
                    name: 'ngTable',
                    files: ['bower_components/ng-table/ng-table.min.js', 'bower_components/ng-table/ng-table.min.css']
                },
                {
                    name: 'ui.bootstrap-slider',
                    files: ['bower_components/angular-bootstrap-slider/slider.js']
                },
                {
                    name: 'ngBootbox',
                    files: ['js_vendors/ng-bootbox/dist/ngBootbox.min.js']
                },
                {
                    name: 'angularBootstrapNavTree',
                    files: ['bower_components/angular-bootstrap-nav-tree/dist/abn_tree_directive.js', 'bower_components/angular-bootstrap-nav-tree/dist/abn_tree.css']
                },
                {
                    name: 'angular-datepicker',
                    files: ['bower_components/angular-native-picker/build/angular-datepicker.js', 'bower_components/angular-native-picker/build/themes/default.css', 'bower_components/angular-native-picker/build/themes/default.date.css', 'bower_components/angular-native-picker/build/themes/default.time.css']
                },
                {
                    name: 'ngImgCrop',
                    files: ['bower_components/ngImgCrop/compile/minified/ng-img-crop.js', 'bower_components/ngImgCrop/compile/minified/ng-img-crop.css']
                },
                {
                    name: 'ng-nestable',
                    files: ['bower_components/ng-nestable/src/angular-nestable.js']
                },
                {
                    name: 'ui.utils',
                    files: ['bower_components/angular-ui-utils/ui-utils.min.js']
                },
                {
                    name: 'AngularGM',
                    files: ['bower_components/AngularGM/angular-gm.min.js']
                },
                {
                    name: 'ui.grid',
                    files: ['bower_components/angular-ui-grid/ui-grid.min.js', 'bower_components/angular-ui-grid/ui-grid.min.css']
                },
                {
                    name: 'wu.masonry',
                    files: ['bower_components/angular-masonry/angular-masonry.js']
                },
                {
                    name: 'ui.bootstrap.datetimepicker',
                    files: ['bower_components/angular-bootstrap-datetimepicker/src/js/datetimepicker.js', 'bower_components/angular-bootstrap-datetimepicker/src/css/datetimepicker.css']
                },
                {
                    name: 'ngGridPanel',
                    files: ['bower_components/ng-grid-panel/ng-grid-panel.min.js', 'bower_components/ng-grid-panel/ng-grid-panel.min.css']
                },
                {
                    name: 'ng-nvd3',
                    files: ['bower_components/ng-nvd3/dist/ng-nvd3.js']
                },
                {
                    name: 'rzModule',
                    files: ['bower_components/angularjs-slider/dist/rzslider.min.js']
                },
                {
                    name: 'ngAutocomplete',
                    files: ['bower_components/ngAutocomplete/src/ngAutocomplete.js']
                },
                {
                    name: 'ngColorPicker',
                    files: ['bower_components/ng-color-picker/color-picker.js', 'bower_components/ng-color-picker/color-picker.css']
                },
                {
                    name: 'ui.footable',
                    files: ['bower_components/angular-footable/dist/angular-footable.min.js']
                },
                {
                    name: 'smart-table',
                    files: ['bower_components/angular-smart-table/dist/smart-table.min.js']
                },
                {
                    name: 'lrDragNDrop',
                    files: ['js_vendors/lrDragNDrop.js']
                },
                {
                    name: 'ngMock',
                    files: ['bower_components/angular-mocks/angular-mocks.js']
                },
                {
                    name: 'oitozero.ngSweetAlert',
                    files: ['bower_components/angular-sweetalert/SweetAlert.min.js']
                },
                {
                    name: 'daterangepicker',
                    files: ['bower_components/angular-daterangepicker/js/angular-daterangepicker.js']
                },
                {
                    name: 'datePicker',
                    files: ['bower_components/angular-datepicker/dist/angular-datepicker.min.js', 'bower_components/angular-datepicker/dist/angular-datepicker.min.css']
                }
            ]);
})(); 