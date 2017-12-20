package com.mydrafts.android.randomuser.data.repository;

import com.mydrafts.android.randomuser.data.entity.PagedResult;
import com.mydrafts.android.randomuser.data.entity.User;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    private static final String BASE_ENDPOINT = "https://api.randomuser.me/";

    private final ApiService apiService;

    public RemoteDataSource() {
        this(BASE_ENDPOINT);
    }

    public RemoteDataSource(String baseEndpoint) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseEndpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public Observable<PagedResult<User>> getUsers(int page, int results) {

        return apiService.getRandomUsers(page, results);
    }
}
