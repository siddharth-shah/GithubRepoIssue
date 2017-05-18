package siddharth.githubrepoissue.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import siddharth.githubrepoissue.ApplicationComponent;
import siddharth.githubrepoissue.R;
import siddharth.githubrepoissue.SearchApplication;
import siddharth.githubrepoissue.base.BaseActivity;

public class SearchActivity extends BaseActivity {

    @Inject
    Context context;
    private SearchFragment searchFragment;
    public static final String TAG_SEARCH_FRAGMENT = "search_fragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (null == savedInstanceState) {
            searchFragment = SearchFragment.newInstance();
            attachFragments();
        }


    }

    @Override
    protected void injectDependencies(SearchApplication application, ApplicationComponent component) {
        application.getSearchSubComponent().inject(this);
    }

    @Override
    protected void releaseSubComponents(SearchApplication application) {

    }


    private void attachFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, searchFragment, TAG_SEARCH_FRAGMENT);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
