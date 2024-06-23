package com.javaweb.ControllerAdvice;

import com.javaweb.model.ErrorHandleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLException.class)
    private ResponseEntity<Object> SQLExceptionHandle(SQLException ex, WebRequest request) {
        ErrorHandleDTO errorHandleDTO = new ErrorHandleDTO();
        errorHandleDTO.setError(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Lỗi cú pháp SQL");
        errorHandleDTO.setDetails(details);
        return new ResponseEntity<>(errorHandleDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    private ResponseEntity<Object> ClassNotFoundExceptionHandle(ClassNotFoundException ex, WebRequest request) {
        ErrorHandleDTO errorHandleDTO = new ErrorHandleDTO();
        errorHandleDTO.setError(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Lỗi Driver SQL");
        errorHandleDTO.setDetails(details);
        return new ResponseEntity<>(errorHandleDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    private ResponseEntity<Object> NumberFormatExceptionHandle(NumberFormatException ex, WebRequest request) {
        ErrorHandleDTO errorHandleDTO = new ErrorHandleDTO();
        errorHandleDTO.setError(ex.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Lỗi data đầu vào không phải là số !");
        errorHandleDTO.setDetails(details);
        return new ResponseEntity<>(errorHandleDTO, HttpStatus.BAD_REQUEST);
    }
}
