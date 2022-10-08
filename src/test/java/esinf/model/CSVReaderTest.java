package esinf.model;

import static org.junit.jupiter.api.Assertions.*;

import esinf.exercicios.Exercicio1;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class CSVReaderTest {

    List<String[]> info = new ArrayList<>();

    @BeforeAll
    public static void init(){
        List<String[]> info = new ArrayList<>();
        String[] line1 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1961","1961","tonnes","15100","*","Unofficial figure"};
        String[] line2 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1962","1962","tonnes","15100","F","FAO estimate"};
        String[] line3 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1962","1963","tonnes","15100","F","FAO estimate"};
        String[] line4 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1964","1964","tonnes","18400","F","FAO estimate"};
        String[] line5 = {"QCL","Crops and livestock products","4","Algeria","5510","Production","486","Bananas","1961","1961","tonnes","","M","Data not available"};

        info.add(line1);
        info.add(line2);
        info.add(line3);
        info.add(line4);
        info.add(line5);
    }

    @Test
    void readCSV() {
        CSVReader csvReader = new CSVReader();
        Exercicio1 exercicio1 = new Exercicio1();

        try {
            csvReader.readCSV(fileDirReader(TEST_FILE1));
            csvReader.readCSV(fileDirReader(TEST_FILE2));

            startArrayList();
            List<String[]> list = csvReader.readCSV(fileDirReader(TEST_FILE3));
            int i=0;
            for (String[] strings : list){
                assertArrayEquals(info.get(i), strings);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final String TEST_FILE1 = "FAOSTAT_data_en_9-7-2022_SMALLTEST.csv";
    public final String TEST_FILE2 = "FAOSTAT_data_en_9-7-2022_SMALLTEST_QUOTATIONMARKS.csv";
    public final String TEST_FILE3 = "ARRAY_ASSERT_TEST.csv";

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("error: file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    public File fileDirReader(String fileName) throws Exception {
        File dir = getFileFromResource(fileName);
        if (dir.isFile() && dir.canRead())
            return dir;
        throw new Exception("erro: o ficheiro nao existe");
    }

    private void startArrayList(){
        String[] line1 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1961","1961","tonnes","15100","*","Unofficial figure"};
        String[] line2 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1962","1962","tonnes","15100","F","FAO estimate"};
        String[] line3 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1962","1963","tonnes","15100","F","FAO estimate"};
        String[] line4 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1964","1964","tonnes","18400","F","FAO estimate"};
        String[] line5 = {"QCL","Crops and livestock products","4","Algeria","5510","Production","486","Bananas","1961","1961","tonnes","","M","Data not available"};

        info.add(line1);
        info.add(line2);
        info.add(line3);
        info.add(line4);
        info.add(line5);
    }
}