/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bureau.Admission;
import bureau.Lit;
import bureau.Mouvement;
import bureau.UniteFonctionnelle;
import bureau.DatabaseUtils;
import bureau.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kurasawa
 */
public class test_bureau {
    
    static EntityManagerFactory fact;
    
    public test_bureau() {
       
    }
    
    static  void clean() {
        Services serv = new Services(DatabaseUtils.fact());
        serv.deleteAllAdmissions();
        serv.deleteAllLits();
        serv.deleteAllUniteFonctionnelles();
        serv.deleteAllMouvements();
        
        List<Admission> res = serv.getAllAdmissions();
        assert(res.isEmpty());
    }
    
    @BeforeClass
    public static void setUpClass() {
        clean();
    }
    
    @AfterClass
    public static void tearDownClass() {

    }
    
    @Before
    public void setUp() {
       
       
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    
    @Test
    public void init() {
        clean();
        Services serv = new Services(DatabaseUtils.fact());
        
        // Admission
        Admission ad1 = serv.newAdmission(1,1);
        assertNotNull(ad1); 
        Admission ad2 = serv.newAdmission(2,2);
        assertNotNull(ad2); 
        Admission ad3 = serv.newAdmission(1,3);
        assertNotNull(ad3); 
       
        List<Admission> res = serv.getAdmissionByIpp(1);
        assert(!res.isEmpty());
        assert(res.size() == 2);
        
        // Lit
        Lit lit1 = serv.newLit("Chambre 1");
        Lit lit2 = serv.newLit("Chambre 2");
        Lit lit3 = serv.newLit("Chambre 3");
        Lit lit4 = serv.newLit("Chambre 3");
        
        List<Lit> lit_cardio = new ArrayList<>();
        lit_cardio.add(lit1);
        lit_cardio.add(lit2);
        
        List<Lit> lit_neuro = new ArrayList<>();
        lit_neuro.add(lit3);
        lit_neuro.add(lit4);
        
        // UF
        System.out.println(lit_cardio);
        UniteFonctionnelle uf1 = serv.newUniteFonctionnelle("Cardiologie",lit_cardio);
        System.out.println("uf1:"+ uf1);
        UniteFonctionnelle uf2 = serv.newUniteFonctionnelle("Neurologie",lit_neuro);
        System.out.println("uf2:"+ uf2);
        
        List<UniteFonctionnelle> res3 = serv.getAllUnitesFonctionnelles();
        assert(!res3.isEmpty());
        assert(res3.size()==2);
        
        //Mouvement
        Mouvement mv1 = serv.newMouvement(ad1, lit1, uf1,new Date());
        Mouvement mv2 = serv.newMouvement(ad2, lit4, uf2, new Date());
        
        List<Mouvement> mouvs = serv.getAllMouvements();
        assert(!mouvs.isEmpty());
        assert(mouvs.size() == 2);
    }
    /* MANQUE 
        Changement de statut du lit
        La vérification lit-Mouvement SINGER
        Creer une fonction qui détermine une date de sortie provisoire en fonction du type d'admission
    */
}
