/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('monApp', ['ngRoute','ngResource']);

angular.module('monApp').config(['$routeProvider', function routeConfig($routeProvider) {
    $routeProvider
    .when('/', {
        controller: "AdmissionController as ctrl",
        templateUrl: 'listeAdmission.html'    
    })
    .when('/admission/new', {
        controller: "AdmissionNewController as ctrl",
        templateUrl: 'newAdmission.html'    
    })
    .when('/admission/edit/:id', {
        controller: "AdmissionEditController as ctrl",
        templateUrl: 'editAdmission.html'    
    })
    .when('/mouvement/id/:id', {
        controller: "MouvementController as ctrl",
        templateUrl: 'listeMouvement.html'    
    })
    .when('/mouvement/new/:id', {
        controller: "MouvementNewController as ctrl",
        templateUrl: 'newMouvement.html'    
    })
    .otherwise({ redirectTo: '/'});
}]);

/* .when('/crayon/edit/:id', {
        controller: "CrayonEditController as ctrl",
        templateUrl: 'editCrayon.html'    
    })
     .when('/crayon/new', {
        controller: "CrayonNewController as ctrl",
        templateUrl: 'newCrayon.html'    
    }) */
