/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bureau.Admission;
import bureau.Lit;
import bureau.UniteFonctionnelle;
import bureau.DatabaseUtils;
import bureau.Services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kurasawa
 */
public class test_lecture_fichier {
    
    static EntityManagerFactory fact;
    
    public test_lecture_fichier() {
       
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

    }
 
}
