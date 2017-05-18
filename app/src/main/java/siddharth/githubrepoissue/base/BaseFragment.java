package siddharth.githubrepoissue.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import siddharth.githubrepoissue.SearchApplication;

/**
 * Created by siddharth on 13/5/17.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        injectDependencies(SearchApplication.get(context));

        // can be used for general purpose in all Fragments of Application
    }

    protected abstract void injectDependencies(SearchApplication application);

}
