package esinf.exercicios;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntPredicate;

import esinf.App;
import esinf.model.Fruto;
import esinf.model.Pais;
import esinf.util.ListPrinter;
import esinf.util.Triplet;

/**
 * Exercicio2
 */
public class Exercicio2 implements Runnable {

    private App app;

    private Comparator<Triplet<Pais, Integer, Integer>> cmpA =
        (t1, t2) -> Integer.compare(t1.getSecond(), t2.getSecond());

    private Comparator<Triplet<Pais, Integer, Integer>> cmpB =
        (t1, t2) -> Integer.compare(t2.getThird(), t1.getThird());

    public Exercicio2() {
        app = App.getInstance();
    }

    @Override
    public void run() throws RuntimeException
    {
        int id = 12;
        int quantidade = 200;

        List<Triplet<Pais, Integer, Integer>> filtered;
        filtered = filtrarPaises(id, q -> q >= quantidade);

        List<Pais> alineaA = sortPais(filtered, cmpA);

        // filtered modificado
        List<Pais> alineaB = sortPais(filtered, cmpB);

        ListPrinter.print(alineaA, "Alinea a)", null);
        System.out.printf("\n--------------------\n");
        ListPrinter.print(alineaB, "Alinea b)", null);
    }

    public List<Triplet<Pais, Integer, Integer>>
        filtrarPaises(int frutoId, IntPredicate condicao)
    {
        var triplets = new LinkedList<Triplet<Pais, Integer, Integer>>();
        Iterator<Pais> paisIter = app.getPaisStore().iterator();

        if (!paisIter.hasNext())
            throw new RuntimeException("erro: nao ha paises armazenados");

        paisIter.forEachRemaining(p -> {
            var iter = p.getIteradorAnos();
            while (iter.hasNext()) {
                var ano = iter.next();
                int qtd = 0;
                if (condicao.test(qtd)) {
                    triplets.add(new Triplet<Pais,Integer,Integer>(p, ano.getAno(), qtd));
                    return;
                }
                /* TODO: mudar para este */
                // var ano = iter.next();
                // int qtd;
                // Fruto f = ano.getFruto(frutoId);
                // if (f != null && condicao.test((qtd = f.getProducao()))) {
                //     triplets.add(new Triplet<Pais,Integer,Integer>(p, ano.getAno(), qtd));
                //     return;
                // }
            }
        });

        return triplets;
    }

    /* E type alias */
    public <E extends Triplet<Pais, Integer, Integer>> List<Pais>
        sortPais(List<E> list, Comparator<E> cmp)
    {
        var result = new LinkedList<Pais>();

        list.sort(cmp);
        list.forEach(e -> result.add(e.getFirst()));
        return result;
    }
}
