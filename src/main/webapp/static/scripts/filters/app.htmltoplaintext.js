(function () {
    'use strict';

    angular.module('app')
            .filter('htmlToPlaintext', function () {
                return function (text) {
                    return String(text).replace(/<[^>]+>/gm, '');
                };
            });
})();