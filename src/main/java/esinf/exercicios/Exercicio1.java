package esinf.exercicios;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
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
        this.csvReader = new CSVReader();
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

        private int getColuna() {
            return i;
        }
    }

    private void saveinfo(List<String[]> list) {
        for (String[] info : list) {

            // TODO: ha linhas sem quantidade NENHUMA de producao; ver linha 242 do ficheiro
            // fazer info[Colunas.QTDPRODUCAO.getColuna()] = "0" ????
            if (info[Colunas.QTDPRODUCAO.getColuna()].matches("[0-9]+")) {
                savePais(info[Colunas.NOMEPAIS.getColuna()], Integer.parseInt(info[Colunas.IDPAIS.getColuna()]));
                saveFruto(info[Colunas.NOMEFRUTO.getColuna()], Integer.parseInt(info[Colunas.IDFRUTO.getColuna()]));

                Pais pais = app.getPaisStore().getPais(Integer.parseInt(info[Colunas.IDPAIS.getColuna()]));

                ProducaoAno producaoAno;
                int ano = Integer.parseInt(info[Colunas.ANOPRODUCAO.getColuna()]);

                if (pais.containsAnoProducao(ano))
                    producaoAno = pais.getProducaoAno(ano);
                else
                    producaoAno = pais.createAnoProducao(ano);

                Fruto fruto = app.getFrutoStore().getFruto(Integer.parseInt(info[Colunas.IDFRUTO.getColuna()]));
                producaoAno.addProducaoFruto(fruto, Integer.parseInt(info[Colunas.QTDPRODUCAO.getColuna()]));

                pais.addAnoProducao(producaoAno);
            }
        }
    }

    //TODO excep instead:
    private boolean savePais(String pais, int id) {
        return app.getPaisStore().addPais(id, pais);
    }

    //TODO excep instead:
    private boolean saveFruto(String fruto, int id) {
        return app.getFrutoStore().addFruto(id, fruto);
    }

    private final String FILE_NAME = "FAOSTAT_data_en_9-7-2022_BIG.csv";

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("error: file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    public File fileDirReader() throws Exception {
        File dir = getFileFromResource(FILE_NAME);
        if (dir.isFile() && dir.canRead())
            return dir;
        throw new Exception("erro: o ficheiro nao existe");
    }
}
