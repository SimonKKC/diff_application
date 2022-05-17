/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.ComparingDecorator;

import com.example.Diffing_API_Task.DataObject.UserInput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author cheungkwaikwan
 */
public class FindDifferentComparingStrategy<T extends Optional<Iterable<UserInput>>, R extends ResponseEntity<Map<Object,Object>>> implements ComparingStrategies<T, R> {

    
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
               
    
    @Override
    public R test(T t) {
       
        
        
        List<String> userList = ComparingStrategies.getLeftRightContentList(t);
        String[] leftContentArray = userList.get(0).split("");
        String[] rightContentArray = userList.get(1).split("");
        
        Map<Integer,Integer> map = new HashMap();
        int startIndex = -1;       
        for(int i = 0; i < leftContentArray.length ; i++){
            if(!leftContentArray[i].equals(rightContentArray[i])){
                if(startIndex == -1){
                    startIndex = i;
                }
                if(map.containsKey(startIndex)){
                    map.put(startIndex, map.get(startIndex) + 1);
                }else{
                    map.put(startIndex, 1);
                }
            }else{
                startIndex = -1;
            }
        }

        Iterator it = map.keySet().iterator();
        ResponseEntity<Map<Object,Object>> result = this.createResponseDTO();
        Map<Object,Object> resultMap = result.getBody();
        List<Map<Object,Object>> diffList = (List<Map<Object,Object>>)resultMap.get(diffs);
                
        while(it.hasNext()){
            Integer key = (Integer)it.next();
            Map<Object,Object> tmpResultMap = new HashMap();
            tmpResultMap.put(offset,String.valueOf(key) );
            tmpResultMap.put(length,String.valueOf(map.get(key)));
            diffList.add(tmpResultMap);
        }
        
        resultMap.put(diffs, diffList);
        return (R)result;
    }
    
    
    private ResponseEntity<Map<Object,Object>> createResponseDTO(){
        List<Map<Object,Object>> list = new LinkedList();
        Map<Object,Object> resultMap = new HashMap<>();
        resultMap.put(diffs,list);
        resultMap.put(diffResultType, ContentDoNotMatch);
        ResponseEntity<Map<Object,Object>> result = new ResponseEntity<>(resultMap, HttpStatus.OK);
        return result;
    }

}
