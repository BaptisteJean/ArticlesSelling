'use strict';

angular.module('articleSellingApp').controller('CategorieDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Categorie', 'Ads',
        function($scope, $stateParams, $modalInstance, entity, Categorie, Ads) {

        $scope.categorie = entity;
        $scope.adss = Ads.query();
        $scope.load = function(id) {
            Categorie.get({id : id}, function(result) {
                $scope.categorie = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('articleSellingApp:categorieUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.categorie.id != null) {
                Categorie.update($scope.categorie, onSaveFinished);
            } else {
                Categorie.save($scope.categorie, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
