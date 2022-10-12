package esinf.util;

/**
 * Pair
 * Simple storage class to temporarily
 * store related objects
 */
public class Pair<T, S> {

    private final T first;
    private final S second;

    public Pair(T fst, S snd) {
        this.first = fst;
        this.second = snd;
    }

    public T getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return String.format("%s --- %s", this.getFirst(), this.getSecond());
    }
}
