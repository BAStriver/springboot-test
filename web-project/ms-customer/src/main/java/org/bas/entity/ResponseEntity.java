package org.bas.entity;

import lombok.Data;

@Data
public class ResponseEntity<T> {

    private int status;
    private boolean success;
    private T message;

    public ResponseEntity(int status, boolean success, T message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }

    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<>(200, true, null);
    }

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(200, true, data);
    }

    public static <T> ResponseEntity<T> failure(int status) {
        return new ResponseEntity<>(status, false, null);
    }

    public static <T> ResponseEntity<T> failure(int status, T data) {
        return new ResponseEntity<>(status, false, data);
    }
}
