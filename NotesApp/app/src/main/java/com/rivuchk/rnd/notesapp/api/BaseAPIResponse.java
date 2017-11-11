package com.rivuchk.rnd.notesapp.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Rivu on 11-11-2017.
 */

public class BaseAPIResponse implements Serializable {
    @SerializedName("error_code") String errorCode;
    @SerializedName("error_message") String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
