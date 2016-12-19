package com.vssh.basicmvp;

import android.content.Context;
import android.support.annotation.CallSuper;

/**
 * Created by varun on 19.10.16.
 */

public abstract class BasePresenter<T> implements Presenter<T> {
    public T view;
    public Context context;

    @Override
    @CallSuper
    public void bindView(T view, Context context) {
        this.view = view;
        this.context = context;
    }

    /**
     * Always call at the end of overridden method
     */
    @Override
    @CallSuper
    public void unbindView() {
        view = null;
        context = null;
    }
}
