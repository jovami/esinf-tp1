package esinf.model.store;

import java.util.HashMap;

import esinf.model.Pais;

public class PaisStore {
    private HashMap<Integer,Pais> paisStore;

    public PaisStore(){
        paisStore = new HashMap<>();
    }
    
    //TODO: necessário?
    public boolean checkPais(int id){
        return paisStore.containsKey(id);
    }
    
    //TODO: addPais
    public boolean addPais(int id,String paisNome){

        if((paisStore.put(id,new Pais(paisNome,id)))!=null){
            return true;
        }

        return false;
        
    }
    
}
