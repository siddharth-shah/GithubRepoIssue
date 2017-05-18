package siddharth.githubrepoissue;

import android.app.Application;
import android.content.Context;

import siddharth.githubrepoissue.search.SearchModule;
import siddharth.githubrepoissue.search.SearchSubComponent;

/**
 * Created by siddharth on 13/5/17.
 */

public class SearchApplication extends Application {

    private static ApplicationComponent component;
    private SearchSubComponent searchSubComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    private ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder().
                androidModule(new AndroidModule(this)).
                build();
    }


    public static SearchApplication get(Context context) {
        return (SearchApplication) context.getApplicationContext();
    }

    public SearchSubComponent getSearchSubComponent() {
        if (searchSubComponent == null) {
            createSearchComponent();
        }
        return searchSubComponent;
    }

    private void createSearchComponent() {
        searchSubComponent = component.plus(new SearchModule());
    }

    public static ApplicationComponent getComponent() {
        return component;
    }


}
