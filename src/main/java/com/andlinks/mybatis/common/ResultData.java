package com.andlinks.mybatis.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by 陈亚兰 on 2017/11/28.
 */

public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = -9170965321344627730L;

    public static final String SUCCESS_MESSAGE="success";
    public static final String ERROR_MESSAGE="failure";
    public static final int SUCCESS_CODE=200;
    public static final int ERROR_CODE=400;

    private String message;
    private int code;
    private T result;

    public static <T> ResultData<T> success(T t){
        ResultData<T> resultData=new ResultData<>();
        resultData.setMessage(SUCCESS_MESSAGE);
        resultData.setCode(SUCCESS_CODE);
        resultData.setResult(t);
        return resultData;
    }

    public static <T> ResultData<T> error(T t){
        ResultData<T> resultData=new ResultData<>();
        resultData.setMessage(ERROR_MESSAGE);
        resultData.setCode(ERROR_CODE);
        resultData.setResult(t);
        return resultData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
