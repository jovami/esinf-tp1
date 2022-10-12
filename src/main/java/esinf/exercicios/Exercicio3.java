package esinf.exercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import esinf.App;
import esinf.model.Pais;
import esinf.model.store.PaisStore;

/**
 * Exercicio3
 */
public class Exercicio3 implements Runnable {

    private final App app;

    private final PaisStore paisStore;

    private final Comparator<Pais> cmpPais =
        (p1, p2) -> Integer.compare(p2.getProdTotal(), p1.getProdTotal());

    public Exercicio3() {
        app = App.getInstance();
        paisStore = app.getPaisStore();
    }

    @Override
    public void run() {

        long producaoMax = 1299298911L;

        ArrayList<Pais> listPais = getPaisSorted();
        
        int numMinPais=getMinPais(producaoMax, getPaisSorted());
        
        if(numMinPais!=-1)
            printPais(numMinPais, listPais, producaoMax);
        else
            System.out.printf("Os paises na base de dados não possuem uma producao suficiente para ultrapassar a producao desejada %d",producaoMax);
        
    }

    public ArrayList<Pais> getPaisSorted(){
        Stream<Pais>  paisStream = paisStore.getStream();
        return paisStream.sorted(cmpPais).collect(Collectors.toCollection(ArrayList::new));
    }

    public int getMinPais(long producaoMax,ArrayList<Pais> iterPais){
        int numPais=0;
        int sumTotal=0;
        
        for (Pais pais : iterPais) {
            sumTotal += pais.getProdTotal();
            numPais++;
            if (sumTotal >= producaoMax){
                return numPais;
            }
        }
        return -1;
    }

    public void printPais(int numPais, List<Pais> listPais, long producaoMax) {
        int sumTotal=0;

        System.out.printf("Paises necessários para atingir um valor de producao total de %d :\n\n",producaoMax);

        for (int i = 0 ;i<numPais;i++) {
            Pais pais=listPais.get(i);
            sumTotal += pais.getProdTotal();

            System.out.printf("Pais:%s     Prod:%d\n",pais.getNomePais(),pais.getProdTotal());
        }
        System.out.printf("Prod total : %d\t Numero de paises:%d\n",sumTotal,numPais);
    }
}
