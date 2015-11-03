

angular.module('monApp').factory('Crayons', ['$resource', function($resource) {
        
    //  voir https://docs.angularjs.org/api/ngResource/service/$resource pour la doc de cet objet
    return $resource('/bureau/webresources/generic/crayons/:id', { id : '@id'} );
    
}]);

angular.module('monApp').factory('Admissions', ['$resource', function($resource) {
        
    return $resource('/bureau/webresources/generic/admission/:id', { id : '@id'} );
    
}]);
