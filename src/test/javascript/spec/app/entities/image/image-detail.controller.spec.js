'use strict';

describe('Image Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockImage, MockAds;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockImage = jasmine.createSpy('MockImage');
        MockAds = jasmine.createSpy('MockAds');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Image': MockImage,
            'Ads': MockAds
        };
        createController = function() {
            $injector.get('$controller')("ImageDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'articleSellingApp:imageUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
