package com.vssh.basicmvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by varun on 19.10.16.
 */

public abstract class BasePresenterActivity<T extends BasePresenter> extends AppCompatActivity {
    private boolean willBeRecreated;
    public T presenter;
    private int presenterId = -1;

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

    @Override
    protected void onStart() {
        super.onStart();
        presenter.bindView(this, getApplicationContext());
    }

    @Override public void onResume() {
        super.onResume();
        willBeRecreated = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("presenterId", presenterId);
        super.onSaveInstanceState(outState);
        willBeRecreated = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unbindView();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (!willBeRecreated) {
            PresenterStore.getInstance().removePresenter(presenterId);
            presenterId = -1;
        }
    }

    public abstract T createNewPresenter();
}
