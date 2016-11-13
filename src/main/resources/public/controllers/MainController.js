var MainController = ['$scope', '$http', 'usSpinnerService', function ($scope, $http,usSpinnerService) {

    $scope.tableData = [];
    $scope.authorities = [];
    $scope.selectedAuthority = null;
    $scope.chartConfig = {
        options: {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: true,
                type: 'pie'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: 'black'
                        }
                    }
                }
            }
        },
        title: {
            text: 'Establishment Rating Summary'
        },
        series: [{
            name: 'Establishments',
            colorByPoint: true,
            data: []
        }]
    };

    $scope.initMethod = function () {
        $http.get('/api/authorities').then(function (response) {
            $scope.authorities = response.data.authorities;
        });
    };

    $scope.getEstablishments = function () {
        usSpinnerService.spin('spinner-1');
        var url = '/api/authority/' + $scope.selectedAuthority + '/stats';
        $http.get(url).then(function (response) {
            $scope.chartConfig.series[0].data = response.data.statItems;
            $scope.tableData = response.data.statItems;
            usSpinnerService.stop('spinner-1');
        })
    }
}];
