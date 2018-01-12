package com.vssh.basicmvp;

import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;

/**
 * Created by varun on 19.10.16.
 */

public abstract class BmvpFragment<T extends BmvpPresenterInterface> extends Fragment {
    private boolean willBeRecreated;
    public T presenter;
    private int presenterId = -1;

    @CallSuper
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            presenterId = savedInstanceState.getInt("presenterId");
        }
        if (presenterId > 0) {
            presenter = (T) PresenterStore.getInstance().getPresenter(presenterId);
        }
        if(presenter == null) {
            presenter = createNewPresenter();
            presenterId = PresenterStore.getInstance().registerPresenter(presenter);
        }
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, Bundle savedState) {
        super.onViewCreated(view, savedState);
        presenter.bindView(this);
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        willBeRecreated = false;
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("presenterId", presenterId);
        super.onSaveInstanceState(outState);
        willBeRecreated = true;
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unbindView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!willBeRecreated) {
            PresenterStore.getInstance().removePresenter(presenterId);
        }
    }

    public abstract T createNewPresenter();
}
