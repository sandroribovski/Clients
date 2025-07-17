package com.cadastro.sistema.dto;

public class FieldMessage {

    private String FieldMessage;
    private String message;

    public FieldMessage(String fieldMessage, String message) {
        FieldMessage = fieldMessage;
        this.message = message;
    }

    public String getFieldMessage() {
        return FieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        FieldMessage = fieldMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
