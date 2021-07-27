package com.vinicius.santos.marvelapi.model.response;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataResponse {

    private ArrayList<Object> result = new ArrayList<>();
    
    public ArrayList<Object> getResult() {
        return result;
    }

    public void setResult(ArrayList<Object> result) {
        this.result = result;
    }
}
