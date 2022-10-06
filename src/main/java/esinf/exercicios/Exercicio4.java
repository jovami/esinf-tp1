package esinf.exercicios;

import esinf.App;
import esinf.model.Fruto;
import esinf.model.Pais;
import esinf.model.ProducaoAno;
import esinf.util.ListPrinter;
import esinf.util.Pair;

import java.util.*;

/**
 * Exercicio4
 */
public class Exercicio4 implements Runnable {
    private App app;

    private Comparator<Pair<Pais, Integer>> comparator =
            (o1, o2) -> Integer.compare(o2.getSecond(), o1.getSecond());


    public Exercicio4() {
        app = App.getInstance();
    }

    @Override
    public void run() {

        /*app.getPaisStore().addPais(174, "Portugal");
        app.getPaisStore().addPais(234, "Espanha");
        app.getFrutoStore().addFruto(515, "Apples");
        app.getFrutoStore().addFruto(486, "Bananas");

         */
        int idFrutoEscolhido = 515;

/*
        Pais pais = app.getPaisStore().getPais(174);
        Pais pais1 = app.getPaisStore().getPais(234);
        Fruto frutoApples = app.getFrutoStore().getFruto(515);
        Fruto frutoBananas = app.getFrutoStore().getFruto(486);

        pais.addAnoProducao(pais.createAnoProducao(2000));
        pais1.addAnoProducao(pais1.createAnoProducao(2000));
        ProducaoAno producaoAno = pais.getProducaoAno(2000);
        ProducaoAno producaoAno6 = pais1.getProducaoAno(2000);

        pais.addAnoProducao(pais.createAnoProducao(2001));
        pais1.addAnoProducao(pais1.createAnoProducao(2001));
        ProducaoAno producaoAno1 = pais.getProducaoAno(2001);
        ProducaoAno producaoAno7 = pais1.getProducaoAno(2001);

        pais.addAnoProducao(pais.createAnoProducao(2002));
        pais1.addAnoProducao(pais1.createAnoProducao(2002));
        ProducaoAno producaoAno2 = pais.getProducaoAno(2002);
        ProducaoAno producaoAno8 = pais1.getProducaoAno(2002);

        pais.addAnoProducao(pais.createAnoProducao(2003));
        pais.addAnoProducao(pais.createAnoProducao(2004));
        pais.addAnoProducao(pais.createAnoProducao(2005));
        ProducaoAno producaoAno3 = pais.getProducaoAno(2003);
        ProducaoAno producaoAno4 = pais.getProducaoAno(2004);
        ProducaoAno producaoAno5 = pais.getProducaoAno(2005);

        producaoAno.addProducaoFruto(frutoApples, 12345);
        producaoAno1.addProducaoFruto(frutoApples, 12346);
        producaoAno2.addProducaoFruto(frutoApples, 12347);
        producaoAno3.addProducaoFruto(frutoApples, 12348);
        producaoAno4.addProducaoFruto(frutoApples, 123565);
        producaoAno5.addProducaoFruto(frutoApples, 1234);
        producaoAno6.addProducaoFruto(frutoApples, 12345);
        producaoAno7.addProducaoFruto(frutoApples, 12346);
        producaoAno8.addProducaoFruto(frutoApples, 12347);


        producaoAno.addProducaoFruto(frutoBananas, 30518);
        producaoAno1.addProducaoFruto(frutoBananas, 28304);
        producaoAno2.addProducaoFruto(frutoBananas, 29227);
        producaoAno3.addProducaoFruto(frutoBananas, 23417);
        producaoAno4.addProducaoFruto(frutoBananas, 2213);
        producaoAno5.addProducaoFruto(frutoBananas, 12133);


        System.out.println("CodePais(id) " + pais.getPaisCodigo() + " PaisNome " + pais.getNomePais());

        System.out.println("Frutonome=" + pais.getProducaoAno(2000).getProducaoFruto(515).getFruto().getNome() +
                " Ano=" + pais.getProducaoAno(2000).getAno() + " Quantidadeproducao=" + pais.getProducaoAno(2000).getProducaoFruto(515).getQuantidadeProducao());

        System.out.println("Frutonome=" + pais.getProducaoAno(2001).getProducaoFruto(515).getFruto().getNome() +
                " Ano=" + pais.getProducaoAno(2001).getAno() + " Quantidadeproducao=" + pais.getProducaoAno(2001).getProducaoFruto(515).getQuantidadeProducao());

        System.out.println("Frutonome=" + pais.getProducaoAno(2002).getProducaoFruto(515).getFruto().getNome() +
                " Ano=" + pais.getProducaoAno(2002).getAno() + " Quantidadeproducao=" + pais.getProducaoAno(2002).getProducaoFruto(515).getQuantidadeProducao());

        System.out.println("Frutonome=" + pais.getProducaoAno(2000).getProducaoFruto(486).getFruto().getNome() +
                " Ano=" + pais.getProducaoAno(2000).getAno() + " Quantidadeproducao=" + pais.getProducaoAno(2000).getProducaoFruto(486).getQuantidadeProducao());

        System.out.println("Frutonome=" + pais.getProducaoAno(2001).getProducaoFruto(486).getFruto().getNome() +
                " Ano=" + pais.getProducaoAno(2001).getAno() + " Quantidadeproducao=" + pais.getProducaoAno(2001).getProducaoFruto(486).getQuantidadeProducao());

        System.out.println("Frutonome=" + pais.getProducaoAno(2002).getProducaoFruto(486).getFruto().getNome() +
                " Ano=" + pais.getProducaoAno(2002).getAno() + " Quantidadeproducao=" + pais.getProducaoAno(2002).getProducaoFruto(486).getQuantidadeProducao());


 */

        Iterator<Pais> paisIter = app.getPaisStore().iterator();
        ArrayList<Pair<Pais, Integer>> listProduction = new ArrayList<>();

        while (paisIter.hasNext()) {
            Pais p = paisIter.next();

            Iterator<ProducaoAno> iter = p.iterator();
            int count = 0, maxCount = 0;
            if (iter.hasNext()) {
                int previous = iter.next().getProducaoFruto(idFrutoEscolhido).getQuantidadeProducao();

                while (iter.hasNext()) {
                    int current = iter.next().getProducaoFruto(idFrutoEscolhido).getQuantidadeProducao();
                    if (current > previous) {
                        previous = current;
                        count++;
                        if (maxCount < count)
                            maxCount = count;

                    }
                }
                listProduction.add(new Pair<Pais, Integer>(p, maxCount));
            }

            listProduction.sort(comparator);


        }
        String header = "\nPais --- Maior Crescimento\n";
        ListPrinter.print(listProduction, header, null);


    }
}