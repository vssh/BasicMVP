package com.vssh.basicmvp;

import android.support.v4.util.SimpleArrayMap;

/**
 * Created by varun on 19.10.16.
 */
public class PresenterStore {
    private static PresenterStore INSTANCE = new PresenterStore();
    private SimpleArrayMap<Integer, Presenter> presenters = new SimpleArrayMap<>();
    private int seed = 0;

    public static PresenterStore getInstance() {
        return INSTANCE;
    }

    private PresenterStore() {
    }

    public final Presenter getPresenter(int id) {
        return presenters.get(id);
    }

    public final void removePresenter(int id) {
        if (presenters != null) {
            presenters.remove(id);
        }
    }

    public int registerPresenter(Presenter presenter) {
        presenters.put(++seed, presenter);
        return seed;
    }
}
