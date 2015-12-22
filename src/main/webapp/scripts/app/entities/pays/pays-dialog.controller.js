'use strict';

angular.module('articleSellingApp').controller('PaysDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Pays', 'Ville',
        function($scope, $stateParams, $modalInstance, entity, Pays, Ville) {

        $scope.pays = entity;
        $scope.villes = Ville.query();
        $scope.load = function(id) {
            Pays.get({id : id}, function(result) {
                $scope.pays = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('articleSellingApp:paysUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.pays.id != null) {
                Pays.update($scope.pays, onSaveFinished);
            } else {
                Pays.save($scope.pays, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
