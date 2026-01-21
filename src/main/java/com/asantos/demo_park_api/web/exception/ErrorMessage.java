package com.asantos.demo_park_api.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ErrorMessage {

    private String path;
    private String method;
    private Integer status;
    private String statusText;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;



    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result){
        this.message = message;
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.statusText = status.getReasonPhrase();
        this.status = status.value();
        addErrors(result);
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus httpStatus, String message) {
        this.message = message;
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = httpStatus.value();
        this.statusText = httpStatus.getReasonPhrase();
    }

    private void addErrors(BindingResult result) {
        this.errors = new HashMap();
        for (FieldError fieldError: result.getFieldErrors()){
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}

