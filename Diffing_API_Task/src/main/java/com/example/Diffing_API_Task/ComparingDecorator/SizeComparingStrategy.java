/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.ComparingDecorator;

import com.example.Diffing_API_Task.DataObject.UserInput;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author cheungkwaikwan
 */
public class SizeComparingStrategy <T extends Optional<Iterable<UserInput>>, R extends ResponseEntity<Map<Object,Object>>>  extends ComparingStrategiesDecorator<T,R>{

    @Autowired
    public SizeComparingStrategy(ComparingStrategies next) {
        super(next);
    }
    
    @Value("${ComparingStrategies.SizeComparingStrategy.responseMessage.diffResultType}")
    public String diffResultType;
    
    @Value("${ComparingStrategies.SizeComparingStrategy.responseMessage.SizeDoNotMatch}")
    public String SizeDoNotMatch;
         
             
    @Override
    public R test(T t) {
        
        
        List<String> leftRightContentList = ComparingStrategies.getLeftRightContentList(t);
        
        if (leftRightContentList.get(0).length() != leftRightContentList.get(1).length()) {
            ResponseEntity<Map<Object,Object>> result = this.createResponseDTO();
            Map<Object, Object> mp = result.getBody();
            mp.put(diffResultType, SizeDoNotMatch);
            return (R)result;
        } else {
            return (R) next.test(t);
        }

    }

}