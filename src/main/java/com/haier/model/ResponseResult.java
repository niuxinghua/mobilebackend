package com.haier.model;

public class ResponseResult<T> {
    public int code;
    public String message;
    public T result;
}
