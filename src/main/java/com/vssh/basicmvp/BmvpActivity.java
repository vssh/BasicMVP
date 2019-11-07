package com.vssh.basicmvp;

import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by varun on 19.10.16.
 */

public abstract class BmvpActivity<T extends BmvpPresenterInterface> extends AppCompatActivity implements BmvpViewInterface {
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
    protected void onStart() {
        super.onStart();
        presenter.bindView(this);
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        willBeRecreated = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("presenterId", presenterId);
        super.onSaveInstanceState(outState);
        willBeRecreated = true;
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
        presenter.unbindView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!willBeRecreated) {
            PresenterStore.getInstance().removePresenter(presenterId);
            presenterId = -1;
        }
    }

    /**
     * create and return the corresponding presenter here
     */
    public abstract T createNewPresenter();
}
