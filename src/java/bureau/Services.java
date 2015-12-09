/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import static java.lang.Integer.parseInt;
import java.util.List;
import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;
import org.jdom2.transform.*;

/**
 *
 * @author Kurasawa
 */
public class Services {
    
    EntityManagerFactory fact;
    EntityManager em;
    
    public Services(EntityManagerFactory fact) {
        this.fact = fact;
        this.em = fact.createEntityManager();
    }
    
    public void close() {
        em.close();
    }
    public void newCrayon(Crayon cr) {
	em.getTransaction( ).begin( );
        em.persist(cr);
        em.getTransaction().commit();
    }

    public Crayon newCrayon(String couleur) {
        Crayon p = new Crayon();
        p.setCouleur(couleur);
     
	em.getTransaction( ).begin( );
        em.persist(p);
        em.getTransaction().commit();
      
        return p;
    }
    
    public void removeCrayon(int id) {
       
        Crayon cr = em.find( Crayon.class, id );
	em.getTransaction( ).begin( );
        em.remove(cr);
        em.getTransaction().commit();
       
    }
  
    public void editCrayon(Crayon cr) {
      
	em.getTransaction( ).begin( );
        em.merge(cr);
        em.getTransaction().commit();
     
    }
    
    public Crayon getCrayonsById(int id) {
       
	Crayon res = em.find( Crayon.class, id );
      
        return res;
    }
    
    public List<Crayon> getAllCrayons() {
      
	TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c", Crayon.class);
        List<Crayon> res = query.getResultList();
       
        return res;
    }
    
    public List<Boite> getAllBoites() {
      
	TypedQuery<Boite> query = em.createQuery("SELECT b FROM Boite b", Boite.class);
        List<Boite> res = query.getResultList();
      
        return res;
    }
    
    public List<Crayon> getCrayonsByCouleurId(String couleur) {
     
        TypedQuery<Crayon> query = em.createQuery("SELECT c FROM Crayon c WHERE c.couleur = :couleur", Crayon.class)
                .setParameter("couleur",couleur);
        List<Crayon> res = query.getResultList();
     
        return res;
    }
    
    public Boite newBoite(List<Crayon> crayons) {
       
        Boite b = new Boite();
	em.getTransaction( ).begin( );
        b.setCrayons(crayons);
        em.persist(b);
        em.getTransaction().commit();
       
        return b;
    }
    
    public void updateBoite(Boite b) {
        em.getTransaction( ).begin( );
        em.persist(b);
        em.getTransaction().commit();
    }
    
    public Boite getBoiteById(int id) {
        
	Boite res = em.find( Boite.class, id );
       
        return res;
    }
    
    public List<Boite> getBoitesByCouleurDeCrayon(String couleur) {
       
        TypedQuery<Boite> query = em.createQuery("SELECT b FROM Boite b JOIN b.crayons c WHERE c.couleur = :couleur", Boite.class)
                .setParameter("couleur",couleur);
        List<Boite> res =  query.getResultList();
       
        return res;
    }
    
