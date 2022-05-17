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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
public class FindDifferentComparingStrategyTests {
    
    @Autowired
    @Qualifier("findDifferentComparator")
    ComparingStrategies sg;
    
    @Autowired
    @Qualifier("UserInputFactory")
    DataFactory df;
 
    
    
    @Value("${ComparingStrategies.FindDifferentComparingStrategy.responseMessage.diffResultType}")
    public String diffResultType;
    
    @Value("${ComparingStrategies.FindDifferentComparingStrategy.responseMessage.ContentDoNotMatch}")
    public String ContentDoNotMatch;

    
    @Value("${ComparingStrategies.FindDifferentComparingStrategy.responseMessage.offset}")
    public String offset;
    
    @Value("${ComparingStrategies.FindDifferentComparingStrategy.responseMessage.length}")
    public String length;
     
    
    @Value("${ComparingStrategies.FindDifferentComparingStrategy.responseMessage.diffs}")
    public String diffs;
    

    
    @Test
    public void test1IfBehavior(){
        List<UserInput> ui = new ArrayList();
        ui.add(df.createUserInput("1", "L", "AAAAAA=="));
        ui.add(df.createUserInput("1", "R", "AQABAQ=="));
        ResponseEntity<Map<Object,Object>> result = (ResponseEntity<Map<Object,Object>>) sg.test(Optional.ofNullable(ui));

        assertTrue(result.getBody().get(diffResultType).toString().equals(ContentDoNotMatch));
        
        List<Map<Object,Object>> ls = (List<Map<Object,Object>>) result.getBody().get(diffs);
        Map<String,String> tmpResult = new HashMap();
        for(Map<Object,Object> m : ls){
            tmpResult.put((String)m.get(offset), (String)m.get(length));
        }
        
        assertTrue(tmpResult.containsKey("1") && tmpResult.get("1").equals("1"));
        assertTrue(tmpResult.containsKey("3") && tmpResult.get("3").equals("1"));
        assertTrue(tmpResult.containsKey("5") && tmpResult.get("5").equals("1"));
        
    }

    
    
}
