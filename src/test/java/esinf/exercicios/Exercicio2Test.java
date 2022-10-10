package esinf.exercicios;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.function.IntPredicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import esinf.App;
import esinf.MainTest;
import esinf.MainTest.Frutos;
import esinf.MainTest.Paises;
import esinf.model.Pais;

/**
 * Exercicio2Test
 */
public class Exercicio2Test {

    private Exercicio2 ex2;

    private IntPredicate getPredicate(int minimumQtd) {
        return q -> q >= minimumQtd;
    }


    @BeforeEach
    void setup() {
        MainTest.beforeEach();
        ex2 = new Exercicio2();
    }

    @Test
    void testNoSuchFruit() {
        int id = Frutos.INVALID_FRUIT.getCode();
        int qtd = 0; // any quantity

        assertThrows(NoSuchElementException.class, () -> {
            ex2.filtrarPaises(id, getPredicate(qtd));
        });
    }

    @Test
    void testEmptyStore() {
        int id = Frutos.BANANAS.getCode();
        int qtd = 0; // any

        MainTest.resetSingleton();
        App.getInstance().getFrutoStore().addFruto(id, "banana");
        assertThrows(NoSuchElementException.class, () -> {
            new Exercicio2().filtrarPaises(id, getPredicate(qtd));
        });
    }

    @Test
    void testEmptyList() {
        int id = Frutos.BANANAS.getCode();
        int qtd = Integer.MAX_VALUE;

        var list = ex2.filtrarPaises(id, getPredicate(qtd));

        assertTrue(list.isEmpty());
    }

    @Test
    void testBananaMax() {
        int id = Frutos.BANANAS.getCode();

        final int qtd = 456_522;
        final int paisCodigo = Paises.PANAMA.getCode();
        final int ano = 2008;

        var list = ex2.filtrarPaises(id, getPredicate(qtd));

        assertTrue(list.size() == 1);

        var triplet = list.get(0);

        assertTrue(
            triplet.getFirst().getPaisCodigo() == paisCodigo
            && triplet.getSecond() == ano
            && triplet.getThird() == qtd
        );
    }

    @Test
    void testSortAnoOnly() {
        int id = Frutos.BANANAS.getCode();

        final int qtd = 1500;

        var list = ex2.filtrarPaises(id, getPredicate(qtd));
        var sorted = ex2.sortA(list)
                        .stream()
                        .mapToInt(Pais::getPaisCodigo)
                        .toArray();

        final int[] expected = new int[] {
            Paises.ANGOLA.getCode(),
            Paises.PAPUA_NEW_GUINEA.getCode(),
            Paises.ALGERIA.getCode(),
            Paises.PANAMA.getCode(),
        };

        assertArrayEquals(expected, sorted);
    }

    @Test
    void testSortAnoQtd() {
        int id = Frutos.BANANAS.getCode();

        final int qtd = 1500;

        var list = ex2.filtrarPaises(id, getPredicate(qtd));
        var sorted = ex2.sortB(list)
                        .stream()
                        .mapToInt(Pais::getPaisCodigo)
                        .toArray();

        final int[] expected = new int[] {
            Paises.PAPUA_NEW_GUINEA.getCode(),
            Paises.ANGOLA.getCode(),
            Paises.ALGERIA.getCode(),
            Paises.PANAMA.getCode(),
        };

        assertArrayEquals(expected, sorted);
    }

    @Test
    void coverage99Percent() {
        // suppress stdout
        var stdout = new java.io.PrintStream(System.out);
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int x) throws java.io.IOException {}
        }));
        ex2.run();
        System.setOut(stdout);
    }
}
