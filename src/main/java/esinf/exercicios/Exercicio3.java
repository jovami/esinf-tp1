package esinf.exercicios;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import esinf.App;
import esinf.model.Fruto;
import esinf.model.Pais;
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
        (t1, t2) -> Integer.compare(t2.getProdTotal(), t1.getProdTotal());

    public Exercicio3() {
        app = App.getInstance();
        
        paisStore = app.getPaisStore();
        frutoStore = app.getFrutoStore();
    }

    @Override
    public void run() {

        //como serao apresentadas os paises
        instancias();
        
        //TODO: como receber este valor
        int producaoMax=901;
        int sumTotal=0;

        //Stream
        Stream<Pais>  paisStream = paisStore.getStream();
        List<Pais> listPais = paisStream.sorted(cmpPais).toList();//list ordenada por ordem decrescente

        //    System.out.println(listPais.toString()); //list ordenada


        System.out.printf("Pais por ordem decrescente:\n");


        for (Pais pais : listPais) {
            sumTotal += pais.getProdTotal();
            System.out.printf("Pais:%s     Prod:%d\n",pais.getNomePais(),pais.getProdTotal());

            if (sumTotal >= producaoMax){
                //save this where??
                break;
            }
        }


    }

    //INSTANCIAS PARA TESTES
    private void instancias() { 
        //PESSEGO
        final int FRUTOID=203;
        final String FRUTONOME="pessego";        
        frutoStore.addFruto(FRUTOID,FRUTONOME);
        Fruto pessego=frutoStore.getFruto(FRUTOID);


        //pera
        final int PERAID=203;
        final String PERANOME="pessego";        
        frutoStore.addFruto(PERAID,PERANOME);
        Fruto pera=frutoStore.getFruto(PERAID);
        


        final int ANOPROD=2003;
        final int ANOPROD2=2003;
        //   Portugal
        final int PORTUGALID=123;
        final int PORTUGALPESSEGOPROD=200;
        final String PORTUGAL="portugal";

        paisStore.addPais(PORTUGALID, PORTUGAL);
        Pais portugal = paisStore.getPais(PORTUGALID);
        portugal.addAnoProducao(portugal.createAnoProducao(ANOPROD));
        portugal.addProducaoFruto(ANOPROD, pessego, PORTUGALPESSEGOPROD);

        //ESPANHA
        final int ESPANHAID=1245;
        final int ESPANHAPESSEGOPROD=111;
        final String ESPANHA="Espanha";
        
        paisStore.addPais(ESPANHAID, ESPANHA);
        Pais espanha = paisStore.getPais(ESPANHAID);
        espanha.addAnoProducao(espanha.createAnoProducao(ANOPROD));
        espanha.addProducaoFruto(ANOPROD,pessego,ESPANHAPESSEGOPROD);

        //GRECIA
        final int GRECIAID=1200;
        final int GRECIAPESSEGOPROD=300;
        final String GRECIA="Grecia";
        
        paisStore.addPais(GRECIAID, GRECIA);
        Pais grecia = paisStore.getPais(GRECIAID);
        grecia.addAnoProducao(grecia.createAnoProducao(ANOPROD));
        grecia.addProducaoFruto(ANOPROD,pessego,GRECIAPESSEGOPROD);

        
        //ITALIA
        final int ITALIAID=120;
        final int ITALIAPESSEGOPROD=200;
        final int ITALIAPERAPROD=200;
        final String ITALIA="Italia";
        
        paisStore.addPais(ITALIAID, ITALIA);
        Pais italia = paisStore.getPais(ITALIAID);
        italia.addAnoProducao(italia.createAnoProducao(ANOPROD));
        italia.addProducaoFruto(ANOPROD,pessego,ITALIAPESSEGOPROD);
        italia.addAnoProducao(italia.createAnoProducao(ANOPROD2));
        italia.addProducaoFruto(ANOPROD2,pera,ITALIAPERAPROD);


    }

    // Exercicio aqui
}
