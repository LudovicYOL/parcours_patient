/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.util.Date;
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
    
    /* Admission */
    public Admission newAdmission(int ipp_, int type_){
        Admission a = new Admission();
        a.setIpp(ipp_);
        a.setType(type_);
        
        EntityManager em = fact.createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
        return a;
    }
    
    public Admission getAdmissionById(int iep) {
        EntityManager em = fact.createEntityManager();
	Admission res = em.find( Admission.class, iep );
        em.close();
        return res;
    }
    
     public List<Admission> getAdmissionByIpp(int ipp) {
        EntityManager em = fact.createEntityManager();
	TypedQuery<Admission> query = em.createQuery("SELECT a FROM Admission a WHERE a.ipp = :ipp", Admission.class)
                .setParameter("ipp",ipp);
        List<Admission> res = query.getResultList();
        em.close();
        return res;
    }
    
     public List<Admission> getAllAdmissions() {
        EntityManager em = fact.createEntityManager();
	TypedQuery<Admission> query = em.createQuery("SELECT a FROM Admission a", Admission.class);
        List<Admission> res = query.getResultList();
        em.close();
        return res;
    }
     
     public void deleteAllAdmissions() {
        EntityManager em = fact.createEntityManager();
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Admission").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
     
    /* LIT */ 
    public Lit newLit (String room){
        Lit l = new Lit();
        l.setChambre(room);
        l.setOccupe(false);
        
        EntityManager em = fact.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
        return l;
    }
    
    public Lit getLitById(int id_lit) {
        EntityManager em = fact.createEntityManager();
	Lit res = em.find( Lit.class, id_lit );
        em.close();
        return res;
    }
    
     public List<Lit> getLitByUF(String nomUF) {
        EntityManager em = fact.createEntityManager();
	TypedQuery<Lit> query = em.createQuery("SELECT u.lits FROM UniteFonctionnelle u WHERE u.nom = :nomUF", Lit.class)
                .setParameter("nomUF",nomUF);
        List<Lit> res = query.getResultList();
        em.close();
        return res;
    }
    
     public List<Lit> getAllLits() {
        EntityManager em = fact.createEntityManager();
	TypedQuery<Lit> query = em.createQuery("SELECT l FROM Lit l", Lit.class);
        List<Lit> res = query.getResultList();
        em.close();
        return res;
    }
     
    public void deleteAllLits() {
        EntityManager em = fact.createEntityManager();
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Lit").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    /* MOUVEMENT */
    public Mouvement newMouvement (Admission ad, Lit lit, UniteFonctionnelle uf, Date date_entree){
        EntityManager em = fact.createEntityManager();
        Mouvement m = new Mouvement();
        em.getTransaction().begin();
        em.persist(m);
        m.setDate_entree(date_entree);
        //Récuperer le type d'admission
        //si consultation externe (3) date entrée = date de sortie
        if(ad.getType()==3){m.setDate_sortie(date_entree);}
        //si hospitalisation (1) date de sortie = date entrée + 3
        if(ad.getType()==1){m.setDate_sortie(new Date(date_entree.getDay()+3));}
        //si urgence (2) date de sortie = null
        if(ad.getType()==2){m.setDate_sortie(null);}
        m.setAdmission(ad);
        m.setLit(lit);
        lit.setOccupe(Boolean.TRUE);
        m.setUf(uf);
        em.merge(m);
        em.getTransaction().commit();
        em.close();
        return m;
    }
    
     public Mouvement getMouvementById(int id_mouv) {
        EntityManager em = fact.createEntityManager();
	Mouvement res = em.find( Mouvement.class, id_mouv );
        em.close();
        return res;
    }
      
     public List<Mouvement> getAllMouvements() {
        EntityManager em = fact.createEntityManager();
	TypedQuery<Mouvement> query = em.createQuery("SELECT m FROM Mouvement m", Mouvement.class);
        List<Mouvement> res = query.getResultList();
        em.close();
        return res;
    }
     
     public void deleteAllMouvements() {
        EntityManager em = fact.createEntityManager();
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Mouvement").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
  
    /* UF */
     public UniteFonctionnelle newUniteFonctionnelle (String nom,List<Lit> lits){
        EntityManager em = fact.createEntityManager();
        UniteFonctionnelle uf = new UniteFonctionnelle();
        em.getTransaction().begin();
        uf.setNom(nom);
        uf.setLits(lits);
        em.persist(uf);
        em.getTransaction().commit();
        em.close();
        return uf;
    }
    
     public UniteFonctionnelle getUniteFonctionnelleById(int id_uf) {
        EntityManager em = fact.createEntityManager();
	UniteFonctionnelle res = em.find( UniteFonctionnelle.class, id_uf );
        em.close();
        return res;
    }
     
     public List<UniteFonctionnelle> getAllUnitesFonctionnelles() {
        EntityManager em = fact.createEntityManager();
	TypedQuery<UniteFonctionnelle> query = em.createQuery("SELECT u FROM UniteFonctionnelle u", UniteFonctionnelle.class);
        List<UniteFonctionnelle> res = query.getResultList();
        em.close();
        return res;
    }
     
     public void deleteAllUniteFonctionnelles() {
        EntityManager em = fact.createEntityManager();
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM UniteFonctionnelle").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
     

     
    /***************************************************/
    
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