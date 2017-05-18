package siddharth.githubrepoissue.search;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import siddharth.githubrepoissue.R;
import siddharth.githubrepoissue.data.model.Issue;

/**
 * Created by siddharth on 17/5/17.
 */

class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Issue> issues;


    Context context;

    static final int VIEW_TYPE_LOADING = 0;
    static final int VIEW_TYPE_LIST = 1;
    private int listType;
    private InteractionListener mListInteractionListener;

    @IntDef({VIEW_TYPE_LOADING, VIEW_TYPE_LIST})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {

    }

    @ViewType
    private int mViewType;

    SearchAdapter(List<Issue> issues, Context context) {
        this.issues = issues;
        this.context = context;
        mViewType = VIEW_TYPE_LIST;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case VIEW_TYPE_LIST:
                final LayoutInflater layoutInflater = LayoutInflater.from(context);
                return new SearchItemViewHolder(layoutInflater.inflate(R.layout.search_item, parent, false));
            case VIEW_TYPE_LOADING:
                return new
                        ProgressBarViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.item_progress_bar, parent, false));
        }
        return null;
    }


    public void setListType(int listType) {
        this.listType = listType;

    }

    public int getListType() {
        return listType;
    }

    @Override
    public int getItemViewType(int position) {
        return issues.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_LIST;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_LOADING) {
            return;
        }

        final Issue issue = issues.get(position);
//
//        ((SearchItemViewHolder) holder).parentView.setOnClickListener(v -> mListInteractionListener
//                .onListClick(issue));
        ((SearchItemViewHolder) holder).title.setText(issue.getTitle());
        ((SearchItemViewHolder) holder).issueBody.setText(issue.getBody());
        ((SearchItemViewHolder) holder).issueNo.setText("#" + String.valueOf(issue.getNumber()));
        ((SearchItemViewHolder) holder).issueReportedBy.setText("Reported by: " + issue.getUser().getLogin());


    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    private void add(Issue item) {
        add(null, item);
    }

    void add(@Nullable Integer position, Issue item) {
        if (position != null) {
            issues.add(position, item);
            notifyItemInserted(position);
        } else {
            issues.add(item);
            notifyItemInserted(issues.size() - 1);
        }
    }


    void addItems(List<Issue> issues) {
        this.issues.addAll(issues);
        notifyItemRangeInserted(getItemCount(), issues.size() - 1);
    }

    private void remove(int position) {
        if (issues.size() < position) {
            return;
        }
        issues.remove(position);
        notifyItemRemoved(position);
    }

    void removeAll() {
        issues.clear();
        notifyDataSetChanged();
    }

    boolean addLoadingView() {
        if (getItemViewType(issues.size() - 1) != VIEW_TYPE_LOADING) {
            add(null);
            return true;
        }
        return false;
    }

    boolean removeLoadingView() {
        if (issues.size() > 1) {
            int loadingViewPosition = issues.size() - 1;
            if (getItemViewType(loadingViewPosition) == VIEW_TYPE_LOADING) {
                remove(loadingViewPosition);
                return true;
            }
        }
        return false;
    }

    boolean isEmpty() {
        return getItemCount() == 0;
    }


    class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progress_bar)
        ProgressBar progressBar;

        ProgressBarViewHolder(View view) {
            super(view);
        }
    }

    class SearchItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.issue_title)
        TextView title;
        @BindView(R.id.issue_body)
        TextView issueBody;
        @BindView(R.id.issue_no)
        TextView issueNo;
        @BindView(R.id.reported_by)
        TextView issueReportedBy;

        SearchItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface InteractionListener {
        void onListClick(Issue issue);
    }

    void setListInteractionListener(InteractionListener listInteractionListener) {
        mListInteractionListener = listInteractionListener;
    }
}