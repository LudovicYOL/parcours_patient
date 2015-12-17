

// ADMISSION
angular.module('monApp').controller('AdmissionController', [ 'Admissions', '$window','UpdateFromFile',
    function (Admissions, $window, UpdateFromFile) {
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
        
        this.moulinette = function(){
            this.reponse = UpdateFromFile.get();
            $window.location.reload();
        }
    }
])

.controller('AdmissionNewController', [ 'Admissions','$window',
   function(Admissions,$window) {
    this.ad = new Admissions();
    this.update = function() { 
        // appel POST asynchrone au service web sur /crayons
       this.ad.$save();
       $window.location.href = '/bureau/#/';
   };
}])


.controller('AdmissionEditController', [ 'Admissions', '$routeParams', '$window',
    function(Admissions, $routeParams, $window) {
        this.iep = $routeParams.id;
        this.ad = Admissions.get({id: $routeParams.id}); 
        this.update = function() {
            // appel POST asynchrone au service web sur /crayons/{id} 
            this.ad.$save();
            $window.location.href = '/bureau/#/';
        };
    }
]);


// MOUVEMENT
angular.module('monApp').controller('MouvementController', [ '$routeParams', 'Mouvements', 'ClotureMouvement',
    function ($routeParams, Mouvements, ClotureMouvement){
        this.iep = $routeParams.id;
        this.mouvements = Mouvements.query({id: this.iep});
        this.delete = function (mv) {
            mv.$delete({id:mv.id_mouv});
            this.mouvements.splice(this.mouvements.indexOf(mv), 1);
        };
    
        this.calendar = function (date) {
           return  moment(date).format('DD/MM/YYYY');
        };
    }
])

.controller('MouvementNewController', [ 'Mouvements', 'Admissions', 'Lits', 'Ufs', '$routeParams', '$window', 'ChangerStatutLit',
   function(Mouvements,Admissions,Lits,Ufs,$routeParams,$window, ChangerStatutLit) {
    this.iep = $routeParams.id;
    this.ad = Admissions.get({id: $routeParams.id});
    this.lits = Lits.query();
    this.ufs = Ufs.query();
    this.mv = new Mouvements();
    this.update = function() {
        ChangerStatutLit.get({id:this.mv.lit.id_lit});
        this.mv.date_entree = moment(this.date).format();
        this.mv.admission = this.ad;
        this.mv.$save();
        $window.location.href = '/bureau/#/mouvement/id/'+ $routeParams.id;
   };
  
}])

.controller('MouvementEditController', [ 'Mouvement', 'Lits', 'Ufs', '$routeParams', '$window',
    function(Mouvement, Lits, Ufs, $routeParams, $window) {
        this.id_mouv = $routeParams.id;
        this.mv = Mouvement.get({id: this.id_mouv});
        this.lits = Lits.query();
        this.ufs = Ufs.query();
        this.formatDate = function(date){
            return moment(date).format('DD/MM/YYYY');
        };
        this.update = function() {
            console.log(this.date_entree);
            this.mv.date_entree = moment(this.mv.date_entree).format();
            this.mv.date_sortie = moment(this.mv.date_sortie).format();
            this.mv.admission = this.mv.admission;
            this.mv.$save();
            $window.location.href = '/bureau/#/mouvement/id/'+ this.mv.admission.iep;
        };
    }
]);

// MOUVEMENT
angular.module('monApp').controller('LitsController', [ '$routeParams', '$window', 'Ufs', 'Lits','ChangerStatutLit',
    function ($routeParams, $window, Ufs, Lits, ChangerStatutLit) {
       this.ufs = Ufs.query();
       console.log(this.ufs);
        this.occupation = function (occupe) {
           if(occupe == false){
               return 'free';
           }else{
               return 'busy';
           }
        };
        this.libererLit = function (id) {
            ChangerStatutLit.get({id:id});
            $window.location.reload();
        };
    }
]);