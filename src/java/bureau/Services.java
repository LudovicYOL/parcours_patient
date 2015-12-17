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
import java.io.FileWriter;
import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
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
    public void editLit(Lit l){
        em.getTransaction().begin();
        em.merge(l);
        em.getTransaction().commit();
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
        //Récuperer le type d'admission
        m.setDate_entree(date_entree);
        //si hospitalisation (1) date de sortie = date entrée + 3
        System.out.println("test :"+ ad.getType());
        if(ad.getType()==1){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date_entree);
            cal.add(Calendar.DATE, 3); //un nombre négatif décrémente la date
            m.setDate_sortie(cal.getTime());
            System.out.println("date de sortie  d'hospitalisation fixée à :"+ cal.getTime());
        }
        //si urgence (2) date de sortie = date entrée + 10
        else if(ad.getType()==2){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date_entree);
            cal.add(Calendar.DATE, 10); 
            m.setDate_sortie(cal.getTime());
            System.out.println("date de sortie  d'hospitalisation fixée à :"+ cal.getTime());
        }
        //si consultation externe (3) date entrée = date de sortie
        else{m.setDate_sortie(date_entree);}
        
        m.setAdmission(ad);
        //em.persist(lit);
        m.setLit(lit);
        m.getLit().setOccupe(Boolean.TRUE);
        System.out.println (" le lit "+lit+" ou "+m.getLit()+" est "+m.getLit().getOccupe()+ " et "+lit.getOccupe());
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
    public void clotureMouvement(Mouvement m){
         em.getTransaction().begin();
         Lit l = m.getLit();
         l.setOccupe(Boolean.FALSE);
         em.merge(m);
         em.getTransaction().commit();
    }
    public void removeMouvement(int id_mouv) {
        Mouvement mv = em.find( Mouvement.class, id_mouv );
	em.getTransaction( ).begin( );
        em.remove(mv);
        em.getTransaction().commit();
    }
    
     public void changeStatutLit(Lit lit) {
	em.getTransaction( ).begin( );
        if(lit.getOccupe()){
            lit.setOccupe(Boolean.FALSE);
        }else{
            lit.setOccupe(Boolean.TRUE);
        }
        em.merge(lit);
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
    public UniteFonctionnelle getUniteFontionnelleByNom(String nom){
        UniteFonctionnelle res = new UniteFonctionnelle();
        List<UniteFonctionnelle> lite_uf = getAllUnitesFonctionnelles();
        for(UniteFonctionnelle uf : lite_uf){
            if(nom.equals(uf.getNom())){
                res=uf;
            }
        }
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
            File repertoire = new File("\\\\172.18.8.70\\temp\\PP_PAT_IN");// \\\\178.18.8.70\\temp
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
                    String date_sortie = admissionXML.getChild("date_sortie").getValue();
                    DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
                    Date date_s = format.parse(date_sortie);
                    String date_entree = admissionXML.getChild("date_sortie").getValue();
                    Date date_e = format.parse(date_entree);
                    List<Mouvement> listemouv = getMouvementByIep(iep);
                    if(listemouv!=null){
                        Mouvement mouv = listemouv.get(listemouv.size());
                        updateMouvement(mouv, mouv.getAdmission(), mouv.getLit(), mouv.getUf(), date_e, date_s);
                    }else{
                        System.out.println("pas de mouvement pour cette admission");
                    }
                    
                }
                if(listefichiers[i].startsWith("mouv")){
                    System.out.println("fichier new trouvé");
                    Document doc = builder.build(""+repertoire+"\\"+listefichiers[i]);
                    Element root = doc.getRootElement();
                    Element mouvementXML = root;
                    String service = "Cardiologie" ;
                    int ipp;
                    int iep;
                    Lit lit = new Lit();
                    String date_entree = mouvementXML.getChild("date_entree").getValue();
                    DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
                    Date date = format.parse(date_entree);
                    ipp = parseInt(mouvementXML.getChild("ipp").getValue());
                    iep = parseInt(mouvementXML.getChild("iep").getValue());
                    if(mouvementXML.getChild("service").getText().endsWith("2"))
                        service="Radio 2";
                    if(mouvementXML.getChild("service").getText().endsWith("1"))
                        service="Radio 1";
                    List<Lit> liste_lits = getLitByUF(service);
                    for(Lit buff_lit : liste_lits){
                        if(!buff_lit.getOccupe()){
                            lit=buff_lit;
                        }
                    }
                    newMouvement(getAdmissionByIep(iep), lit ,getUniteFontionnelleByNom(service), date);
                    System.out.println("nouvelle admission ajoutée");
                }
            }            
         }catch(Exception e){
             System.out.println("Erreur de lecture du fichier d'admission");
         }
     }
}
