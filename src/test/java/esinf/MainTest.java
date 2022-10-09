package esinf;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;

import esinf.exercicios.Exercicio1;
import esinf.model.CSVReader;

public class MainTest {
    static final String TEST_FILE = "FAOSTAT_data_en_9-7-2022_SMALLTEST_QUOTATIONMARKS.csv";

    public static void beforeEach() {
        resetSingleton();

        try {
            File f = getFileFromResource(TEST_FILE);
            new Exercicio1().saveInfo(new CSVReader().readCSV(f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void resetSingleton() {
        try {
            Field instance = App.class.getDeclaredField("singleton");
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File getFileFromResource(String fileName) throws URISyntaxException {
        URL resource = MainTest.class.getClassLoader().getResource(fileName);

        if (resource != null) {
            File f = new File(resource.toURI());
            if (f.isFile() && f.canRead())
                return f;
        }

        throw new IllegalArgumentException("error: file not found! " + fileName);
    }
}
