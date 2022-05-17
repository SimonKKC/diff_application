/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.RestController;

import com.example.Diffing_API_Task.BeanConfiguration.ExceptionHandler.SaveExceptionHandler;
import com.example.Diffing_API_Task.BeanConfiguration.requestDTP.Request;
import com.example.Diffing_API_Task.Repository.UserInputRepository;
import com.example.Diffing_API_Task.ComparingDecorator.ComparingStrategies;
import com.example.Diffing_API_Task.DataObject.DataFactory;
import com.example.Diffing_API_Task.DataObject.UserInput;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cheungkwaikwan
 */
@Slf4j
@RestController
@RequestMapping(path="/v1/diff",produces="application/json")
public class DiffController {
    
    
    @Autowired
    UserInputRepository uir;
       
    @Autowired
    @Qualifier("isExistComparator")
    ComparingStrategies cs;

    @Autowired
    @Qualifier("UserInputFactory")
    DataFactory df;
    
    
    @GetMapping("/healthCheck")
    public ResponseEntity<String> healthCheck() {
         return ResponseEntity
        .status(HttpStatus.OK)
        .body("Hello World");
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<Object,Object>> getTestByID(@PathVariable("id") String id) {
        Optional<List<UserInput>> userInput = uir.findByID(id);
        return (ResponseEntity<Map<Object,Object>>)cs.test(userInput);
    }

    @PutMapping("/{id}/left")
    @ResponseStatus(HttpStatus.CREATED)
    public void putLeft(@PathVariable("id") String id, @Valid  @RequestBody Request req){    
       uir.save(df.createUserInputLeft(id, req.getData()));
    }
    
    @PutMapping("/{id}/right")
    @ResponseStatus(HttpStatus.CREATED)
    public void putRight(@PathVariable("id") String id, @Valid  @RequestBody Request req){
        uir.save(df.createUserInputRight(id, req.getData()));
    }
    
    
    
  @ExceptionHandler(SaveExceptionHandler.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleSaveException(
      SaveExceptionHandler exception
  ) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleValidationException(
      Exception exception
  ) {  
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }
  
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleNAllException(
      Exception exception
  ) {  
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }
  
  
  
    
}
