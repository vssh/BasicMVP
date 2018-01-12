package com.vssh.basicmvp;

/**
 * Created by varun on 19.10.16.
 */

public interface BmvpPresenterInterface<T> {
    void bindView(T view);
    void unbindView();
}
