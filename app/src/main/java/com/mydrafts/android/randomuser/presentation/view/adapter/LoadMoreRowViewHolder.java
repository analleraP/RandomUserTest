package com.mydrafts.android.randomuser.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.mydrafts.android.randomuser.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadMoreRowViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.progress_bar) ProgressBar progressBar;

    public LoadMoreRowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
