package siddharth.githubrepoissue;

import javax.inject.Singleton;

import dagger.Component;
import siddharth.githubrepoissue.data.network.ApiModule;
import siddharth.githubrepoissue.data.network.ClientModule;
import siddharth.githubrepoissue.search.SearchModule;
import siddharth.githubrepoissue.search.SearchSubComponent;


/**
 * Created by siddharth on 13/5/17.
 */
@Singleton
@Component(modules = {
        AndroidModule.class,
        ApiModule.class,
        SearchApplicationModule.class,
        ClientModule.class
})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);


    SearchSubComponent plus(SearchModule searchModule);


}
