package com.example.project.response;

import lombok.Getter;
import lombok.Setter;

public class Response {
    @Setter
    @Getter
    private String message;

    public Response() {}

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
