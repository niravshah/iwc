var MainController = ['$scope', '$http', function ($scope, $http) {

    $scope.authorities = [];
    $scope.selectedAuthority = null;
    $scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
    $scope.data = [300, 500, 100];

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
