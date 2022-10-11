package esinf.exercicios;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
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

    public final String FILE_NAME = "FAOSTAT_data_en_9-7-2022_SMALLTEST_QUOTATIONMARKS.csv";

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
            saveInfo(csvReader.readCSV(dir));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private enum Colunas {
        NOMEPAIS(3), IDPAIS(2), NOMEFRUTO(7), IDFRUTO(6), ANOPRODUCAO(8), QTDPRODUCAO(11);

        private final int i;

        Colunas(int i) {
            this.i = i;
        }

        private int getColuna() {
            return i;
        }
    }

    public void saveInfo(List<String[]> list) {
        String nomePais, nomeFruto;
        int idPais, idFruto, anoProducao, qtdProducao;

        for (String[] info : list) {
            nomePais = info[Colunas.NOMEPAIS.getColuna()];
            nomeFruto = info[Colunas.NOMEFRUTO.getColuna()];
            idPais = Integer.parseInt(info[Colunas.IDPAIS.getColuna()]);
            idFruto = Integer.parseInt(info[Colunas.IDFRUTO.getColuna()]);
            anoProducao = Integer.parseInt(info[Colunas.ANOPRODUCAO.getColuna()]);

            if (info[Colunas.QTDPRODUCAO.getColuna()].matches("")) {
                qtdProducao = 0;
            }else {
                qtdProducao = Integer.parseInt(info[Colunas.QTDPRODUCAO.getColuna()]);
            }

            if (info[Colunas.QTDPRODUCAO.getColuna()].matches("[0-9]+")) {
                savePais(nomePais, idPais);
                saveFruto(nomeFruto, idFruto);
                Pais pais = app.getPaisStore().getPais(idPais);

                ProducaoAno producaoAno;

                if (pais.containsAnoProducao(anoProducao))
                    producaoAno = pais.getProducaoAno(anoProducao);
                else
                    producaoAno = pais.createAnoProducao(anoProducao);

                Fruto fruto = app.getFrutoStore().getFruto(idFruto);
                producaoAno.addProducaoFruto(fruto, qtdProducao);

                pais.addAnoProducao(producaoAno);
            }
        }
    }

    private void savePais(String pais, int id) {
        app.getPaisStore().addPais(id, pais);
    }

    private void saveFruto(String fruto, int id) {
        app.getFrutoStore().addFruto(id, fruto);
    }

    private File getFileFromResource() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(FILE_NAME);
        if (resource == null) {
            throw new IllegalArgumentException("error: file not found! " + FILE_NAME);
        } else {
            return new File(resource.toURI());
        }
    }

    public File fileDirReader() throws Exception {
        File dir = getFileFromResource();
        if (dir.isFile() && dir.canRead())
            return dir;
        throw new Exception("erro: o ficheiro nao existe");
    }
}
