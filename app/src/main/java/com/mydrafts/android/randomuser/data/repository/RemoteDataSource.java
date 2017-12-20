package com.mydrafts.android.randomuser.data.repository;

import com.mydrafts.android.randomuser.data.entity.apimodel.ApiUser;
import com.mydrafts.android.randomuser.data.entity.apimodel.PagedResult;
import com.mydrafts.android.randomuser.data.exception.NetworkErrorException;
import com.mydrafts.android.randomuser.data.exception.UnknownErrorException;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    private static final String BASE_ENDPOINT = "https://api.randomuser.me/";

    private final ApiService apiService;

    @Inject
    public RemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public PagedResult<ApiUser> getApiUsers(int page, int results) throws UnknownErrorException, NetworkErrorException {
        try {
            Response<PagedResult<ApiUser>> response = apiService.getRandomUsers(page, results).execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new UnknownErrorException();
            }
        } catch (IOException e) {
            throw new NetworkErrorException();
        }
    }
}
