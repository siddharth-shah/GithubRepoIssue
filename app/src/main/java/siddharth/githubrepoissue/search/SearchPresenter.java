package siddharth.githubrepoissue.search;


import siddharth.githubrepoissue.base.BasePresenter;

/**
 * Created by siddharth on 14/5/17.
 */

interface SearchPresenter extends BasePresenter<MySearchView> {


    void doSearch(String owner, String repoName);

    boolean isQueryValid(String query);

    void onReachEndOfPageLoadMore();

    void resetPresenter(String owner, String repoName);


}
