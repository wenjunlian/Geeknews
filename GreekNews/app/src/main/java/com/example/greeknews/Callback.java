package com.example.greeknews;

public interface Callback<T> {
    void onSuccess(T bean);
    void onFail(String msg);
}
