package esinf.exercicios;

import esinf.App;
import esinf.model.Fruto;
import esinf.model.Pais;
import esinf.model.ProducaoAno;
import esinf.model.ProducaoFrutoPorPaisPorAno;
import esinf.util.ListPrinter;
import esinf.util.Triplet;
import java.util.*;


/**
 * Exercicio5
 */
public class Exercicio5 implements Runnable {

    private App app;

    //comparator para os nomes dos frutos
    private Comparator<Triplet<Integer, Integer, Integer>> cmpIDFruto =
            (t1 , t2) -> Integer.compare(t1.getSecond(), t2.getSecond());

    //comparator para as quantidades de producao
    private Comparator<Triplet<Integer, Integer, Integer>> cmpQuantProducao =
            (t1 , t2) -> Integer.compare(t1.getThird(), t2.getThird());


    public Exercicio5() {
        app = App.getInstance();
    }



    @Override
    public void run() {

        // Colocar aqui codigo que queiram correr
       /* app.getPaisStore().addPais(174,"Portugal");
        app.getFrutoStore().addFruto(515,"Apples");
        app.getFrutoStore().addFruto(486,"Bananas");


        Pais pais = app.getPaisStore().getPais(174);
        Fruto frutoApples = app.getFrutoStore().getFruto(515);
        Fruto frutoBananas = app.getFrutoStore().getFruto(486);

        pais.addAnoProducao(pais.createAnoProducao(2000));
        ProducaoAno producaoAno = pais.getProducaoAno(2000);

        pais.addAnoProducao(pais.createAnoProducao(2001));
        ProducaoAno producaoAno1 = pais.getProducaoAno(2001);

        pais.addAnoProducao(pais.createAnoProducao(2002));
        ProducaoAno producaoAno2 = pais.getProducaoAno(2002);

        producaoAno.addProducaoFruto(frutoApples,229794);
        producaoAno.addProducaoFruto(frutoBananas,30518);

        producaoAno1.addProducaoFruto(frutoApples,258363);
        producaoAno1.addProducaoFruto(frutoBananas,28304);

        producaoAno2.addProducaoFruto(frutoApples,300225);
        producaoAno2.addProducaoFruto(frutoBananas,29227);
*/

        /*
        System.out.println("CodePais(id) "+pais.getPaisCodigo()+ " PaisNome "+pais.getNomePais());

        System.out.println("Frutonome=" +pais.getProducaoAno(2000).getProducaoFruto(515).getFruto().getNome()+
                " Ano=" + pais.getProducaoAno(2000).getAno()+" Quantidadeproducao=" + pais.getProducaoAno(2000).getProducaoFruto(515).getQuantidadeProducao());

        System.out.println("Frutonome=" +pais.getProducaoAno(2001).getProducaoFruto(515).getFruto().getNome()+
                " Ano=" + pais.getProducaoAno(2001).getAno()+" Quantidadeproducao=" + pais.getProducaoAno(2001).getProducaoFruto(515).getQuantidadeProducao());

        System.out.println("Frutonome=" +pais.getProducaoAno(2002).getProducaoFruto(515).getFruto().getNome()+
                " Ano=" + pais.getProducaoAno(2002).getAno()+" Quantidadeproducao=" + pais.getProducaoAno(2002).getProducaoFruto(515).getQuantidadeProducao());

        System.out.println("Frutonome=" +pais.getProducaoAno(2000).getProducaoFruto(486).getFruto().getNome()+
                " Ano=" + pais.getProducaoAno(2000).getAno()+" Quantidadeproducao=" + pais.getProducaoAno(2000).getProducaoFruto(486).getQuantidadeProducao());

        System.out.println("Frutonome=" +pais.getProducaoAno(2001).getProducaoFruto(486).getFruto().getNome()+
                " Ano=" + pais.getProducaoAno(2001).getAno()+" Quantidadeproducao=" + pais.getProducaoAno(2001).getProducaoFruto(486).getQuantidadeProducao());

        System.out.println("Frutonome=" +pais.getProducaoAno(2002).getProducaoFruto(486).getFruto().getNome()+
                " Ano=" + pais.getProducaoAno(2002).getAno()+" Quantidadeproducao=" + pais.getProducaoAno(2002).getProducaoFruto(486).getQuantidadeProducao());

        */

        //lista com todos os tripletos de [ano , frutoID , quantidadeproducao]
        List tripletos = getListProdFrutosQuantidadeAno(174);

        //Ordenar lista por frutoID seguido de quantidadeproducao
        List tripletosOrdenados = sortPorFrutosIDPorQuantidadeProducaoAno(tripletos,cmpIDFruto,cmpQuantProducao);

        //Fazer a diferenca dos absolutos
        List<String> estrutura = getEstruturaPedida(tripletosOrdenados);

        //Imprimir a lista pedida
        ListPrinter.print(estrutura,"[AnoDaProducaoMinima/AnoDaProducaoMaxima,Nome do Fruto(ordenado por IDFruto), Diff Abs das quantidades de producao]\n",null);

    }



