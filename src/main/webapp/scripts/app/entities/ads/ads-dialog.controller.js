'use strict';

angular.module('articleSellingApp').controller('AdsDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Ads', 'Categorie', 'Image',
        function($scope, $stateParams, $modalInstance, entity, Ads, Categorie, Image) {

        $scope.ads = entity;
        $scope.categories = Categorie.query();
        $scope.images = Image.query();
        $scope.load = function(id) {
            Ads.get({id : id}, function(result) {
                $scope.ads = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('articleSellingApp:adsUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.ads.id != null) {
                Ads.update($scope.ads, onSaveFinished);
            } else {
                Ads.save($scope.ads, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
