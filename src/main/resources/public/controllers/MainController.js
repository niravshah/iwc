var MainController = ['$scope', 'Authorities', function ($scope, Authorities) {
    $scope.authorities = Authorities.query();

    $scope.initMethod = function(){
        console.log('Init MainController')
    }
}];
