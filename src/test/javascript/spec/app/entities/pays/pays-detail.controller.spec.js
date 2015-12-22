'use strict';

describe('Pays Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockPays, MockVille;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockPays = jasmine.createSpy('MockPays');
        MockVille = jasmine.createSpy('MockVille');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Pays': MockPays,
            'Ville': MockVille
        };
        createController = function() {
            $injector.get('$controller')("PaysDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'articleSellingApp:paysUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
