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
import javax.persistence.TypedQuery;
import org.hibernate.Session;

/**
 *
 * @author Kurasawa
 */
public class Services {
    
    EntityManagerFactory fact;

    public Services(EntityManagerFactory fact) {
        this.fact = fact;
    }

    public Crayon newCrayon(String couleur) {
        Crayon p = new Crayon();
        p.setCouleur(couleur);
        EntityManager em = fact.createEntityManager();
	em.getTransaction( ).begin( );
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        return p;
    }
    
    public Crayon getCrayonsById(int id) {
        EntityManager em = fact.createEntityManager();
	Crayon res = em.find( Crayon.class, id );
        em.close();
        return res;
    }
    
    public List<Crayon> getAllCrayons() {
        EntityManager em = fact.createEntityManager();
	TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c", Crayon.class);
        List<Crayon> res = query.getResultList();
        em.close();
        return res;
    }
    
    public List<Boite> getAllBoites() {
        EntityManager em = fact.createEntityManager();
	TypedQuery<Boite> query = em.createQuery("SELECT b FROM Boite b", Boite.class);
        List<Boite> res = query.getResultList();
        em.close();
        return res;
    }
    
    public List<Crayon> getCrayonsByCouleurId(String couleur) {
        EntityManager em = fact.createEntityManager();
        TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c WHERE c.couleur = :couleur", Crayon.class)
                .setParameter("couleur",couleur);
        List<Crayon> res = query.getResultList();
        em.close();
        return res;
    }
    
    public Boite newBoite(List<Crayon> crayons) {
        EntityManager em = fact.createEntityManager();
        Boite b = new Boite();
	em.getTransaction( ).begin( );
        b.setCrayons(crayons);
        em.persist(b);
        em.getTransaction().commit();
        em.close();
        return b;
    }
    
    public Boite getBoiteById(int id) {
        EntityManager em = fact.createEntityManager();
	Boite res = em.find( Boite.class, id );
        em.close();
        return res;
    }
    
    public List<Boite> getBoitesByCouleurDeCrayon(String couleur) {
        EntityManager em = fact.createEntityManager();
        TypedQuery<Boite> query = em.createQuery("SELECT b FROM Boite b JOIN b.crayons c WHERE c.couleur = :couleur", Boite.class)
                .setParameter("couleur",couleur);
        List<Boite> res =  query.getResultList();
        em.close();
        return res;
    }
    
    public void deleteAllBoites() {
        EntityManager em = fact.createEntityManager();
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Boite").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    public void deleteAllCrayons() {
        EntityManager em = fact.createEntityManager();
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Crayon").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}