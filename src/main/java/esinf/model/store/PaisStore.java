package esinf.model.store;

import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;

import esinf.model.Pais;

public class PaisStore implements Iterable<Pais> {

    private HashMap<Integer,Pais> paisStore;

    public PaisStore() {
        // num paises no mundo; evita re-hashing
        // Taiwan + https://www.worldometers.info/geography/how-many-countries-are-there-in-the-world/
        final int numCountries = 196;
        this.paisStore = new HashMap<>(numCountries);
    }


    public Stream<Pais> getStream() {
        return this.paisStore.values().stream();
    }

    public int getSize() {
        return this.paisStore.size();
    }

    //TODO: necessário?
    public boolean checkPais(int id) {
        return this.paisStore.containsKey(id);
    }

    public boolean addPais(int id, String paisNome) {
        if (this.paisStore.containsKey(id))
            return false; //TODO excep instead:

        return this.paisStore.put(id, new Pais(paisNome,id)) != null;
    }

    public Pais getPais(int id) {
        // TODO: nao returnar ref?
        return this.paisStore.get(id);
    }
    

    public Iterator<Pais> iterator() {
        return this.paisStore.values().iterator();
    }
}
