package com.example.ataaspringbootangular.exception.advice;

import com.example.ataaspringbootangular.exception.except.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String ,String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String , String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());

        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UtilisateurFoundException.class)
    public Map<String , String> handleBusinessException(UtilisateurFoundException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MemberFoundException.class)
    public Map<String , String> handleBusinessException(MemberFoundException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmailDejaExisteException.class)
    public Map<String , String> handleEmailException(EmailDejaExisteException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NbrSerieDejaExisteException.class)
    public Map<String , String> handleEmailException(NbrSerieDejaExisteException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AssociationFoundException.class)
    public Map<String , String> handleBusinessException(AssociationFoundException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BiensEssentielFoundException.class)
    public Map<String , String> handleBusinessException(BiensEssentielFoundException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DowarFoundException.class)
    public Map<String , String> handleBusinessException(DowarFoundException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(VilleFoundException.class)
    public Map<String , String> handleBusinessException(VilleFoundException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(KafilaFoundException.class)
    public Map<String , String> handleBusinessException(KafilaFoundException ex){
        Map<String , String> errorMap = new HashMap<>();
        errorMap.put("errorMessage ",ex.getMessage());
        return errorMap;
    }

}
