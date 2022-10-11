package esinf.exercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import esinf.App;
import esinf.model.Fruto;
import esinf.model.Pais;
import esinf.model.ProducaoAno;
import esinf.model.store.FrutoStore;
import esinf.model.store.PaisStore;

/**
 * Exercicio3
 */
public class Exercicio3 implements Runnable {

    private App app;

    private PaisStore paisStore;
    private FrutoStore frutoStore;

    private Comparator<Pais> cmpPais =
        (p1, p2) -> Integer.compare(p2.getProdTotal(), p1.getProdTotal());

    public Exercicio3() {
        app = App.getInstance();

        paisStore = app.getPaisStore();
        frutoStore = app.getFrutoStore();
    }

    @Override
    public void run() {

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c28d4217f32526e004cea1f26467393f20965fce
        Long producaoMax = 1299298911l;

        ArrayList<Pais> listPais = getPaisSorted();
        
        int numMinPais=getMinPais(producaoMax, getPaisSorted());
        
        if(numMinPais!=-1)
            printPais(numMinPais, listPais, producaoMax);
        else
            System.out.printf("Os paises na base de dados não possuem uma producao suficiente para ultrapassar a producao desejada %d",producaoMax);
        
    }
<<<<<<< HEAD
=======
        //como serao apresentadas os paises
        instancias();

        //TODO: como receber este valor
        int producaoMax=860;
        int sumTotal=0;
>>>>>>> dd4f61f ((feature/exercicio3)updated ex3 de acordo com as novas alterações nas incrementacoes das producoes)
=======
>>>>>>> c28d4217f32526e004cea1f26467393f20965fce

    public ArrayList<Pais> getPaisSorted(){
        Stream<Pais>  paisStream = paisStore.getStream();
        return paisStream.sorted(cmpPais).collect(Collectors.toCollection(ArrayList::new));
    }
<<<<<<< HEAD

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

<<<<<<< HEAD
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
=======
>>>>>>> c28d4217f32526e004cea1f26467393f20965fce

    public int getMinPais(long producaoMax,ArrayList<Pais> iterPais){

<<<<<<< HEAD

=======
        //System.out.println(listPais.toString()); //list ordenada

        System.out.printf("Paises necessários para atingir um valor de producao total de %d :\n",producaoMax);
        for (Pais pais : listPais) {
=======
        int numPais=0;
        int sumTotal=0;
        
        for (Pais pais : iterPais) {
>>>>>>> c28d4217f32526e004cea1f26467393f20965fce
            sumTotal += pais.getProdTotal();
            numPais++;
            if (sumTotal >= producaoMax){
<<<<<<< HEAD
                System.out.printf("Prod total: %d\n",sumTotal);
                break;
            }
        }
=======
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
>>>>>>> c28d4217f32526e004cea1f26467393f20965fce
    }
>>>>>>> dd4f61f ((feature/exercicio3)updated ex3 de acordo com as novas alterações nas incrementacoes das producoes)


    //INSTANCIAS PARA TESTES
    private void instancias() {
        //PESSEGO
        final int FRUTOID=203;
        final String FRUTONOME="pessego";
        frutoStore.addFruto(FRUTOID,FRUTONOME);
        Fruto pessego=frutoStore.getFruto(FRUTOID);


        //pera
        final int PERAID=233;
        final String PERANOME="pessego";
        frutoStore.addFruto(PERAID,PERANOME);
        Fruto pera=frutoStore.getFruto(PERAID);



        final int ANOPROD=2003;
        final int ANOPROD2=2005;

        //   Portugal
        final int PORTUGALID=123;
        final int PORTUGALPESSEGOPROD=200;
        final String PORTUGAL="portugal";

        paisStore.addPais(PORTUGALID, PORTUGAL);
        Pais portugal = paisStore.getPais(PORTUGALID);
        ProducaoAno prodPT = portugal.createAnoProducao(ANOPROD);
        prodPT.addProducaoFruto(pessego, PORTUGALPESSEGOPROD);
        portugal.addAnoProducao(prodPT);

        //ESPANHA
        final int ESPANHAID=1245;
        final int ESPANHAPESSEGOPROD=111;
        final String ESPANHA="Espanha";

        paisStore.addPais(ESPANHAID, ESPANHA);
        Pais espanha = paisStore.getPais(ESPANHAID);
        ProducaoAno prodES = espanha.createAnoProducao(ANOPROD);
        prodES.addProducaoFruto(pessego, ESPANHAPESSEGOPROD);
        espanha.addAnoProducao(prodES);

        //GRECIA
        final int GRECIAID=1200;
        final int GRECIAPESSEGOPROD=300;
        final String GRECIA="Grecia";

        paisStore.addPais(GRECIAID, GRECIA);
        Pais grecia = paisStore.getPais(GRECIAID);
        ProducaoAno prodGR = grecia.createAnoProducao(ANOPROD);
        prodGR.addProducaoFruto(pessego, GRECIAPESSEGOPROD);
        grecia.addAnoProducao(prodGR);


        //ITALIA
        final int ITALIAID=120;
        final int ITALIAPESSEGOPROD=200;
        final int ITALIAPERAPROD=200;
        final String ITALIA="Italia";

        paisStore.addPais(ITALIAID, ITALIA);
        Pais italia = paisStore.getPais(ITALIAID);

        ProducaoAno prodIT1 = italia.createAnoProducao(ANOPROD);
        prodIT1.addProducaoFruto(pessego, ITALIAPESSEGOPROD);
        italia.addAnoProducao(prodIT1);

        ProducaoAno prodIT2 = italia.createAnoProducao(ANOPROD2);
        prodIT2.addProducaoFruto(pera, ITALIAPERAPROD);
        italia.addAnoProducao(prodIT2);
    }

}
