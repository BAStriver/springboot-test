package com.bas.config;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * After the parameter verification fails, MethodArgumentNotValidException will be thrown.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringJoiner sj = new StringJoiner(";");
        e.getBindingResult().getFieldErrors() // get all the errors
                .forEach(x -> sj.add(x.getDefaultMessage()));

        Map map = new HashMap<>();
        map.put("code", UUID.randomUUID());
        map.put("msg", sj.toString());
        return map;
    }

    /**
     * After the parameter verification fails, ConstraintViolationException will be thrown.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Map handleConstraintViolationException(ConstraintViolationException e) {
        StringJoiner sj = new StringJoiner(";");
        e.getConstraintViolations().forEach(x -> sj.add(x.getMessage()));
        Map map = new HashMap<>();
        map.put("code", 1001);
        map.put("msg", sj.toString());

        return map;
    }

}