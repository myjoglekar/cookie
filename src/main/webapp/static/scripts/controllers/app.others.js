app.controller('OtherCtrl', function ($scope, $rootScope, $aside, $uibModal, $log) {
                $scope.textAngular = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li style="color: blue;">Super Easy <b>Theming</b> Options</li><li>Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li>Doesnt Use an iFrame</li><li>Works with Firefox, Chrome, and IE8+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';

                $scope.bricks = [];

                function genBrick() {
                    for (var i = 1; i <= 15; i++) {
                        $scope.bricks.push({src: "img/gallery/dummy" + i + ".jpg"});
                    }
                }

                genBrick();

                $scope.priceSlider = {
                    min: 4,
                    max: 481,
                    ceil: 500,
                    floor: 0
                };
                $scope.priceSlider2 = 150;
                $scope.priceSlider3 = 250;
                $scope.translate = function (value)
                {
                    return '$' + value;
                };
                var alphabetArray = 'abcdefghijklmnopqrstuvwxyz'.split('');
                $scope.letter = 5;
                $scope.letterMax = alphabetArray.length - 1;
                $scope.alphabetTranslate = function (value)
                {
                    return alphabetArray[value].toUpperCase();
                };

                $scope.sliders = {
                    sliderValue: 0,
                    secondSliderValue: 0,
                    rangeSliderValue: [10, 50],
                    rangeSliderValue2: [10, 50],
                    thirdSliderValue: 0,
                    asdasda: 3434
                };


                $scope.range = true;
                $scope.value = [55, 70];

                $scope.testOptions = {
                    min: 5,
                    max: 103,
                    step: 2.2,
                    value: 7,
                    rangeValue: [2, 20]
                };

                $scope.myFormater = function (value) {
                    return value + "%";
                };

                $scope.slideDelegate = function (value) {
                    $log.log('slide value: ' + value);
                };


                $rootScope.openAside = function (position, backdrop, size) {
                    $aside.open({
                        templateUrl: 'views/ui-components/aside_example.html',
                        placement: position,
                        size: size,
                        backdrop: backdrop,
                        controller: function ($scope, $uibModalInstance) {
                            $scope.ok = function (e) {
                                $uibModalInstance.close();
                                e.stopPropagation();
                            };
                            $scope.cancel = function (e) {
                                $uibModalInstance.dismiss();
                                e.stopPropagation();
                            };
                        }
                    });
                };

                $rootScope.showModal = function (size) {
                    var modalInstance = $uibModal.open({
                        templateUrl: 'myModalContent.html',
                        controller: 'ModalInstanceCtrl',
                        size: size,
                        resolve: {
                            items: function () {
                                return ['item1', 'item2', 'item3'];
                            }
                        }
                    });

                    modalInstance.result.then(function (selectedItem) {
                        alert('You selected ' + selectedItem);
                    }, function () {
                    });
                };


            })
            .controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {
                $scope.items = items;
                $scope.selected = {
                    item: $scope.items[0]
                };

                $scope.ok = function () {
                    $uibModalInstance.close($scope.selected.item);
                };

                $scope.cancel = function () {
                    $uibModalInstance.dismiss('cancel');
                };
            });

