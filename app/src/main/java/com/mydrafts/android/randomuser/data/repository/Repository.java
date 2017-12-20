package com.mydrafts.android.randomuser.data.repository;

import com.mydrafts.android.randomuser.data.entity.User;

import java.util.List;

import io.reactivex.Observable;

public interface Repository<T> {

  Observable<List<User>> getUsers();
}