var MainController = ['$scope', '$http', function ($scope, $http) {

    $scope.authorities = [];
    $scope.selectedAuthority = null;
    $scope.initMethod = function () {
        $http.get('/api/authorities').then(function (response) {
            $scope.authorities = response.data.authorities;
        });
    };

    $scope.getEstablishments = function () {
        var url = '/api/authority/' + $scope.selectedAuthority + '/stats';
        $http.get(url).then(function (response) {
            console.log(response.data)
        })
    }
}];
