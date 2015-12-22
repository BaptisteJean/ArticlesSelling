'use strict';

angular.module('articleSellingApp').controller('VilleDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Ville', 'Pays',
        function($scope, $stateParams, $modalInstance, entity, Ville, Pays) {

        $scope.ville = entity;
        $scope.payss = Pays.query();
        $scope.load = function(id) {
            Ville.get({id : id}, function(result) {
                $scope.ville = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('articleSellingApp:villeUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.ville.id != null) {
                Ville.update($scope.ville, onSaveFinished);
            } else {
                Ville.save($scope.ville, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
