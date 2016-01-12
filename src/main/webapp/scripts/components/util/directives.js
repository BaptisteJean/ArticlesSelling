'use strict';

var owlitem;
console.log('hello');
angular.module('articleSellingApp')
    .directive('wrapOwlcarousel', function () {
        return {
            restrict: 'E',
            link: function (scope, element, attrs) {

                //var options = scope.$eval($(element).attr('data-options'));
                    owlitem=$(element).owlCarousel({
                        //navigation : true, // Show next and prev buttons
                        navigation: false,
                        pagination: true,
                        items: 5,
                        itemsDesktopSmall: 	[979,3],
                        itemsTablet: [768, 3],
                        itemsTabletSmall: [660, 2],
                        itemsMobile: [400,1]
                    });
            }
        };
    })
    // Custom Navigation Events
    .directive('nextItem', function () {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                element.bind('click', function() {
                    owlitem.trigger('owl.next');
                })
            }
        };
    })
    .directive('prevItem', function () {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                element.bind('click', function() {
                    owlitem.trigger('owl.prev');
                })
            }
        };
    });
