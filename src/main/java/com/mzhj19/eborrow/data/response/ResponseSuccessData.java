package com.mzhj19.eborrow.data.response;

import com.mzhj19.eborrow.constant.ResponseMessageConstants;

public class ResponseSuccessData<T> extends ResponseBaseData<T> {

    public ResponseSuccessData(T data) {
        super(true, 1, ResponseMessageConstants.SUCCESS, data);
    }

    public ResponseSuccessData(String message, T data) {
        super(true, 1, message, data);
    }

}
