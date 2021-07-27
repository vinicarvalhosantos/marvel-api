package com.vinicius.santos.marvelapi.model.response;

import org.springframework.stereotype.Component;

@Component
public class ResponseError {

    private int code;
    private Object status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
}
