package com.mydrafts.android.randomuser.data.entity;

public interface Mapper<M, P> {
    P modelToApi(M model);

    M apiToModel(P data);
}