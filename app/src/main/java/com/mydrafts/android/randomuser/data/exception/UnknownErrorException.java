package com.mydrafts.android.randomuser.data.exception;

public class UnknownErrorException extends ApiClientException {

  private final int code;

  public UnknownErrorException(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}