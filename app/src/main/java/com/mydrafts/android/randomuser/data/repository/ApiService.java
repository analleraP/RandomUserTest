package com.mydrafts.android.randomuser.data.repository;

import com.mydrafts.android.randomuser.data.entity.apimodel.ApiUser;
import com.mydrafts.android.randomuser.data.entity.apimodel.PagedResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/")
    Call<PagedResult<ApiUser>> getRandomUsers(@Query("page") int page, @Query("results") int results);
}
