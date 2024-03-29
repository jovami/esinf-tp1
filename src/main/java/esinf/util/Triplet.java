package esinf.util;

/**
 * Triplet
 */
public class Triplet<T, S, U> {

    private final T first;
    private final S second;
    private final U third;

    public Triplet(T fst, S snd, U trd) {
        this.first = fst;
        this.second = snd;
        this.third = trd;
    }

	public T getFirst() {
		return this.first;
	}

	public S getSecond() {
		return this.second;
	}

	public U getThird() {
		return this.third;
	}
}
