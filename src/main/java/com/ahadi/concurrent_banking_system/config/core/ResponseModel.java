package com.ahadi.concurrent_banking_system.config.core;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@Component
public class ResponseModel {


    private HttpStatus status;
    private Long timestamp;
    private String message;
    private String traceId;
    private String debugMessage;
    private List<ApiSubError> subErrors;
    private List<Object> data;



    public ResponseModel() {
        timestamp = new Date().getTime();
    }

    public ResponseModel(HttpStatus status) {
        this();
        this.status = status;
    }

    public ResponseModel(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ResponseModel(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ResponseModel(List<Object> data) {
        this();
        this.status = HttpStatus.OK;
        this.message = "عملیات با موفقیت انجام شد";
        this.data = data;
    }

    public ResponseModel(Object data) {
        this();
        List<Object> dataList = new ArrayList<>();
        dataList.add(data);
        this.status = HttpStatus.OK;
        this.message = "عملیات با موفقیت انجام شد";
        this.data = dataList;
    }
}
