package esinf.exercicios;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.IntPredicate;

import esinf.App;
import esinf.model.Pais;
import esinf.model.ProducaoAno;
import esinf.util.ListPrinter;
import esinf.util.Triplet;

/**
 * Exercicio2
 */
public class Exercicio2 implements Runnable {

    private App app;

    private Comparator<Triplet<Pais, Integer, Integer>> cmpA =
        Comparator.comparing(Triplet::getSecond);

    private Comparator<Triplet<Pais, Integer, Integer>> cmpB =
        Comparator.comparing(Triplet::getThird, (k1, k2) -> k2.compareTo(k1));

    public Exercicio2() {
        app = App.getInstance();
    }

    @Override
    public void run() throws NoSuchElementException
    {
        int id = 515;
        int quantidade = 14_000;

        List<Triplet<Pais, Integer, Integer>> filtered;
        filtered = filtrarPaises(id, q -> q >= quantidade);

        List<Pais> alineaA = sortA(filtered);
        List<Pais> alineaB = sortB(filtered);

        ListPrinter.print(alineaA, "Alinea a)", null);
        System.out.printf("\n--------------------\n");
        ListPrinter.print(alineaB, "Alinea b)", null);
    }

    public List<Triplet<Pais, Integer, Integer>>
    filtrarPaises(int frutoId, IntPredicate condicao)
    {
        if (!app.getFrutoStore().hasFruto(frutoId))
            throw new NoSuchElementException("erro: fruto nao existe");

        var triplets = new LinkedList<Triplet<Pais, Integer, Integer>>();
        Iterator<Pais> paisIter = app.getPaisStore().iterator();

        if (!paisIter.hasNext())
            throw new NoSuchElementException("erro: nao ha paises armazenados");

        paisIter.forEachRemaining(p -> {
            var iter = p.iterator();
            while (iter.hasNext()) {
                ProducaoAno ano = iter.next();

                var prodFruto = ano.getProducaoFruto(frutoId);
                int qtd;
                if (prodFruto != null
                && condicao.test((qtd = prodFruto.getQuantidadeProducao())))
                {
                    triplets.add(new Triplet<Pais,Integer,Integer>(p, ano.getAno(), qtd));
                    return; // move on to the next Pais
                }
            }
        });

        return triplets;
    }

    /* E type alias */
    private <E extends Triplet<Pais, Integer, Integer>> List<Pais>
    sortPais(List<E> list, Comparator<? super E> cmp)
    {
        return list.stream().sorted(cmp).map(Triplet::getFirst).toList();
    }

    public <E extends Triplet<Pais, Integer, Integer>> List<Pais>
    sortA(List<E> list)
    {
        return sortPais(list, cmpA);
    }

    public <E extends Triplet<Pais, Integer, Integer>> List<Pais>
    sortB(List<E> list)
    {
        return sortPais(list, cmpA.thenComparing(cmpB));
    }
}
