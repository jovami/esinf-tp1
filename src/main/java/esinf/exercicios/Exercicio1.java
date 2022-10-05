package esinf.exercicios;

import esinf.App;
import esinf.model.CSVReader;

import java.io.File;
import java.util.List;

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

    private void saveinfo(List<String[]> list) {
        for (String[] info : list) {

        }
    }

    private final String FILE_DIRECTORY = null;

    public File fileDirReader() throws Exception {
        File dir = new File(FILE_DIRECTORY);
        if (dir.exists())
            return dir;
        throw new Exception("erro: o ficheiro nao existe");
    }
}