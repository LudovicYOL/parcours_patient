
package bureau;

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
    @Path("crayons/{id}")
    @Produces("application/json")
    public Crayon getCrayons(@PathParam("id") int id) {
        return serv.getCrayonsById(id);
    }
    
    @GET
    @Path("crayons")
    @Produces("application/json")
    public List<Crayon> getAllCrayons() {
        return serv.getAllCrayons();
    }

    @GET
    @Path("boites")
    @Produces("application/json")
    public List<Boite> getBoites() {
        return serv.getAllBoites();
    }
    
    @POST
    @Path("crayons")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Crayon newCrayon(Crayon cr) {
        serv.newCrayon(cr);
        System.out.println("id:"+cr.getId());
        return cr;
    }
    
    @POST
    @Path("crayons/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCrayon(Crayon cr) {
        serv.editCrayon(cr);
        return Response.status(200).entity(cr).build();
    }
    
    @DELETE
    @Path("crayons/{id}")
    public Response removeCrayon(@PathParam("id") int id) {
        serv.removeCrayon(id);
        return Response.status(200).build();
    }


    
    @GET
    @Path("admission/{iep}")
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
    /*//je foire
    @GET
    @Path("lit/{id}")
    @Produces("application/json")
    public Lit getLitById(@PathParam("id") int id) {
        return serv.getLitById(id);
    }
    */
    @GET
    @Path("mouvements")
    @Produces("application/json")
    public List<Mouvement> getMouvements() {
        return serv.getAllMouvements();
    }
    
    @GET
    @Path("mouvement/{id}")
    @Produces("application/json")
    public Mouvement getMouvementById(@PathParam("id_mouv") int id_mouv) {
        return serv.getMouvementById(id_mouv);
    }
    /*
    @GET
    @Path("mouvement/{ad}")
    @Produces("application/json")
    public List<Mouvement> getMouvementByIep(@PathParam("ad") Admission ad) {
        return serv.getMouvementByIep(ad);
    }
    */
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
    
    
}
