package esinf.exercicios;

import esinf.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Exercicio1
 */
public class Exercicio1 implements Runnable {

    private App app;

    private final int EXPECTED_COLUMNS;

    private final CSVHeader header;

    public Exercicio1()
    {
        app = App.getInstance();
        this.header = CSVHeader.HEADER1;
        this.EXPECTED_COLUMNS = header.getColumnCount();
    }

    @Override
    public void run() {
        try {
            File dir = fileDirReader();

            saveinfo(readCSV(dir));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void saveinfo(List<String[]> list) {
        for (String[] info : list){
            
        }
    }

    public File fileDirReader() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o diretório do ficheiro:");
        File dir = new File(sc.nextLine());
        if (dir.exists())
            return dir;
        throw new Exception("erro: o ficheiro nao existe");
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
                    line = br.readLine(); // we can skip the header
                } else {
                    throw new Exception("error: the header of the file is INVALID");
                }

                do {
                    tmp = line.split(delim);
                    if (tmp.length != EXPECTED_COLUMNS)
                        throw new Exception(String.format("error: the csv file contains invalid data!\nOffending Line:\n\t%s", line));
                    else
                        info.add(tmp);
                } while ((line = br.readLine()) != null);
            } finally {
                br.close();
            }
        } catch (IOException e) {
            System.err.println("Error reading file. Aborting...");
        }

        return info;
    }

    public enum CSVHeader {
        HEADER1(14, ";") {
            @Override
            public String toString() {
                return "Domain Code;Domain;Area Code (FAO);Area;Element Code;Element;Item Code (FAO);Item;Year Code;Year;Unit;Value;Flag;Flag Description";
            }
        };

        private final int colums;

        private final String delimiter;

        public int getColumnCount()
        {
            return this.colums;
        }

        public String getDelimiter()
        {
            return this.delimiter;
        }

        CSVHeader(int col, String delim)
        {
            this.colums = col;
            this.delimiter = delim;
        }
    }

    private boolean isHeader(String line)
    {
        return line.trim().equalsIgnoreCase(this.header.toString());
    }
}
