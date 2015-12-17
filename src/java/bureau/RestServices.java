
package bureau;

import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Nicolas Singer
 */
@Path("generic")
public class RestServices {

    @Context
    private UriInfo context;
    
    Services serv;

    public RestServices() {
        serv = new Services(DatabaseUtils.fact());
    }


    
    @GET
    @Path("admissions/{iep}")
    @Produces("application/json")
    public Admission getAdmission(@PathParam("iep") int iep) {
        return serv.getAdmissionByIep(iep);
    }
    
    @GET
    @Path("admissions")
    @Produces("application/json")
    public List<Admission> getAdmissions() {
        return serv.getAllAdmissions();
    }
    
    @POST
    @Path("admissions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Admission newAdmission(Admission ad) {
        serv.newAdmission(ad);
        System.out.println("id:"+ad.getIep());
        return ad;
    }
    
    @POST
    @Path("admissions/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editAdmission(Admission ad) {
        serv.editAdmission(ad);
        return Response.status(200).entity(ad).build();
    }
    
    @DELETE
    @Path("admissions/{id}")
    public Response removeAdmission(@PathParam("id") int id) {
        serv.removeAdmission(id);
        return Response.status(200).build();
    }
    
    
    @DELETE
    @Path("admissions")
    public Response deleteAllAdmissions() {
        serv.deleteAllAdmissions();
        return Response.status(200).build();
    }
    
    @GET
    @Path("UF/{nom}/lit")
    @Produces("application/json")
    public List<Lit> getLitByUF(@PathParam("nom") String nomUF) {
        return serv.getLitByUF(nomUF);
    }
    
    @GET
    @Path("lits")
    @Produces("application/json")
    public List<Lit> getLits() {
        return serv.getAllLits();
    }
    
    @POST
    @Path("lit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editLit(Lit lit) {
        serv.editLit(lit);
        return Response.status(200).entity(lit).build();
    }
    
    //je foire
    @GET
    @Path("lit/{id}")
    @Produces("application/json")
    public Lit getLitById(@PathParam("id") int id) {
        return serv.getLitById(id);
    }
    
    
    @GET
    @Path("mouvements")
    @Produces("application/json")
    public List<Mouvement> getMouvements() {
        return serv.getAllMouvements();
    }
    
    
    @GET
    @Path("mouvement/{id}")
    @Produces("application/json")
    public Mouvement getMouvementById(@PathParam("id") int id) {
        return serv.getMouvementById(id);
    }
    
    
    @GET
    @Path("mouvements/{iep}")
    @Produces("application/json")
    public List<Mouvement> getMouvementByIep(@PathParam("iep") int iep) {
        return serv.getMouvementByIep(iep);
    }
    
    @POST
    @Path("mouvements")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Mouvement newMouvement(Mouvement mouv){
        serv.newMouvement(mouv.getAdmission(), mouv.getLit(), mouv.getUf(), mouv.getDate_entree());
        return mouv;
    }
    
    @POST
    @Path("mouvement/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editMouvement(Mouvement m) {
        serv.editMouvement(m);
        return Response.status(200).entity(m).build();
    }
    
    @GET
    @Path("mouvement/cloture/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cloturerMouvement(@PathParam("id") int id) {
        Mouvement m = serv.getMouvementById(id);
        serv.clotureMouvement(m);
        return Response.status(200).entity(m).build();
    }
    
    @DELETE
    @Path("mouvements/{iep}")
    public Response removeMouvement(@PathParam("iep") int id_mouv) {
        serv.removeMouvement(id_mouv);
        return Response.status(200).build();
    }
    
    @GET
    @Path("lit/changeStatut/{id}")
    public Response changeStatut(@PathParam("id") int id) {
        Lit lit = serv.getLitById(id);
        serv.changeStatutLit(lit);
        return Response.status(200).build();
    }
    
    @GET
    @Path("unitefonctionnelle")
    @Produces("application/json")
    public List<UniteFonctionnelle> getUFs() {
        return serv.getAllUnitesFonctionnelles();
    }
    
    
    @GET
    @Path("unitefonctionnelle/{id}")
    @Produces("application/json")
    public UniteFonctionnelle getUniteFonctionnelleById(@PathParam("id") int id) {
        return serv.getUniteFonctionnelleById(id);
    }
    
    @GET
    @Path("/updateFromFile")
    public Response updateFromFile(){
        serv.getAdmissionsFromFile();
        return Response.status(200).build();
    }
}
