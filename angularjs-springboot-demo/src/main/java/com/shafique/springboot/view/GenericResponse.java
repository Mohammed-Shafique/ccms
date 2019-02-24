package com.shafique.springboot.view;

/**
 * @author mohammedshafique
 * @param <T>
 */
public class GenericResponse<T> {
    private String messageSuccess;
    private String messageError;
    private String messageInfo;
    private T entiry;

    public GenericResponse(){}

    public String getMessageSuccess() {
        return messageSuccess;
    }

    public void setMessageSuccess(String messageSuccess) {
        this.messageSuccess = messageSuccess;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public T getEntiry() {
        return entiry;
    }

    public void setEntiry(T entiry) {
        this.entiry = entiry;
    }
}
