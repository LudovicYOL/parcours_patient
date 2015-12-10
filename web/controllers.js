
angular.module('monApp').controller('CrayonsController', [ 'Crayons',
    function (Crayons) {
        this.crayons = Crayons.query();
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


// ADMISSION
angular.module('monApp').controller('AdmissionController', [ 'Admissions', '$location',
    function (Admissions, $location) {
        this.admissions = Admissions.query();
        this.delete = function (ad) {
            // appel DELETE asynchrone au service web sur /crayons/{id}
            ad.$delete();
            //Admissions.delete(ad);
            // remet à jour le tableau des crayons en suprimant l'élément effacé
            this.admissions.splice(this.admissions.indexOf(ad), 1);
        };
        
        this.type = function(i){
           
            if(i==1){
                type = "Hospitalisation Programmée";
            }else if(i==2){
                type = "Urgences";
            }else if(i==3){
                type = "Consultation Externe";
            }else{
                type = "Erreur";
            }

            return type;
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
}])


.controller('AdmissionEditController', [ 'Admissions', '$routeParams', '$location',
    function(Admissions, $routeParams, $location) {
        this.iep = $routeParams.id;
        this.ad = Admissions.get({id: $routeParams.id}); 
        this.update = function() {
            // appel POST asynchrone au service web sur /crayons/{id} 
            this.ad.$save();
            $location.path("/");
        };
    }
]);


// MOUVEMENT
angular.module('monApp').controller('MouvementController', [ '$routeParams', 'Mouvements',
    function ($routeParams, Mouvements) {
        this.iep = $routeParams.id;
        this.mouvements = Mouvements.query({id: this.iep});
        this.delete = function (mv) {
            mv.$delete({id:mv.id_mouv});
            this.mouvements.splice(this.mouvements.indexOf(mv), 1);
        };
        
        this.calendar = function (date) {
           return  moment(date).format('DD/MM/YYYY hh:mm:ss');
        };
    }
])

.controller('MouvementNewController', [ 'Mouvements', 'Admissions', 'Lits', 'Ufs', '$routeParams', '$location',
   function(Mouvements,Admissions,Lits,Ufs,$routeParams,$location) {
    this.iep = $routeParams.id;
    this.ad = Admissions.get({id: $routeParams.id});
    this.lits = Lits.query();
    this.ufs = Ufs.query();
    this.date = new Date();
    this.mv = new Mouvements ();
    this.update = function() {
       this.mv.date_entree = moment(this.date).format();
       this.mv.admission = this.ad;
       this.mv.$save();
       $location.path( "/mouvement/id/"+ $routeParams.id );
   };
  
}])

.controller('MouvementEditController', [ 'Mouvement', 'Lits', 'Ufs', '$routeParams', '$location',
    function(Mouvement, Lits, Ufs, $routeParams, $location) {
        this.id_mouv = $routeParams.id;
        this.mv = Mouvement.get({id: this.id_mouv});
        this.lits = Lits.query();
        this.ufs = Ufs.query();
        this.formatDate = function(date){
            console.log('date :'+ date);
            console.log(moment(date).format('DD/MM/YYYY'));
            return moment(date).format('DD/MM/YYYY');
        };
        this.update = function() {
            this.mv.date_entree = moment(this.date_entree).format();
            this.mv.date_sortie = moment(this.date_sortie).format();
            this.mv.admission = this.ad;
            this.mv.$save();
            $location.path("/mouvement/id/"+ this.iep);
        };
    }
]);

// MOUVEMENT
angular.module('monApp').controller('LitsController', [ '$routeParams', 'Ufs',
    function ($routeParams, Ufs) {
        this.ufs = Ufs.query();
       console.log(this.ufs);
        this.occupation = function (occupe) {
           if(occupe == false){
               return 'free';
           }else{
               return 'busy';
           }
        };
    }
]);