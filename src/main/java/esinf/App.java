package esinf;

import esinf.model.store.FrutoStore;
import esinf.model.store.PaisStore;

/**
 * App
 * Singleton pattern based class,
 * used to obtain the PaisStore
 */
public class App {
    private final PaisStore paisStore;
    private final FrutoStore frutoStore;

    private App() {
        this.paisStore = new PaisStore();
        this.frutoStore = new FrutoStore();
    }

    public PaisStore getPaisStore() {
        return this.paisStore;
    }

    public FrutoStore getFrutoStore() {
		return this.frutoStore;
	}

	/* singleton pattern */
    private static App singleton = null;
    public static App getInstance() {
        if (singleton == null) {
            synchronized(App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }
}
