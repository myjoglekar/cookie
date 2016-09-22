(function () {
    'use strict';
    angular.module('app.dashboard.emotionChart', [])
            .controller('DashboardEmotionChartController', ['$scope', function ($scope) {
                    $scope.chartjs = {
                        polar: {
                            options: {
                                scaleShowLabelBackdrop: true,
                                scaleBackdropColor: "rgba(255,255,255,0.75)",
                                scaleBeginAtZero: true,
                                scaleBackdropPaddingY: 1,
                                scaleBackdropPaddingX: 1,
                                scaleShowLine: true,
                                segmentShowStroke: true,
                                segmentStrokeColor: "#fff",
                                segmentStrokeWidth: 2,
                                animationSteps: 100,
                                animationEasing: "easeOutBounce",
                                animateRotate: true,
                                animateScale: false,
                            },
                            data: [
                                {
                                    value: 5,
                                    color: "#EBC142",
                                    highlight: "#73dd4d",
                                    label: "Disgust"
                                },
                                {
                                    value: 40,
                                    color: "#03A9F4",
                                    highlight: "#34b5f7",
                                    label: "Joy"
                                },
                                {
                                    value: 5,
                                    color: "#009688",
                                    highlight: "#fec07e",
                                    label: "Sad"
                                },
                                {
                                    value: 30,
                                    color: "#615CA8",
                                    highlight: "#fec07e",
                                    label: "Surprise"
                                },
                                {
                                    value: 20,
                                    color: "#F13C6E",
                                    highlight: "#fec07e",
                                    label: "Unknown"
                                }
                            ]
                        }
                    }
                }]);
})();
