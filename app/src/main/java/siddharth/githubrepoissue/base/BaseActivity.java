package siddharth.githubrepoissue.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import siddharth.githubrepoissue.ApplicationComponent;
import siddharth.githubrepoissue.SearchApplication;


/**
 * Created by siddharth on 13/5/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(SearchApplication.get(this), SearchApplication.getComponent());

        // can be used for general purpose in all Activities of Application
    }

    protected abstract void injectDependencies(SearchApplication application, ApplicationComponent component);

    @Override
    public void finish() {
        super.finish();

        releaseSubComponents(SearchApplication.get(this));
    }

    protected abstract void releaseSubComponents(SearchApplication application);
}
