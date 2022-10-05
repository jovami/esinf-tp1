package esinf.model;

import esinf.model.enumerated.CSVHeader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private final CSVHeader header;

    private final int EXPECTED_COLUMNS;

    public CSVReader(CSVHeader header)
    {
        this.header = header;
        this.EXPECTED_COLUMNS = header.getColumnCount();
    }

    public List<String[]> readCSV(File dir) throws Exception {
    List<String[]> info = new ArrayList<>();

    String line, delim;
    String[] tmp;

        try {
        var br = new BufferedReader(new FileReader(dir));

        try {
            line = br.readLine();

            if (isHeader(line)) {
                delim = this.header.getDelimiter();
            } else {
                throw new Exception("error: the header of the file is INVALID");
            }

            while ((line = br.readLine()) != null);{
                tmp = line.split(delim);
                if (tmp.length != EXPECTED_COLUMNS)
                    throw new Exception(String.format("error: the csv file contains invalid data!\nOffending Line:\n\t%s", line));
                else
                    info.add(tmp);
            }
        } finally {
            br.close();
        }
    } catch (IOException e) {
        System.err.println("Error reading file. Aborting...");
    }

        return info;
    }
    private boolean isHeader(String line)
    {
        return line.trim().equalsIgnoreCase(this.header.toString());
    }

}
