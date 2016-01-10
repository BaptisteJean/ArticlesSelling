'use strict';

describe('Ville Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockVille, MockPays;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockVille = jasmine.createSpy('MockVille');
        MockPays = jasmine.createSpy('MockPays');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Ville': MockVille,
            'Pays': MockPays
        };
        createController = function() {
            $injector.get('$controller')("VilleDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'articleSellingApp:villeUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
