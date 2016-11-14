var app = angular.module('iwc-demo', ['ui.router', 'highcharts-ng','angularSpinner'])
    .config(function ($httpProvider,usSpinnerConfigProvider) {

        usSpinnerConfigProvider.setTheme('bigWhite', {color: 'white', radius: 60});

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        /** Controllers **/
        app.controller('MainController', MainController);
    });

