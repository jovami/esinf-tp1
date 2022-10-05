package esinf.exercicios;

import esinf.App;
import esinf.model.Fruto;
import esinf.model.Pais;
import esinf.model.ProducaoAno;
import esinf.model.ProducaoFrutoPorPaisPorAno;
import esinf.model.store.PaisStore;
import esinf.util.ListPrinter;
import esinf.util.Triplet;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Exercicio5
 */
public class Exercicio5 implements Runnable {

    private App app;


    //compare a producao para um pais e um determinado fruto num ano com o ano seguinte ,exemplo:
    //         2000 Apples 229794
    //         2001 Apples 258363

    //comparator para os nomes dos frutos
    private Comparator<Triplet<Integer, String, Integer>> compString =
            (t1 , t2) -> String.CASE_INSENSITIVE_ORDER.compare(t1.getSecond(), t2.getSecond());

    //comparator para as quantidades de producao
    private Comparator<Triplet<Integer, String, Integer>> compInteger =
            (t1 , t2) -> Integer.compare(t1.getThird(), t2.getThird());


    public Exercicio5() {
        app = App.getInstance();
    }



    @Override
    public void run() {

        // Colocar aqui codigo que queiram correr
        app.getPaisStore().addPais(174,"Portugal");
        app.getPaisStore().addPais(203,"Espanha");


        //Fruto appl = new Fruto("Apples",515);
        //Fruto banans = new Fruto("Bananas",486);


        //Imprimir a lista pedida
        //ListPrinter.print(getEstruturaPedida("Portugal"), null, null);

    }

    // Exercicio aqui


    private Pais getPaisAnalisar(String nomePais)
    {
        //percorrer todos os paises e escolher o pedido
        for (Iterator<Pais> paisIter = app.getPaisStore().getIteradorPais(); paisIter.hasNext(); )
        {
            if (!paisIter.hasNext())
                throw new RuntimeException("erro: nao ha paises armazenados");

            Pais p = paisIter.next();

            //se o pais passado como parametro estiver na lista de paises continuar dentro do if
            if(p.getNomePais().compareToIgnoreCase(nomePais) == 0)
                return p;
        }

        return null;
    }


    public List<Triplet<Integer, String, Integer>> getListDeTodosOsFrutosProdQuantidade(String nomePais)
    {
        // [ano, Fruto , quantidade de producao]
        var estrutura = new LinkedList<Triplet<Integer, String, Integer>>();
        Pais pais = getPaisAnalisar(nomePais);


        for (Iterator<ProducaoAno> producaoAnualIter = pais.getIteradorAnos(); producaoAnualIter.hasNext(); )
        {
            if (!producaoAnualIter.hasNext())
                throw new RuntimeException("erro: nao ha producao");

            ProducaoAno prodAnual = producaoAnualIter.next();

            //Iterator<ProducaoFrutoPorPaisPorAno>
            for(Iterator<ProducaoFrutoPorPaisPorAno> producaoFrutoPorPaisPorAnoIter =  prodAnual.getIterador(); producaoFrutoPorPaisPorAnoIter.hasNext(); )
            {
                if (!producaoFrutoPorPaisPorAnoIter.hasNext())
                    throw new RuntimeException("erro: nao ha producao para aquele fruto");

                ProducaoFrutoPorPaisPorAno prodFrutoPorPaisPorAno = producaoFrutoPorPaisPorAnoIter.next();

                //agora fazer uma lista do genero
                // 2000 Apples 229794
                // 2001 Bananas 234512
                // 2001 Apples 258363
                // 2015 Bananas 123456

               // estrutura.add(new Triplet<Integer, Fruto, Integer>(prodAnual.getAno(), prodFrutoPorPaisPorAno.getFruto().getNome(), prodFrutoPorPaisPorAno.getQuantidadeProducao()));
            }

        }

       return estrutura;
    }

    public List<Triplet<String, String, Integer>> getEstruturaPedida(String nomePais)
    {
        // [anox/anoy , nomefruto , diferenca entre o maior e menor valor de producao]
        var result = new LinkedList<Triplet<String, String, Integer>>();

        // [ano, Fruto , quantidade de producao]
        var estrutura = getListDeTodosOsFrutosProdQuantidade(nomePais);


        //ordenar a lista de frutos pelo nome seguido da quantidade de producao
        sortFrutosProducao(getListDeTodosOsFrutosProdQuantidade(nomePais),compInteger);

        // [anox/anoy , nomefruto , diferenca entre o maior e menor valor de producao]

        return result;
    }




    public <E extends Triplet<Integer, String, Integer>> List<Triplet<String, String, Integer>> sortFrutosProducao(List<E> list, Comparator<E> cmp)
    {
        var result = new LinkedList<Triplet<String, String, Integer>>();

        list.sort(cmp);
        //list.forEach(e -> result.add(e.getThird()));

        return result;
    }

}
