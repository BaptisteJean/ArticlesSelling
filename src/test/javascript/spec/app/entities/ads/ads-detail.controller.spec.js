'use strict';

describe('Ads Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockAds, MockCategorie, MockImage;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockAds = jasmine.createSpy('MockAds');
        MockCategorie = jasmine.createSpy('MockCategorie');
        MockImage = jasmine.createSpy('MockImage');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Ads': MockAds,
            'Categorie': MockCategorie,
            'Image': MockImage
        };
        createController = function() {
            $injector.get('$controller')("AdsDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'articleSellingApp:adsUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
