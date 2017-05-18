package siddharth.githubrepoissue.search;

import java.util.List;

import siddharth.githubrepoissue.base.BaseView;
import siddharth.githubrepoissue.data.model.Issue;

/**
 * Created by siddharth on 18/5/17.
 */

public interface MySearchView extends BaseView {

    void showItems(List<Issue> issues);

    void clearList();


}