    public List<Triplet<Integer, Integer, Integer>> getListProdFrutosQuantidadeAno(int idPais)
    {

        // [ano , frutoID , quantidadeproducao]
        var estrutura = new LinkedList<Triplet<Integer, Integer, Integer>>();
        Pais pais =  app.getPaisStore().getPais(idPais);

        for (Iterator<ProducaoAno> producaoAnualIter = pais.iterator(); producaoAnualIter.hasNext(); )
        {
            if (!producaoAnualIter.hasNext())
                throw new RuntimeException("erro: nao ha producao naquele ano");

            ProducaoAno prodAnual = producaoAnualIter.next();

            //Iterator<ProducaoFrutoPorPaisPorAno>
            for(Iterator<ProducaoFrutoPorPaisPorAno> producaoFrutoPorPaisPorAnoIter =  prodAnual.iterator(); producaoFrutoPorPaisPorAnoIter.hasNext(); )
            {
                if (!producaoFrutoPorPaisPorAnoIter.hasNext())
                    throw new RuntimeException("erro: nao ha producao para aquele fruto naquele ano");

                ProducaoFrutoPorPaisPorAno prodFrutoPorPaisPorAno = producaoFrutoPorPaisPorAnoIter.next();

                // [ano , frutoID , quantidadeproducao]
                estrutura.add(new Triplet<Integer, Integer, Integer>(prodAnual.getAno(), prodFrutoPorPaisPorAno.getFruto().getId(), prodFrutoPorPaisPorAno.getQuantidadeProducao()));

            }

        }

       return estrutura;
    }


    public <E extends Triplet<Integer, Integer, Integer>> List<Triplet<Integer, Integer, Integer>> sortPorFrutosIDPorQuantidadeProducaoAno(List<E> list, Comparator<E> cmpIDFruto, Comparator<E> cmpQuantProducao)
    {
        var result = new LinkedList<Triplet<Integer, Integer, Integer>>();

        list.sort(cmpIDFruto.thenComparing(cmpQuantProducao));
        list.forEach(e -> result.add(new Triplet<Integer, Integer, Integer>(e.getFirst(),e.getSecond(),e.getThird())));

        return result;
    }


    public <E extends Triplet<Integer, Integer, Integer>> List<String> getEstruturaPedida(List<E> list)
    {

        var result = new LinkedList<String>();
        final int[] start = {list.get(0).getSecond()};
        final int[] max = {list.get(0).getThird()};
        final int[] yearMax = {list.get(0).getFirst()};
        final int[] min = {list.get(0).getThird()};
        final int[] yearMin = {list.get(0).getFirst()};

        // TODO: VER SE NAO HA UMA MELHOR MANEIRA DE FAZER ESTA PARTE, TALVEZ COM UM ITERADOR DENTRO DO FOREACH
        list.forEach(e -> {
            if(e.getSecond() == start[0])
            {
                if(e.getThird() >= max[0])
                {
                    max[0] = e.getThird();
                    yearMax[0] = e.getFirst().intValue();
                }
                else if (e.getThird().intValue() < min[0])
                {
                    min[0] = e.getThird().intValue();
                    yearMin[0] = e.getFirst().intValue();
                }
            }
            else
            {

                int diff = Math.abs(max[0] - min[0]);
                String s = "["+yearMin[0]+"/"+yearMax[0]+ "," + app.getFrutoStore().getFruto(start[0]).getNome() + "," + diff + "]";
                result.add(s);

                start[0] = e.getSecond().intValue();
                max[0] = e.getThird().intValue();
                min[0]= e.getThird().intValue();
                yearMin[0] = e.getFirst().intValue();
                yearMax[0] = e.getFirst().intValue();

            }

        });

        int diff = Math.abs(max[0] - min[0]);

        // [anox/anoy , nomefruto , diferenca entre o maior e menor valor de producao]
        String s = "["+yearMin[0]+"/"+yearMax[0]+ "," + app.getFrutoStore().getFruto(start[0]).getNome() + "," + diff + "]";
        result.add(s);

        return result;
    }

}
