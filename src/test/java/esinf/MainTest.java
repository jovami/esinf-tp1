package esinf;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;

import esinf.exercicios.Exercicio1;
import esinf.model.CSVReader;

public class MainTest {
    static final String TEST_FILE = "FAOSTAT_data_en_9-7-2022_SMALLTEST_QUOTATIONMARKS.csv";

    public static enum Frutos {
        APPLE(515),
        BANANAS(486),
        BLUEBERRIES(552),
        BRAZIL_NUTS(216),
        CHERRIES(531),
        INVALID_FRUIT(-1);

        private final int code;
        public int getCode() {
            return this.code;
        }

        Frutos(int i) {
            this.code = i;
        }
    }

    public static enum Paises {
        AFGHANISTAN(2),
        ALBANIA(3),
        ALGERIA(4),
        ANGOLA(7),
        PANAMA(166),
        PAPUA_NEW_GUINEA(168),
        INVALID_COUNTRY(-1);

        private final int code;
        public int getCode() {
            return this.code;
        }

        Paises(int i) {
            this.code = i;
        }
    }

    public static void beforeEach() {
        resetSingleton();

        try {
            File f = getFileFromResource(TEST_FILE);
            new Exercicio1().saveInfo(new CSVReader().readCSV(f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetSingleton() {
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
