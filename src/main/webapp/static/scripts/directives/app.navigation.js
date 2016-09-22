(function () {
    'use strict';

    angular.module('app')
            .directive('appNavigation', ['$window', '$rootScope','$timeout','$compile', function ($window, $rootScope, $timeout,$compile) {
                    return {
                        restrict: 'AC',
                        link: function (scope, el, attr) {

                            var body = angular.element("body");
                            var mainAside = angular.element("body .app-aside");
                            var min = 150;
                            var offset = 43;
                            var holder;
                            var mainElement;
                            var originalWidth = 0;

                            function hideNav() {
                                holder && holder.trigger('mouseleave.nav');
                            }

                            function clickEvent(self) {
                                var element = angular.element(self);
                                var liParent = element.parent();
                                var ulParent = liParent.parent();

                                if (liParent.children('.nav-children').length > 0) {
                                    var navChildren = liParent.children('.nav-children');
                                    if (liParent.hasClass('opened')) {
                                        liParent.removeClass('opened');
                                    }
                                    else {
                                        var liParent = angular.element(liParent);
                                        ulParent.children('li').each(function (index, child) {
                                            var child = angular.element(child);
                                            if (child.hasClass('opened') && !child.hasClass('current')) {
                                                child.removeClass('opened');
                                            }
                                        });
                                        liParent.addClass('opened');
                                    }
                                }

                                if ($rootScope.mobileLayout) {
                                    $rootScope.app.settings.asideFolded = false;
                                }
                            }
                            
                            function setDocked(el){
                                var el = angular.element(el);
                                if(body.hasClass('app-aside-docked')){
                                    $timeout(function () {   
                                        var parentWidth = el.parent().width();
                                        var width = el.width();
                                        el.attr("style", "");
                                        el.children('.app-nav-caret').remove();
                                        if(parentWidth<=width+80){
                                            el.prepend($compile("<li class='app-nav-left-caret app-nav-caret'><a ng-click='moveNav(false)'><i class='fa fa fa-angle-left'></a></li></li>")(scope));
                                            el.append($compile("<li class='app-nav-right-caret app-nav-caret'><a ng-click='moveNav(true)'><i class='fa fa fa-angle-right'></a></li></li>")(scope));
                                            el.css('width',width+80);
                                            el.css('margin-left',30);
                                        }
                                    }, 0);
                                }
                                else{
                                    el.attr("style", "");
                                    el.children('.app-nav-caret').remove();
                                }
                            }

                            el.on('click', 'a', function (e) {
                                hideNav();
                                clickEvent(this);
                            });

                            el.on('mouseenter', 'a', function (e) {
                                if (body.hasClass('app-aside-folded') && body.hasClass('app-aside-fixed') && !body.hasClass('app-aside-docked')) {
                                    hideNav();
                                    var windowHeight = angular.element($window).height();
                                    mainElement = angular.element(this);
                                    if (mainElement.next().is('ul')) {
                                        holder = mainElement.next();
                                        var top = mainElement.parent().position().top;
                                        holder.css('top', top);
                                        if (top + holder.height() > windowHeight) {
                                            holder.css('bottom', 0);
                                        }
                                        holder.appendTo(mainAside);
                                        holder.on('mouseleave.nav', function (e) {
                                            holder.appendTo(mainElement.parent());
                                            holder.off('mouseleave.nav').attr("style", "");
                                            holder.off('click', 'a');
                                        });
                                        holder.on('click', 'a', function (e) {
                                            clickEvent(this);
                                        });
                                    }
                                }
                            });

                            mainAside.on('mouseleave', function (e) {
                                hideNav();
                            });
                            
                            scope.moveNav =function (dir){
                                var marginLeft = parseInt(el.css('margin-left').replace(/[^-\d\.]/g, ''));
                                if(dir){
                                    marginLeft-=100;
                                }
                                else{
                                    marginLeft+=100;
                                }
                                el.css('margin-left',marginLeft<=30 ? marginLeft : 30);
                            };
                            
                        }};
                }]);
})();


