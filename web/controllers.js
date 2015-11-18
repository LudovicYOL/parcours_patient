
angular.module('monApp').controller('CrayonsController', [ 'Crayons',
    function (Crayons) {
        this.crayons = Crayons.query();
        console.log(this.crayons);
        this.delete = function (cr) {
            // appel DELETE asynchrone au service web sur /crayons/{id}
            //cr.$delete();
            Crayons.delete(cr);
            // remet à jour le tableau des crayons en suprimant l'élément effacé
            this.crayons.splice(this.crayons.indexOf(cr), 1);
        };
    }
])

.controller('CrayonNewController', [ 'Crayons',
   function(Crayons) {
    this.cr = new Crayons();
    this.update = function() { 
        // appel POST asynchrone au service web sur /crayons
       this.cr.$save();
   };
}])


.controller('CrayonEditController', [ '$routeParams', 'Crayons', '$location',
function($routeParams, Crayons, $location) {
    this.cr = Crayons.get({id: $routeParams.id}); 
    this.update = function() {
        // appel POST asynchrone au service web sur /crayons/{id} 
        this.cr.$save();
        $location.path("/")
    };
}
]);



angular.module('monApp').controller('AdmissionController', [ 'Admissions',
    function (Admissions) {
        this.admissions = Admissions.query();
        console.log(this.admissions);
        this.delete = function (ad) {
            // appel DELETE asynchrone au service web sur /crayons/{id}
            //cr.$delete();
            Admissions.delete(ad);
            // remet à jour le tableau des crayons en suprimant l'élément effacé
            this.admissions.splice(this.admissions.indexOf(ad), 1);
        };
    }
])

.controller('AdmissionNewController', [ 'Admissions',
   function(Admissions) {
    this.ad = new Admissions();
    this.update = function() { 
        // appel POST asynchrone au service web sur /crayons
       this.ad.$save();
       //$location.path( "/admission" );
   };
}]);