/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Kurasawa
 */
@Path("generic")
public class RestServices {

    @Context
    private UriInfo context;
   
    /**
     * Creates a new instance of GenericResource
     */
    public RestServices() {
       
    }

    /**
     * Retrieves representation of an instance of bureau.RestServices
     * @return an instance of java.lang.String
     */
    @GET
    @Path("crayons/{id}")
    @Produces("application/json")
    public Crayon getCrayons(@PathParam("id") int id) {
        //TODO return proper representation object
        Services serv = new Services(DatabaseUtils.fact());
        return serv.getCrayonsById(id);
    }
    
    @GET
    @Path("crayons")
    @Produces("application/json")
    public List<Crayon> getAllCrayons() {
        //TODO return proper representation object
        Services serv = new Services(DatabaseUtils.fact());
         return serv.getAllCrayons();
    }

    @GET
    @Path("boites")
    @Produces("application/json")
    public List<Boite> getBoites() {
        //TODO return proper representation object
        Services serv = new Services(DatabaseUtils.fact());
        return serv.getAllBoites();
    }
   
}
