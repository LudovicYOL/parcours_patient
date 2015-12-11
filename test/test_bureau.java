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
        serv.deleteAllMouvements();
        serv.deleteAllUniteFonctionnelles();
        serv.deleteAllLits();
        serv.deleteAllAdmissions();
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
        serv.getAdmissionsFromFile();
        
        List<Admission> res = serv.getAdmissionByIpp(105);
        assert(!res.isEmpty());
        assert(res.size() == 1);
        
        Admission ad_test = serv.getAdmissionByIep(63);
        assertNotNull(ad_test);
        System.out.println("Admission par IEP : "+ ad_test);
        
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
        Mouvement mv1 = serv.newMouvement(ad_test, lit1, uf1,new Date());
        //Mouvement mv2 = serv.newMouvement(ad2, lit4, uf2,new Date());
        //Mouvement mv3 = serv.newMouvement(ad1, lit1, uf2,new Date());
        
        //une fois le lit affecté à un mouvement, le lit est occupé
        assert(mv1.getLit().getOccupe()==true);
        
        assert(serv.getLitById(mv1.getLit().getId_lit()).getOccupe()==true);
        
        List<Mouvement> mouvs = serv.getAllMouvements();
        assert(!mouvs.isEmpty());
        assert(mouvs.size() == 1);
        
        // Liste mouvement selon admission
        List<Mouvement> mouvs_test = serv.getMouvementByIep(ad_test.getIep());
        assert(!mouvs.isEmpty());
        System.out.println("Mouvement pour admission test :"+ mouvs_test);
        
        
        // Cloturer Mouvement
        Date date_cloture =  new Date();
        serv.clotureMouvement(mv1, date_cloture);
        
        
        
        
        
    }
    /* MANQUE 
        La vérification lit-Mouvement SINGER
    */
}
