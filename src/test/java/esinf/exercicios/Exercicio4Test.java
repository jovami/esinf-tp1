package esinf.exercicios;

import esinf.App;
import esinf.MainTest;
import esinf.model.Pais;
import esinf.util.ListPrinter;
import esinf.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Exercicio4Test {

    ArrayList<Pair<Pais, Integer>> expected = new ArrayList<>();
    Exercicio4 exercicio4 = new Exercicio4();



    @BeforeEach
    void setup(){
        MainTest.beforeEach();

    }



    @Test
    void getListMaxProductionTest(){
        int idFrutoEscolhido = 486;
        Exercicio4 exercicio4 = new Exercicio4();
        Pais p =new Pais("Papua New Guinea",168);
        Pais p1 =new Pais("Panama",166);
        expected.add(new Pair<>(p, 4));
        expected.add(new Pair<>(p1, 3));
        //assertEquals(expected, exercicio4.getListMaxProduction(idFrutoEscolhido));
        assertEquals(expected, exercicio4.getListMaxProduction(idFrutoEscolhido));
    }

    @Test
    void printListProduction(){
        int idFrutoEscolhido= 486;
        String header = "\nPais --- Maior Crescimento\n";
        //assertEquals(ListPrinter.print(expected, header, null), ListPrinter.print(exercicio4.getListMaxProduction(idFrutoEscolhido), header, null));



    }



}
