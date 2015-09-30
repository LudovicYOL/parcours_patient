/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

bureauServices.factory('Crayons', ['$resource', function($resource) {
    return $resource('/bureau/webresources/generic/crayons/:id', 
    { id : '@id'}, { charge: {method: 'POST', params: {charge:true}, isArray:false}});
    
}]);
