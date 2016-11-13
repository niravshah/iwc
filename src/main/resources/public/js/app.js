var app = angular.module('iwc-demo', ['ui.router', 'highcharts-ng','angularSpinner'])
    .config(function ($httpProvider) {

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        /** Controllers **/
        app.controller('MainController', MainController);
    });

