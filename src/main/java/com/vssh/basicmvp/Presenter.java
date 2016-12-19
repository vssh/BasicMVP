package com.vssh.basicmvp;

import android.content.Context;

/**
 * Created by varun on 19.10.16.
 */

public interface Presenter<T> {
    void bindView(T view, Context context);
    void unbindView();
}
