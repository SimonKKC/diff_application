/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.ComparingDecorator;

import com.example.Diffing_API_Task.DataObject.UserInput;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


/**
 *
 * @author cheungkwaikwan
 */
public class IsExistComparingStrategy <T extends Optional<Iterable<UserInput>>, R extends ResponseEntity<Map<Object,Object>>>  extends ComparingStrategiesDecorator<T,R>{

    @Autowired
    public IsExistComparingStrategy(ComparingStrategies next) {
        super(next);
    }
    
    @Override
    public R test(T t) {
        if(!t.isPresent()){
            ResponseEntity<Map<Object,Object>> result = this.createResponseDTO404();
            return (R)result;
        }else{
            return (R)next.test(t);
        }
        
    }

}