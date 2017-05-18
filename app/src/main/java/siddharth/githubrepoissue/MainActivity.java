package siddharth.githubrepoissue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import siddharth.githubrepoissue.base.BaseActivity;
import siddharth.githubrepoissue.search.SearchActivity;

public class MainActivity extends BaseActivity {

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(context, SearchActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void injectDependencies(SearchApplication application, ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    protected void releaseSubComponents(SearchApplication application) {

    }
}
