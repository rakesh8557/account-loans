package com.guts.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class ErrorMessageDTO {
    private String path;
    private HttpStatus status;
    private String message;
    private LocalDateTime time;

    public ErrorMessageDTO(String path, HttpStatus status, String message, LocalDateTime time) {
        this.path = path;
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public ErrorMessageDTO() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ErrorMessageDTO{" +
                "path='" + path + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