    public void deleteAllBoites() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Boite").executeUpdate();
        em.getTransaction().commit();
        
    }
    
    public void deleteAllCrayons() {
      
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Crayon").executeUpdate();
        em.getTransaction().commit();
        
    }
    
    /******************************************************************************************************/
    
    /* Admission */
    public void newAdmission(Admission ad) {
	em.getTransaction( ).begin( );
        em.persist(ad);
        em.getTransaction().commit();
    }
    public void removeAdmission(int id) {
       
        Admission ad = em.find( Admission.class, id );
	em.getTransaction( ).begin( );
        em.remove(ad);
        em.getTransaction().commit();
       
    }
    public void editAdmission(Admission ad) {
      
	em.getTransaction( ).begin( );
        em.merge(ad);
        em.getTransaction().commit();
     
    }
    public Admission newAdmission(int ipp_, int type_, int iep_){
        Admission a = new Admission();
        a.setIpp(ipp_);
        a.setType(type_);
        a.setIep(iep_);
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        return a;
    }
    public Admission getAdmissionByIep(int iep) {
	Admission res = em.find( Admission.class, iep );
        return res;
    }
    public List<Admission> getAdmissionByIpp(int ipp) {
	TypedQuery<Admission> query = em.createQuery("SELECT a FROM Admission a WHERE a.ipp = :ipp", Admission.class)
                .setParameter("ipp",ipp);
        List<Admission> res = query.getResultList();
        return res;
    }
    public List<Admission> getAllAdmissions() {
	TypedQuery<Admission> query = em.createQuery("SELECT a FROM Admission a", Admission.class);
        List<Admission> res = query.getResultList();
        return res;
    }
    public void deleteAllAdmissions() {
        em.getTransaction().begin( );
        em.createQuery("DELETE FROM Admission").executeUpdate();
        em.getTransaction().commit();
    }
    public void updateAdmission(Admission ad, int ipp_, int type_){
        ad.setIpp(ipp_);
        ad.setType(type_);
        em.getTransaction().begin();
        em.persist(ad);
        em.getTransaction().commit();
     }
     
    /* LIT */ 
    public Lit newLit (String room){
        Lit l = new Lit();
        l.setChambre(room);
        l.setOccupe(false);
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
        return l;
    }
    public void updateLit(Lit l){
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
     }
    public Lit getLitById(int id_lit) {
	Lit res = em.find( Lit.class, id_lit );
        return res;
    }
    public List<Lit> getLitByUF(String nomUF) {
	TypedQuery<Lit> query = em.createQuery("SELECT u.lits FROM UniteFonctionnelle u WHERE u.nom = :nomUF", Lit.class)
                .setParameter("nomUF",nomUF);
        List<Lit> res = query.getResultList();
        return res;
    }
    public List<Lit> getAllLits() {
	TypedQuery<Lit> query = em.createQuery("SELECT l FROM Lit l", Lit.class);
        List<Lit> res = query.getResultList();
        return res;
    }
    public void deleteAllLits() {
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Lit").executeUpdate();
        em.getTransaction().commit();
    }
    
    
    /* MOUVEMENT */
    public Mouvement newMouvement (Admission ad, Lit lit, UniteFonctionnelle uf, Date date_entree){
        Mouvement m = new Mouvement();
        em.getTransaction().begin();
  
        m.setDate_entree(date_entree);
        //Récuperer le type d'admission
        
        
        //si hospitalisation (1) date de sortie = date entrée + 3
        System.out.println("test :"+ ad.getType());
        if(ad.getType()==1){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.DAY_OF_MONTH+3);
            m.setDate_sortie(calendar.getTime());
        }
        //si urgence (2) date de sortie = null
        else if(ad.getType()==2){m.setDate_sortie(null);}
        //si consultation externe (3) date entrée = date de sortie
        else{m.setDate_sortie(date_entree);}
        
        m.setAdmission(ad);
        lit.setOccupe(Boolean.TRUE);
        //em.persist(lit);
        m.setLit(lit);
       
        m.setUf(uf);
        em.persist(m);
        em.getTransaction().commit();
        return m;
    }
    public void updateMouvement (Mouvement m, Admission ad, Lit lit, UniteFonctionnelle uf, Date date_entree, Date date_sortie){
        em.getTransaction().begin();
        m.setDate_entree(date_entree);
        //Récuperer le type d'admission
        m.setDate_sortie(date_sortie);
        m.setAdmission(ad);
        m.setLit(lit);
        m.setUf(uf);
        em.persist(m);
        em.getTransaction().commit();
    }
    
    public void editMouvement (Mouvement m){
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
    }
    public void clotureMouvement(Mouvement m, Date date_sortie){
         em.getTransaction().begin();
         m.setDate_sortie(date_sortie);
         Lit l = m.getLit();
         l.setOccupe(Boolean.FALSE);
         em.persist(l);
         em.persist(m);
         em.getTransaction().commit();
    }
    public void removeMouvement(int id_mouv) {
        Mouvement mv = em.find( Mouvement.class, id_mouv );
	em.getTransaction( ).begin( );
        em.remove(mv);
        em.getTransaction().commit();
    }
    public Mouvement getMouvementById(int id_mouv) {
	Mouvement res = em.find( Mouvement.class, id_mouv );
        return res;
    }
    public List<Mouvement> getAllMouvements() {
	TypedQuery<Mouvement> query = em.createQuery("SELECT m FROM Mouvement m", Mouvement.class);
        List<Mouvement> res = query.getResultList();
        return res;
    }
    public List<Mouvement> getMouvementByIep(int iep){
        TypedQuery<Mouvement> query = em.createQuery("SELECT m FROM Mouvement m WHERE m.admission.iep = :iep", Mouvement.class).setParameter("iep", iep);
        List<Mouvement> res = query.getResultList();
        return res;
    }
    public void deleteAllMouvements() {
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM Mouvement").executeUpdate();
        em.getTransaction().commit();
    }
  
    /* UF */
    public UniteFonctionnelle newUniteFonctionnelle (String nom,List<Lit> lits){
        UniteFonctionnelle uf = new UniteFonctionnelle();
        em.getTransaction().begin();
        uf.setNom(nom);
        uf.setLits(lits);
        em.persist(uf);
        em.getTransaction().commit();
        return uf;
    }
    public UniteFonctionnelle getUniteFonctionnelleById(int id_uf) {
	UniteFonctionnelle res = em.find( UniteFonctionnelle.class, id_uf );
        return res;
    }
    public List<UniteFonctionnelle> getAllUnitesFonctionnelles() {
	TypedQuery<UniteFonctionnelle> query = em.createQuery("SELECT u FROM UniteFonctionnelle u", UniteFonctionnelle.class);
        List<UniteFonctionnelle> res = query.getResultList();
        return res;
    }
    public void deleteAllUniteFonctionnelles() {
        em.getTransaction( ).begin( );
        em.createQuery("DELETE FROM UniteFonctionnelle").executeUpdate();
        em.getTransaction().commit();
    }
    
    /*Alomentation de la base de données*/
    public void getAdmissionsFromFile (){
         SAXBuilder builder = new SAXBuilder();
         try{
            //on liste et on parcours les fichiers pour trouver les .xml qui sont en ajout et en edit 
            File repertoire = new File("C:\\Users\\areceveu");
            System.out.println("new file");
            String [] listefichiers;
            int i;
            System.out.println("int i string liste fichier");
            listefichiers=repertoire.list();
            System.out.println("repertoire.list()");
            for(i=0;i<listefichiers.length;i++){
                System.out.println("dans la boucle");
                //si le fichier est en ajout
                if(listefichiers[i].startsWith("new")){
                    System.out.println("fichier new trouvé");
                    Document doc = builder.build(""+repertoire+"\\"+listefichiers[i]);
                    Element root = doc.getRootElement();
                    Element admissionXML = root;
                    int type=1;
                    int ipp;
                    int iep;
                    ipp = parseInt(admissionXML.getChild("patient").getAttributeValue("ipp"));
                    iep = parseInt(admissionXML.getAttributeValue("iep"));
                    if(admissionXML.getChild("type").getText().startsWith("c"))
                        type=3;
                    if(admissionXML.getChild("type").getText().startsWith("h"))
                        type=1;
                    if(admissionXML.getChild("type").getText().startsWith("u"))
                        type=2;
                    newAdmission(ipp, type, iep);
                    System.out.println("nouvelle admission ajoutée");
                }
                if(listefichiers[i].startsWith("update")){
                    System.out.println("fichier en update trouvé");
                    Document doc = builder.build(""+repertoire+"\\"+listefichiers[i]);
                    Element root = doc.getRootElement();
                    Element admissionXML = root;
                    int type=1;
                    int iep = parseInt(admissionXML.getAttributeValue("iep"));
                    if(admissionXML.getChild("type").getText().startsWith("c"))
                        type=3;
                    if(admissionXML.getChild("type").getText().startsWith("h"))
                        type=1;
                    if(admissionXML.getChild("type").getText().startsWith("u"))
                        type=2;
                    if(getAdmissionByIep(iep)==null){
                        System.out.println("Il n'y a pas d'admission avec cet IEP");
                    }else{
                        Admission ad = getAdmissionByIep(iep);
                        ad.setType(type);
                        editAdmission(ad);
                        System.out.println("admission modifiée");
                    }
                    
                }
            }
            /* 
            Document doc = builder.build("C:\\Users\\areceveu\\admission.xml");
            Element root = doc.getRootElement();
            List<Element> admissions = root.getChildren("admission");
            int type=1;
            int ipp;
            int iep;
            for(Element admissionXML : admissions){
                ipp = parseInt(admissionXML.getChild("patient").getAttributeValue("ipp"));
                iep = parseInt(admissionXML.getAttributeValue("iep"));
                if(admissionXML.getChild("type").getText().startsWith("c"))
                    type=3;
                if(admissionXML.getChild("type").getText().startsWith("h"))
                    type=1;
                if(admissionXML.getChild("type").getText().startsWith("u"))
                    type=2;
                newAdmission(ipp, type, iep);
            }*/
            
         }catch(Exception e){
             System.out.println("Erreur de lecture du fichier d'admission");
         }
     }
}