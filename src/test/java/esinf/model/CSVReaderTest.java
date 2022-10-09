package esinf.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderTest {

    List<String[]> info;

    @BeforeEach
    void startArrayList() {
        info = new ArrayList<>();

        String[] line1 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1961","1961","tonnes","15100","*","Unofficial figure"};
        String[] line2 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1962","1962","tonnes","15100","F","FAO estimate"};
        String[] line3 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1962","1963","tonnes","15100","F","FAO estimate"};
        String[] line4 = {"QCL","Crops and livestock products","2","Afghanistan","5510","Production","515","Apples","1964","1964","tonnes","18400","F","FAO estimate"};
        String[] line5 = {"QCL","Crops and livestock products","4","Algeria","5510","Production","486","Bananas","1961","1961","tonnes","","M","Data not available"};
        String[] line6 = {"QCL","Crops and livestock products","4","Algeria","5510","Production","486","Bananas","1961","1961","tonnes","72931","M","FAO estimate"};

        info.add(line1);
        info.add(line2);
        info.add(line3);
        info.add(line4);
        info.add(line5);
        info.add(line6);
    }

    @Test
    void readCSV() {
        CSVReader csvReader = new CSVReader();

        try {
            csvReader.readCSV(fileDirReader(TEST_FILE1));
            csvReader.readCSV(fileDirReader(TEST_FILE2));

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

    final String TEST_FILE1 = "FAOSTAT_data_en_9-7-2022_SMALLTEST.csv";
    final String TEST_FILE2 = "FAOSTAT_data_en_9-7-2022_SMALLTEST_QUOTATIONMARKS.csv";
    final String TEST_FILE3 = "ARRAY_ASSERT_TEST.csv";

    File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("error: file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    File fileDirReader(String fileName) throws Exception {
        File dir = getFileFromResource(fileName);
        if (dir.isFile() && dir.canRead())
            return dir;
        throw new Exception("erro: o ficheiro nao existe");
    }
}
