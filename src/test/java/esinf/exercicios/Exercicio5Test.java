package esinf.exercicios;

import esinf.App;
import esinf.MainTest;
import esinf.model.Fruto;
import esinf.model.ProducaoAno;
import esinf.model.store.PaisStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class Exercicio5Test {

    Exercicio5 ex5;

    @BeforeEach
    void setup() {
        MainTest.beforeEach();
        ex5 = new Exercicio5();
    }


    @Test
    void getEstruturaPedida() {

        int idPais = MainTest.Paises.PANAMA.getCode();
        String result = ex5.getEstruturaPedida(idPais);

        int yearMin = 2020;
        int yearMax = 2008;
        int diffQuantities = 171334;

        String expected = "["+yearMin+"/"
                +yearMax+ ","
                + "Bananas" + ","
                + diffQuantities + "]";

        assertEquals(expected,result);

    }

    @Test
    void getEstruturaPedidaMenor() {

        //Adicionado um fruto nao contido na lista e com uma menor diferenca
        //entre o minimo e a maximo de quantidade de producao face ao fruto
        //contido na lista "Bananas"

        PaisStore p= App.getInstance().getPaisStore();
        App.getInstance().getFrutoStore().addFruto(515,"Apples");

        ProducaoAno producaoAno = p.getPais(166).getProducaoAno(2012);
        ProducaoAno producaoAnox = p.getPais(166).getProducaoAno(2020);

        Fruto fruto = App.getInstance().getFrutoStore().getFruto(515);

        producaoAno.addProducaoFruto(fruto, 10);
        producaoAnox.addProducaoFruto(fruto, 1);


        int yearMin = 2020;
        int yearMax = 2008;
        int diffQuantities = 171334;

        String expected = "["+yearMin+"/"
                +yearMax+ ","
                + "Bananas" + ","
                + diffQuantities + "]";

        int idPais = MainTest.Paises.PANAMA.getCode();
        String result = ex5.getEstruturaPedida(idPais);

        assertEquals(expected,result);

    }

    @Test
    void getEstruturaPedidaMaior() {

        //Adicionado um fruto nao contido na lista e com uma maior diferenca
        //entre o minimo e a maximo de quantidade de producao face ao fruto
        //contido na lista "Bananas"

        PaisStore p= App.getInstance().getPaisStore();
        App.getInstance().getFrutoStore().addFruto(515,"Apples");

        ProducaoAno producaoAno = p.getPais(166).getProducaoAno(2012);
        ProducaoAno producaoAnox = p.getPais(166).getProducaoAno(2020);

        Fruto fruto = App.getInstance().getFrutoStore().getFruto(515);

        producaoAno.addProducaoFruto(fruto, 10000000);
        producaoAnox.addProducaoFruto(fruto, 1);


        int yearMin = 2012;
        int yearMax = 2020;
        int diffQuantities = 9999999;

        String expected = "["+yearMax+"/"
                +yearMin+ ","
                + "Apples" + ","
                + diffQuantities + "]";

        int idPais = MainTest.Paises.PANAMA.getCode();
        String result = ex5.getEstruturaPedida(idPais);

        assertEquals(expected,result);

    }
}