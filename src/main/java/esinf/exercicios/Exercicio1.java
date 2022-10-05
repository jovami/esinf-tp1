package esinf.exercicios;

import java.io.File;
import java.util.List;

import esinf.App;
import esinf.model.CSVReader;
import esinf.model.Fruto;
import esinf.model.Pais;
import esinf.model.ProducaoAno;

/**
 * Exercicio1
 */
public class Exercicio1 implements Runnable {

    private App app;

    private CSVReader csvReader;

    public Exercicio1() {
        app = App.getInstance();
    }

    @Override
    public void run() {
        try {
            File dir = fileDirReader();

            saveinfo(csvReader.readCSV(dir));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private enum Colunas {
        NOMEPAIS(3), IDPAIS(2), NOMEFRUTO(7), IDFRUTO(6), ANOPRODUCAO(8), QTDPRODUCAO(11);

        private int i;

        Colunas(int i) {
            this.i = i;
        }

        private int getColuna(){
            return i;
        }
    }

    private void saveinfo(List<String[]> list) {
        for (String[] info : list) {
            savePais(info[Colunas.NOMEPAIS.getColuna()], Integer.parseInt(info[Colunas.IDPAIS.getColuna()]));
            saveFruto(info[Colunas.NOMEFRUTO.getColuna()], Integer.parseInt(info[Colunas.IDFRUTO.getColuna()]));

            Fruto fruto = app.getFrutoStore().getFruto(Colunas.IDPAIS.getColuna());
            Pais pais = app.getPaisStore().getPais(Colunas.IDPAIS.getColuna());
            pais.addAnoProducao(pais.createAnoProducao(Colunas.ANOPRODUCAO.getColuna()));

            ProducaoAno producaoAno = pais.getProducaoAno(Colunas.ANOPRODUCAO.getColuna());
            producaoAno.addProducaoFruto(fruto, Colunas.QTDPRODUCAO.getColuna());
        }
    }

    //TODO excep instead:
    private boolean savePais(String pais, int id){
        return app.getPaisStore().addPais(id, pais);
    }

    //TODO excep instead:
    private boolean saveFruto(String fruto, int id){
        return app.getFrutoStore().addFruto(id, fruto);
    }

    private final String FILE_DIRECTORY = "/Users/diogonapoles/Downloads/FAOSTAT_data_9-7-2022 (1)/FAOSTAT_data_en_9-7-2022_SMALL.csv";

    public File fileDirReader() throws Exception {
        File dir = new File(FILE_DIRECTORY);
        if (dir.exists())
            return dir;
        throw new Exception("erro: o ficheiro nao existe");
    }
}