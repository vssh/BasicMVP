package com.vssh.basicmvp;

import android.app.DialogFragment;
import android.os.Bundle;
import androidx.annotation.CallSuper;
import android.view.View;

/**
 * Created by varun on 30.12.17.
 */

public abstract class BmvpDialogFragment<T extends BmvpPresenterInterface> extends DialogFragment implements BmvpViewInterface {
    private boolean willBeRecreated;
    public T presenter;
    private int presenterId = -1;

    @CallSuper
    @Override
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
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
