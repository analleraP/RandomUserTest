package com.mydrafts.android.randomuser.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydrafts.android.randomuser.R;
import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.di.ActivityScope;
import com.mydrafts.android.randomuser.presentation.presenter.UsersListPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class UsersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final UsersListPresenter presenter;
    private List<User> users = new ArrayList<>();

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROGRESS_BAR = 0;

    @Inject
    public UsersListAdapter(final UsersListPresenter presenter) {
        this.presenter = presenter;
        users = new ArrayList<>();

    }

    public void addUsers(List<User> newUsers) {
        users.addAll(newUsers);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        //We inflate the row value depending if it's an item or footer.
        if (viewType == VIEW_ITEM) {
            View row = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.row_users_list, parent, false);
            viewHolder = new UserRowViewHolder(row, presenter);

        } else {  //In any other case, it's footer
            View row = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.row_load_more, parent, false);
            viewHolder = new LoadMoreRowViewHolder(row);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserRowViewHolder) {
            UserRowViewHolder userRowViewHolder = (UserRowViewHolder) holder;
            User user = users.get(position);
            userRowViewHolder.render(user);
        }
    }

    @Override
    public int getItemCount() {
        return users.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return VIEW_PROGRESS_BAR;
        } else {
            return VIEW_ITEM;
        }
    }

    /**
     * Method to find where should be the footer
     *
     * @return footer position
     */
    private boolean isPositionFooter(int position) {
        return position == users.size();
    }
}
