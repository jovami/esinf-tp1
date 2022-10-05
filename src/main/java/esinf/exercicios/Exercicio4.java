package esinf.exercicios;

import esinf.App;
import esinf.model.Fruto;
import esinf.model.Pais;
import esinf.model.ProducaoAno;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Exercicio4
 */
public class Exercicio4 implements Runnable {
    Scanner in = new Scanner(System.in);

    private App app;

    private Fruto oFruto;
    private Pais oPais;

    public Exercicio4() {
        app = App.getInstance();
    }

    @Override
    public void run() {
        System.out.println("Fruto para ver o crescimento");
        String fruto = in.nextLine();
        int id = oFruto.getId();

        Iterator<Pais> paisIter = app.getPaisStore().getIteradorPais();

 /*       while (paisIter.hasNext()) {
            Pais p = paisIter.next();
            Iterator<ProducaoAno> iter = p.getIteradorAnos();
            while (iter.hasNext()){
                ProducaoAno pr = iter.next();
                pr.getFruto(codigo).getProducao();
            }
        }

  */



    /*    int id = 1015;


        getPaisesComFrutoF(id);
        List<Pais, Integer> filter;
        filter =
        if (fruto)
            while ()
                oPais.getIteradorAnos(i);


     */

    }

    private void getMaiorCrescimentoConsecutivo() {

        Iterator<Pais> paisIter = app.getPaisStore().getIteradorPais();
        while (paisIter.hasNext()) {
            Pais p = paisIter.next();
            Iterator<ProducaoAno> iter = p.getIteradorAnos();

        }


    }

    private void getPaisesComFrutoF(int id) {


    }


}
