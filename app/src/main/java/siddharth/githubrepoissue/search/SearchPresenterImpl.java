package siddharth.githubrepoissue.search;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import siddharth.githubrepoissue.data.model.Issue;
import siddharth.githubrepoissue.data.network.RestApi;


/**
 * Created by siddharth on 14/5/17.
 */

public class SearchPresenterImpl implements SearchPresenter {
    private MySearchView view;
    private RestApi api;

    private boolean canLoadMore;
    private int currentPage = 0;
    private String owner = "";
    private String repoName = "";

    @Inject
    Context context;

    @Inject
    SearchPresenterImpl(RestApi restApi) {
        this.api = restApi;
    }


    @Override
    public void doSearch(String owner, String repoName) {
        if (!canLoadMore) {
            view.hideProgress();
            return;
        }
        view.showProgress();
        final Observable<List<Issue>> observable = api.getIssues(owner, repoName);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Issue>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        if (e.getMessage().toLowerCase().contains("unable to resolve host")) {
                            view.showErrorMessage("Internet Issue");
                        }
                    }

                    @Override
                    public void onNext(List<Issue> issues) {
                        view.hideProgress();
                        if (issues.size() == 0) {
                            view.showMessageLayout(true);
                            view.showErrorMessage("Oops! No Results!");

                        } else {
                            view.showMessageLayout(false);
                            view.showItems(issues);
                        }
                    }

                });


    }

    @Override
    public boolean isQueryValid(String query) {
        if ((query == null || query.isEmpty())) {
            view.showErrorMessage("Enter a valid query");
            return false;
        }
        String[] queryParams = query.split("/");
        if (queryParams.length == 2) {
            return true;
        } else {
            view.showErrorMessage("Enter a valid query");
            return false;
        }

    }

    @Override
    public void onReachEndOfPageLoadMore() {
        doSearch(owner, repoName);
    }

    @Override
    public void resetPresenter(String owner, String repoName) {
        canLoadMore = true;
        currentPage = 0;
        this.owner = owner;
        this.repoName = repoName;
        view.clearList();
    }


    @Override
    public void bind(MySearchView view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;

    }


}
