package com.mydrafts.android.randomuser.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mydrafts.android.randomuser.R;
import com.mydrafts.android.randomuser.RandomUserApplication;
import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.di.ActivityModule;
import com.mydrafts.android.randomuser.presentation.presenter.UserDetailsPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsPresenter.View {

    private static final String USER_ID_KEY = "user_id_key";

    @Inject
    UserDetailsPresenter presenter;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_picture)
    ImageView userPicture;
    @BindView(R.id.tv_gender)
    TextView userGender;
    @BindView(R.id.tv_email)
    TextView userEmail;
    @BindView(R.id.tv_location)
    TextView userLocation;
    @BindView(R.id.tv_registered)
    TextView userRegistered;
    @BindView(R.id.base_layout)
    View baseLayout;

    public static void open(Context context,  @NonNull String userID) {
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra(USER_ID_KEY, userID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        initializeButterKnife();
        initializeToolbar();
        initializeDagger();
        initializePresenter();

        if (getIntent().hasExtra(USER_ID_KEY)) {
            presenter.onLoadUserByID(getIntent().getStringExtra(USER_ID_KEY));
        } else {
            Snackbar.make(baseLayout, "Oops, something went wrong :/", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /////// UserDetailsPresenter View ///////

    @Override
    public void showErrorView(String message) {
        Snackbar.make(baseLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showUserDetails(User user) {

        toolbarLayout.setTitle(user.getFullName());

        Picasso.with(this)
                .load(user.getLargePicture())
                .fit()
                .centerCrop()
                .into(userPicture);

        userGender.setText(user.getGender());
        userEmail.setText(user.getEmail());
        userLocation.setText(user.getAddress());
        userRegistered.setText(user.getRegistered()); //TODO Show pretty print
    }

    /////// Private methods ///////

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void initializeDagger() {
        RandomUserApplication.get(this)
                .getAppComponent().activityComponent(new ActivityModule(this))
                .inject(this);
    }
}
