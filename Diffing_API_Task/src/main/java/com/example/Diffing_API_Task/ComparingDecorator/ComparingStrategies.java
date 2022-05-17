/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.ComparingDecorator;

import com.example.Diffing_API_Task.DataObject.UserInput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author cheungkwaikwan
 */
public interface ComparingStrategies<T,R> {
   R test(T t);
   
   static List<UserInput> convertIterableToList(Optional<Iterable<UserInput>> t){
        Iterable<UserInput> ite = t.get();
        List<UserInput> userList = new ArrayList<>();
        ite.forEach(userList::add);
        return userList;
   }
   
      static List<String> getLeftRightContentList(Optional<Iterable<UserInput>> t){
        Iterable<UserInput> ite = t.get();
        List<String> result = new ArrayList();
        ite.forEach((s)->{
            result.add(s.getContent());
        });
        return result;
   }
       
}
