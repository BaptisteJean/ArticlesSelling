'use strict';

describe('Categorie Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockCategorie, MockAds;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockCategorie = jasmine.createSpy('MockCategorie');
        MockAds = jasmine.createSpy('MockAds');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Categorie': MockCategorie,
            'Ads': MockAds
        };
        createController = function() {
            $injector.get('$controller')("CategorieDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'articleSellingApp:categorieUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
