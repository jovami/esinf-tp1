package esinf.exercicios;

import java.util.Iterator;
import java.util.LinkedList;

import esinf.App;
import esinf.model.Pais;

/**
 * Exercicio2
 */
public class Exercicio2 implements Runnable {

    private App app;

    public Exercicio2() {
        app = App.getInstance();
    }

    @Override
    public void run() {
    }

    public void aaaa(int frutoId, int quantidade) {
        var l = new LinkedList<Pais>();
        Iterator<Pais> paisIter = app.getPaisStore().getIteradorPais();

        paisIter.forEachRemaining((Pais p) -> {
            var iter = p.getIteradorAnos();
            while (iter.hasNext()) {
                var ano = iter.next();
                if (true /*ano.getFruto(frutoId).getProducao() >= quantidade*/) {
                    l.add(p);
                    return;
                }
            }
        });
    }


    // Exercicio aqui
}
