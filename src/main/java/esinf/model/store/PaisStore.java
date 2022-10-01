package esinf.model.store;

import esinf.model.Pais;

import java.util.HashMap;
import java.util.Iterator;

public class PaisStore {

    private HashMap<Integer,Pais> paisStore;

    public PaisStore() {
        // num paises no mundo; evita re-hashing
        // Taiwan + https://www.worldometers.info/geography/how-many-countries-are-there-in-the-world/
        final int numCountries = 196;
        this.paisStore = new HashMap<>(numCountries);
    }

    public int getSize() {
        return this.paisStore.size();
    }

    //TODO: necessário?
    public boolean checkPais(int id){
        return this.paisStore.containsKey(id);
    }

    public boolean addPais(int id, String paisNome) {
        if (this.paisStore.containsKey(id))
            return false; // throw excep instead:

        return this.paisStore.put(id, new Pais(paisNome,id)) != null;
    }

    public Pais getPais(int id) {
        // TODO: nao returnar ref?
        return this.paisStore.get(id);
    }

    public Iterator<Pais> getIteradorPais() {
        return this.paisStore.values().iterator();
    }
}
