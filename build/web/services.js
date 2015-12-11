
angular.module('monApp').factory('Crayons', ['$resource', function($resource) {
        
    //  voir https://docs.angularjs.org/api/ngResource/service/$resource pour la doc de cet objet
    return $resource('/bureau/webresources/generic/crayons/:id', { id : '@id'} );
    
}]);

angular.module('monApp').factory('Admissions', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/admissions/:id', { id : '@iep'} );
    
}]);

angular.module('monApp').factory('Mouvements', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/mouvements/:id', { id : '@iep'} );
    
}]);

angular.module('monApp').factory('Mouvement', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/mouvement/:id', { id : '@id_mouv'} );
    
}]);

angular.module('monApp').factory('Mouvement', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/mouvement/:id', { id : '@id_mouv'} );
    
}]);

angular.module('monApp').factory('Lits', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/lits/:id', { id : '@id'} );
    
}]);

angular.module('monApp').factory('Ufs', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/unitefonctionnelle/:id', { id : '@id'} );
    
}]);

// Cloture mouvement
angular.module('monApp').factory('ClotureMouvement', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/mouvement/cloture/:id', { id : '@id'} );
    
}]);

// Changer statut lit
angular.module('monApp').factory('ChangerStatutLit', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/lit/changeStatut/:id', { id : '@id'} );
    
}]);

//Update from file
angular.module('monApp').factory('UpdateFromFile', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/updateFromFile');
    
}]);