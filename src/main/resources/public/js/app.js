var app = angular.module('iwc-demo', ['ui.router','ngResource']);

/** Controllers **/
app.controller('MainController', MainController);
/** Services **/
app.factory('Authorities', Authorities);

app.run();