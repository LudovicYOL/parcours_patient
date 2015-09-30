/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function bureauRouteConfig($routeProvider) {
    $routeProvider
     .when('/', {
        controller: CrayonsController,
        templateUrl: 'listeCrayon.html'    
    })
    .otherwise({ redirectTo: '/'});
}

bureauServices.config(bureauRouteConfig);



function CrayonsController($scope, Crayons) {
   $scope.crayons = Crayons.query();
   // -> ligne pour obtenir le crayon d'identifiant 19 :  console.dir(Crayons.get({id:19}));
}

