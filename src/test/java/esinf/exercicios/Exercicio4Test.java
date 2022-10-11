package esinf.exercicios;

import esinf.MainTest;
import esinf.model.Pais;
import esinf.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Exercicio4Test {

    ArrayList<Pair<Pais, Integer>> expected = new ArrayList<>();

    Exercicio4 exercicio4 = new Exercicio4();


    @BeforeEach
    void setup() {
        MainTest.beforeEach();

    }


    @Test
    void getListMaxProductionTest() {
        int idFrutoEscolhido = 486;
        Exercicio4 exercicio4 = new Exercicio4();
        Pais p = new Pais("Papua New Guinea", 168);
        Pais p1 = new Pais("Panama", 166);
        Pais p2 = new Pais("Algeria", 4);


        expected.add(new Pair<>(p, 4));
        expected.add(new Pair<>(p1, 3));
        expected.add(new Pair<>(p2, 1));

        //assertEquals(expected, exercicio4.getListMaxProduction(idFrutoEscolhido));
        var actual = exercicio4.getListMaxProduction(idFrutoEscolhido);
        for (int i = 0; i <actual.size(); i++) {
            assertEquals(actual.get(i).getFirst(),expected.get(i).getFirst());
            assertEquals(actual.get(i).getSecond(),expected.get(i).getSecond());

        }

    }



}
