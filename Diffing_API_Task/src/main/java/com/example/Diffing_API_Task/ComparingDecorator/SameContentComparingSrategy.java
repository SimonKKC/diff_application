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
public class SameContentComparingSrategy <T extends Optional<Iterable<UserInput>>, R extends ResponseEntity<Map<Object,Object>>> extends ComparingStrategiesDecorator<T,R>{

    public SameContentComparingSrategy(@Autowired ComparingStrategies next) {
        super(next);
    }
    
    @Value("${ComparingStrategies.SameContentComparingSrategy.responseMessage.diffResultType}")
    public String diffResultType;
    
    @Value("${ComparingStrategies.SameContentComparingSrategy.responseMessage.Equals}")
    public String Equals;
    
    
    @Override
    public R test(T t) {
        
        
        List<UserInput> userList = ComparingStrategies.convertIterableToList(t);
        
        
        
        if (userList.get(0).equals(userList.get(1))) {
            ResponseEntity<Map<Object,Object>> result = this.createResponseDTO();
            Map<Object, Object> mp = result.getBody();
            mp.put(diffResultType, Equals);
            return (R)result;
        } else {
            return (R) next.test(t);
        }

    }

}
