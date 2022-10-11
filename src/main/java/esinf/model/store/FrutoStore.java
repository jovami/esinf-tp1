package esinf.model.store;

import java.util.HashMap;
import java.util.Iterator;

import esinf.model.Fruto;

public class FrutoStore implements Iterable<Fruto> {

    private HashMap<Integer,Fruto> frutoStore;

    public FrutoStore() {
        frutoStore= new HashMap<>();
    }

    public Fruto getFruto(int idFruto) {
        return frutoStore.get(idFruto);
    }

    public boolean hasFruto(int idFruto) {
        return frutoStore.get(idFruto) != null;
    }

    //retorna falso caso exista já um elemento destes
    public boolean addFruto(int idFruto, String nomeFruto) {
        if (this.hasFruto(idFruto))
            return false;

        return frutoStore.put(idFruto,new Fruto(nomeFruto, idFruto))!= null;
    }

    public int size() {
        return this.frutoStore.size();
    }


    @Override
    public Iterator<Fruto> iterator() {
        return frutoStore.values().iterator();
    }
}
