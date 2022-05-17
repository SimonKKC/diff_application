/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.ComparingDecorator;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author cheungkwaikwan
 */
public abstract class ComparingStrategiesDecorator <T,R> implements ComparingStrategies<T, R> {
    ComparingStrategies next; 
    
    @Autowired
    ComparingStrategiesDecorator(ComparingStrategies next){
        this.next = next;
    }
    
    public ResponseEntity<Map<Object,Object>> createResponseDTO(){
        return new ResponseEntity<>(new HashMap<Object,Object>(), HttpStatus.OK);
    }
    
    public ResponseEntity<Map<Object,Object>> createResponseDTO404(){
        return new ResponseEntity<>(new HashMap<Object,Object>(), HttpStatus.NOT_FOUND);
    }

}
