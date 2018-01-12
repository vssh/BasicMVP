package com.vssh.basicmvp;

import android.support.v4.util.SimpleArrayMap;

/**
 * Created by varun on 19.10.16.
 */
class PresenterStore {
    private static PresenterStore INSTANCE = new PresenterStore();
    private SimpleArrayMap<Integer, BmvpPresenterInterface> presenters = new SimpleArrayMap<>();
    private int seed = 0;

    public static PresenterStore getInstance() {
        return INSTANCE;
    }

    private PresenterStore() {
    }

    final BmvpPresenterInterface getPresenter(int id) {
        return presenters.get(id);
    }

    final void removePresenter(int id) {
        if (presenters != null) {
            presenters.remove(id);
        }
    }

    int registerPresenter(BmvpPresenterInterface presenter) {
        presenters.put(++seed, presenter);
        return seed;
    }
}
