package siddharth.githubrepoissue.search;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.*;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import siddharth.githubrepoissue.R;
import siddharth.githubrepoissue.SearchApplication;
import siddharth.githubrepoissue.base.BaseFragment;
import siddharth.githubrepoissue.data.model.Issue;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment implements MySearchView, SearchView.OnQueryTextListener {

    @Inject
    Context context;

    @Inject
    SearchPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.search_data_list)
    RecyclerView searchDataList;

    @BindView(R.id.content_progress)
    ProgressBar contentProgress;

    @BindView(R.id.empty_layout)
    FrameLayout emptyLayout;

    private SearchAdapter searchAdapter;

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, view);
        ((SearchActivity) getActivity()).setSupportActionBar(toolbar);
        searchAdapter = new SearchAdapter(new ArrayList<>(0), context);
        searchDataList.setLayoutManager(new LinearLayoutManager(context));
        searchDataList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        searchDataList.setAdapter(searchAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem search = menu.findItem(R.id.search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter.bind(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.unbind();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (presenter.isQueryValid(query)) {
            String[] querySplit = query.split("/");
            presenter.resetPresenter(querySplit[0], querySplit[1]);
            presenter.doSearch(querySplit[0], querySplit[1]);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void injectDependencies(SearchApplication application) {
        application.getSearchSubComponent().inject(this);
    }

    @Override
    public void showMessageLayout(boolean show) {
        searchDataList.setVisibility(show ? View.GONE : View.VISIBLE);
        emptyLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        showMessageLayout(true);
    }


    @Override
    public void showProgress() {
        contentProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        contentProgress.setVisibility(View.GONE);
        searchAdapter.removeLoadingView();

    }


    @Override
    public void showEmpty() {

    }

    @Override
    public void showItems(List<Issue> issues) {
        searchAdapter.addItems(issues);
    }

    @Override
    public void clearList() {
        searchAdapter.removeAll();
    }

    private EndlessRecyclerViewOnScrollListener setupScrollListener(boolean isTabletLayout,
                                                                    RecyclerView.LayoutManager layoutManager) {
        return new EndlessRecyclerViewOnScrollListener(isTabletLayout ?
                (GridLayoutManager) layoutManager : (LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                searchAdapter.addLoadingView();
                presenter.onReachEndOfPageLoadMore();

            }
        };
    }


//    @Override
//    public void onListClick(Issue issue) {
//        Toast.makeText(context, issue.getTitle(), Toast.LENGTH_SHORT).show();
//    }
}
