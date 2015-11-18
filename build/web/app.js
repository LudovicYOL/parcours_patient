/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('monApp', ['ngRoute','ngResource']);

angular.module('monApp').config(['$routeProvider', function routeConfig($routeProvider) {
    $routeProvider
     .when('/', {
        controller: "CrayonsController as ctrl",
        templateUrl: 'listeCrayon.html'    
    })
     .when('/crayon/edit/:id', {
        controller: "CrayonEditController as ctrl",
        templateUrl: 'editCrayon.html'    
    })
     .when('/crayon/new', {
        controller: "CrayonNewController as ctrl",
        templateUrl: 'newCrayon.html'    
    })
    .when('/admission/', {
        controller: "AdmissionController as ctrl",
        templateUrl: 'listeAdmission.html'    
    })
    .when('/admission/new', {
        controller: "AdmissionNewController as ctrl",
        templateUrl: 'newAdmission.html'    
    })
    .otherwise({ redirectTo: '/'});
}]);

