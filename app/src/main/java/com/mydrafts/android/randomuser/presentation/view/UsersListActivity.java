package com.mydrafts.android.randomuser.presentation.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mydrafts.android.randomuser.R;
import com.mydrafts.android.randomuser.RandomUserApplication;
import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.di.ActivityModule;
import com.mydrafts.android.randomuser.presentation.presenter.UsersListPresenter;
import com.mydrafts.android.randomuser.presentation.view.adapter.UsersListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersListActivity extends AppCompatActivity implements UsersListPresenter.View {

    @Inject
    UsersListPresenter presenter;

    private UsersListAdapter adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_empty)
    View emptyView;
    @BindView(R.id.base_layout)
    View baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        initializeButterKnife();
        initializeDagger();
        initializePresenter();
        initializeRecyclerView();

        toolbar.setTitle(getResources().getString(R.string.app_name));
    }

    /////// UserListPresenter View ///////

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView(String message) {
        Snackbar.make(baseLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showUsersList(List<User> users) {
        adapter.addUsers(users);
    }

    @Override
    public void openUserDetailsScreen(User user) {
        UserDetailsActivity.open(this, user.getId());
    }

    /////// Private methods ///////

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void initializeDagger() {
        RandomUserApplication.get(this)
                .getAppComponent().activityComponent(new ActivityModule(this))
                .inject(this);
    }

    private void initializeRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new UsersListAdapter(presenter, recyclerView);

        recyclerView.setAdapter(adapter);
    }
}
