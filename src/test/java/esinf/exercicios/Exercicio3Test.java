package esinf.exercicios;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import esinf.App;
import esinf.MainTest;
import esinf.model.Pais;
import esinf.model.store.PaisStore;

public class Exercicio3Test {

    private Exercicio3 ex3;

    @BeforeEach
    void setup(){
        MainTest.beforeEach();
        ex3 = new Exercicio3();

    }


    @Test
    void getPaisSortedTest (){
        ArrayList<Pais> pais = new ArrayList<>();
        PaisStore paisStore = App.getInstance().getPaisStore();
        pais.add(paisStore.getPais(166));//4 359 379
        pais.add(paisStore.getPais(168));//3 082 500
        pais.add(paisStore.getPais(7));//175000
        pais.add(paisStore.getPais(2));//15100
        pais.add(paisStore.getPais(4));//5 848
        
        
        Assertions.assertEquals(pais,ex3.getPaisSorted());

    }

    @Test
    void getMinPaisTest (){
        ArrayList<Pais> pais = ex3.getPaisSorted();
        int expected=3;
        long prodTotal=7616870;
        Assertions.assertEquals(expected,ex3.getMinPais(prodTotal, pais));

    }

    
    @Test
    void getMinPaisExceptionTest (){
        ArrayList<Pais> pais = ex3.getPaisSorted();
        int expected = -1;
        long prodTotal=76168701;
        Assertions.assertEquals(expected,ex3.getMinPais(prodTotal, pais));

    }
    
}
