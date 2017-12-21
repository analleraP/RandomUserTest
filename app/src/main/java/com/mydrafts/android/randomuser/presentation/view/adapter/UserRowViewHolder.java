package com.mydrafts.android.randomuser.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mydrafts.android.randomuser.R;
import com.mydrafts.android.randomuser.data.entity.User;
import com.mydrafts.android.randomuser.presentation.presenter.UsersListPresenter;
import com.mydrafts.android.randomuser.presentation.view.PicassoCircleTransformation;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserRowViewHolder extends RecyclerView.ViewHolder {

    private final UsersListPresenter presenter;

    @BindView(R.id.iv_row_avatar) ImageView avatarImageView;
    @BindView(R.id.tv_row_name) TextView nameTextView;
    @BindView(R.id.tv_row_email) TextView emailTextView;
    @BindView(R.id.tv_row_phone) TextView phoneTextView;

    public UserRowViewHolder(View itemView, UsersListPresenter presenter) {
        super(itemView);
        this.presenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(User user) {
        renderUser(user);

        hookListeners(user);
    }

    private void hookListeners(final User user) {
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                presenter.onUserClicked(user);
            }
        });
    }

    private void renderUser(User user) {
        Picasso.with(getContext())
                .load(user.getThumbnail())
                .transform(new PicassoCircleTransformation())
                .fit()
                .centerCrop()
                .into(avatarImageView);

        nameTextView.setText(user.getFullName());
        emailTextView.setText(user.getEmail());
        phoneTextView.setText(user.getPhone());
    }

    private Context getContext() {
        return itemView.getContext();
    }
}
