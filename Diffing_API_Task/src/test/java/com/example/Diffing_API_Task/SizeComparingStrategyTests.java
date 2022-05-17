/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task;

import com.example.Diffing_API_Task.ComparingDecorator.ComparingStrategies;
import com.example.Diffing_API_Task.DataObject.DataFactory;
import com.example.Diffing_API_Task.DataObject.UserInput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author cheungkwaikwan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DiffingApiTaskApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class SizeComparingStrategyTests {
    
    @Autowired
    @Qualifier("sizeComparator")
    ComparingStrategies sg;
    
    @Autowired
    @Qualifier("UserInputFactory")
    DataFactory df;
 
    @Value("${ComparingStrategies.SizeComparingStrategy.responseMessage.diffResultType}")
    public String diffResultType;
    
    @Value("${ComparingStrategies.SizeComparingStrategy.responseMessage.SizeDoNotMatch}")
    public String SizeDoNotMatch;


    @Value("${mock.test.result.behavior}")
    public String mockTestResultBehavior;

    
    @Test
    public void test1IfBehavior(){
        List<UserInput> ui = new ArrayList();
        ui.add(df.createUserInput("1", "L", "AAAAAA=="));
        ui.add(df.createUserInput("1", "R", "AAAA=="));
        ResponseEntity<Map<Object,Object>> result = (ResponseEntity<Map<Object,Object>>) sg.test(Optional.ofNullable(ui)); 
        assertTrue(result.getBody().get(diffResultType).toString().equals(SizeDoNotMatch));
    }
    
    @Test
    public void test2ElseBehavior(){
        List<UserInput> ui = new ArrayList();
        ui.add(df.createUserInput("1", "L", "AAAAAA=="));
        ui.add(df.createUserInput("1", "R", "AAAAAA=="));
        ResponseEntity<Map<Object,Object>> result = (ResponseEntity<Map<Object,Object>>) sg.test(Optional.ofNullable(ui)); 
        assertTrue(result.getBody().get(mockTestResultBehavior).toString().equals(mockTestResultBehavior));
    }
    
    
}
