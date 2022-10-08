package esinf.exercicios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Exercicio1Test {

    List<String[]> info = new ArrayList<>();

    @Test
    void run() {
        Exercicio1 exercicio1 = new Exercicio1();

        exercicio1.run();
    }

    @Test
    void saveInfo(){
        Exercicio1 exercicio1 = new Exercicio1();

        startArrayList();
        exercicio1.saveInfo(info);
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