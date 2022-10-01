package esinf;

import esinf.model.store.PaisStore;

/**
 * App
 * Singleton pattern based class,
 * used to obtain the PaisStore
 */
public class App {
    private PaisStore store;

    private App() {
        this.store = new PaisStore();
    }

    public PaisStore getPaisStore() {
        return this.store;
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
