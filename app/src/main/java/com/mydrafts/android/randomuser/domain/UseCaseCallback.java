package com.mydrafts.android.randomuser.domain;

public interface UseCaseCallback<T> {

    enum Error {
        NETWORK, UNKNOWN;

        public String message() {
            return this == NETWORK ? "Check your network please" : "Oops, something went wrong :/";
        }
    }

    void onSuccess(T t);

    void onError(Error e);
}