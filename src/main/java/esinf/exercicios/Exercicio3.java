package esinf.exercicios;

import java.util.Comparator;
import java.util.List;
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
        (t1, t2) -> Integer.compare(t2.getProdTotal(), t1.getProdTotal());

    public Exercicio3() {
        app = App.getInstance();
        
        paisStore = app.getPaisStore();
        frutoStore = app.getFrutoStore();
    }

    @Override
    public void run() {

        instancias();
        //Stream

        Stream<Pais>  paisStream = paisStore.getStream();
        List<Pais> listPais = paisStream.sorted(cmpPais).toList();

        System.out.printf("Pais por ordem crescente:\n");
        for (Pais pais : listPais) {
            System.out.printf("Pais:    %s  | ProdTotal:    %d \n",pais.getNomePais(),pais.getProdTotal());
        }


        // ^
        /*
            for(each pais ){
                for(each ano){           calcular a produção anual

                    for(each fruto){         sumar a produção de cada fruto


                        Pais.producao+=fruto.getProduçao()


                    }
                }
            }

            armazenar por ordem decrescente
            criar uma nova estrutura de dados para armazenar a ordem??

        */
    }

    private void instancias() {
        //PESSEGO
        final int FRUTOID=203;
        final String FRUTONOME="pessego";        
        frutoStore.addFruto(FRUTOID,FRUTONOME);
        Fruto pessego=frutoStore.getFruto(FRUTOID);
        
        //   Portugal
        final int ANOPROD=2003;
        final int PORTUGALID=123;
        final int PORTUGALPESSEGOPROD=200;
        final String PORTUGAL="portugal";

        paisStore.addPais(PORTUGALID, PORTUGAL);
        Pais portugal = paisStore.getPais(PORTUGALID);
        portugal.addAnoProducao(portugal.createAnoProducao(ANOPROD));
        portugal.addProducaoFruto(ANOPROD, pessego, PORTUGALPESSEGOPROD);

        //ESPANHA
        final int ESPANHAID=1245;
        final int ESPANHAPESSEGOPROD=10;
        final String ESPANHA="Espanha";
        
        paisStore.addPais(ESPANHAID, ESPANHA);
        Pais espanha = paisStore.getPais(ESPANHAID);
        espanha.addAnoProducao(espanha.createAnoProducao(ANOPROD));
        espanha.addProducaoFruto(ANOPROD,pessego,ESPANHAPESSEGOPROD);

    }

    // Exercicio aqui
}
