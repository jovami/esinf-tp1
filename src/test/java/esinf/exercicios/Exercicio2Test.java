package esinf.exercicios;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.IntPredicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import esinf.MainTest;
import esinf.MainTest.Frutos;
import esinf.MainTest.Paises;

/**
 * Exercicio2Test
 */
public class Exercicio2Test {

    Exercicio2 ex2;

    IntPredicate getPredicate(int minimumQtd) {
        return q -> q >= minimumQtd;
    }


    @BeforeEach
    void setup() {
        MainTest.beforeEach();
        ex2 = new Exercicio2();
    }

    @Test
    void testEmptyStore() {
        int id = Frutos.BANANAS.getCode();
        int qtd = 14_000;

        MainTest.resetSingleton();
        ex2 = new Exercicio2();

        assertThrows(RuntimeException.class, () -> {
            new Exercicio2().filtrarPaises(id, getPredicate(qtd));
        });
    }

    @Test
    void testEmptyListNoFruit() {
        int id = Frutos.INVALID_FRUIT.getCode();
        int qtd = 0;

        var list = ex2.filtrarPaises(id, getPredicate(qtd));

        assertTrue(list.isEmpty());
    }

    @Test
    void testEmptyListNoQtd() {
        int id = Frutos.APPLE.getCode();
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

        assertTrue(triplet.getFirst().getPaisCodigo() == paisCodigo
                && triplet.getSecond() == ano
                && triplet.getThird() == qtd
        );
    }

    /* LinkedList<Triplet<Integer, Integer, Integer>> expected = new LinkedList<>();
    expected.add(new Triplet<>(Paises.PAPUA_NEW_GUINEA.getCode(), 1961, 31_000));
    expected.add(new Triplet<>(Paises.ALGERIA.getCode(), 2001, ));
    expected.add(new Triplet<>(Paises.PAPUA_NEW_GUINEA.getCode(), 1961, 31_000)); */

    @Test
    void testSortAnoOnly() {
        int id = Frutos.BANANAS.getCode();

        final int qtd = 1500;

        var list = ex2.filtrarPaises(id, getPredicate(qtd));
        var sorted = ex2.sortA(list)
                        .stream()
                        .mapToInt(p -> p.getPaisCodigo())
                        .toArray();

        assertTrue(sorted.length == 4);

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
                        .mapToInt(p -> p.getPaisCodigo())
                        .toArray();

        assertTrue(sorted.length == 4);

        final int[] expected = new int[] {
            Paises.PAPUA_NEW_GUINEA.getCode(),
            Paises.ANGOLA.getCode(),
            Paises.ALGERIA.getCode(),
            Paises.PANAMA.getCode(),
        };

        assertArrayEquals(expected, sorted);
    }
}
