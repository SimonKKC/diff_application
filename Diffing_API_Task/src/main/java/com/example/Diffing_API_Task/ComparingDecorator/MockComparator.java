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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author cheungkwaikwan
 */
public class MockComparator<T extends Optional<Iterable<UserInput>>, R extends ResponseEntity<Map<Object, Object>>> extends ComparingStrategiesDecorator<T, R> {

    @Autowired
    public MockComparator(ComparingStrategies next) {
        super(next);
    }
    
    @Value("${mock.test.result.behavior}")
    public String mockTestResultBehavior;

    @Override
    public R test(T t) {
        ResponseEntity<Map<Object, Object>> result = this.createResponseDTO();
        Map<Object, Object> mp = result.getBody();
        mp.put(mockTestResultBehavior, mockTestResultBehavior);
        return (R)result;
    }

}
