package com.mydrafts.android.randomuser.data.repository;


import com.mydrafts.android.randomuser.data.entity.PagedResult;
import com.mydrafts.android.randomuser.data.entity.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/")
    Observable<PagedResult<User>> getRandomUsers(@Query("page") int page, @Query("results") int results);
}
