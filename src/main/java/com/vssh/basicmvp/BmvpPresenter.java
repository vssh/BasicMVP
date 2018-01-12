package com.vssh.basicmvp;

import android.support.annotation.CallSuper;

/**
 * Created by varun on 19.10.16.
 */

public abstract class BmvpPresenter<T extends BmvpViewInterface> implements BmvpPresenterInterface<T> {
    public T view;

    @Override
    @CallSuper
    public void bindView(T view) {
        this.view = view;
    }

    /**
     * Always call at the end of overridden method
     */
    @Override
    @CallSuper
    public void unbindView() {
        view = null;
    }
}
