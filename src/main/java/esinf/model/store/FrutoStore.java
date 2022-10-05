package esinf.model.store;
import java.util.HashMap;

import esinf.model.Fruto;

public class FrutoStore {

    private HashMap<Integer,Fruto> frutoStore;

    public FrutoStore(){
        frutoStore= new HashMap<>();    
    }    
    
    public Fruto getFruto(int idFruto){
        return frutoStore.get(idFruto);
    }

    //TODO: change method name??
    public boolean checkFrutoExistence(int idFruto){
        return frutoStore.containsKey(idFruto);
    }

    
    public Fruto createFruto(int idFruto, String nomeFruto){
        if(!checkFrutoExistence(idFruto))// se já existir vai nao vai adicionar nada ao hashMap
            return new Fruto(nomeFruto, idFruto);            
        else
            throw new NullPointerException(String.format("Element already exist: Fruto %s"+nomeFruto));
    }


    //retorna falso caso exista já um elemento destes 
    public boolean addFruto(int idFruto, String nomeFruto){
        return frutoStore.put(idFruto,new Fruto(nomeFruto, idFruto))!= null;
    }

}
