/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.BeanConfiguration;

import com.example.Diffing_API_Task.ComparingDecorator.IsExistComparingStrategy;
import com.example.Diffing_API_Task.ComparingDecorator.ComparingStrategies;
import com.example.Diffing_API_Task.ComparingDecorator.FindDifferentComparingStrategy;
import com.example.Diffing_API_Task.ComparingDecorator.IsExistBothSidesComparingStrategy;
import com.example.Diffing_API_Task.ComparingDecorator.MockComparator;
import com.example.Diffing_API_Task.ComparingDecorator.SizeComparingStrategy;
import com.example.Diffing_API_Task.ComparingDecorator.SameContentComparingSrategy;
import com.example.Diffing_API_Task.DataObject.DataFactory;
import com.example.Diffing_API_Task.DataObject.UserInputFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author cheungkwaikwan
 */
@Configuration
public class ComparingStrategiesBeanConfiguration {
  
    @Bean("UserInputFactory")
    public DataFactory getDataFactory(){
        return  new UserInputFactory();
    }  
    
    @Bean("findDifferentComparator")
    @Profile("!test")
    public ComparingStrategies getFindDifferentComparator(){
        return new FindDifferentComparingStrategy();
    }      
    
    @Bean("sizeComparator")
    @Profile("!test")
    public ComparingStrategies getSizeComparator(@Autowired @Qualifier("findDifferentComparator") ComparingStrategies r){
        return new SizeComparingStrategy(r);
    }    
    
             
    @Bean("sameContentComparator")
    @Profile("!test")
    public ComparingStrategies getSameContentComparator(@Autowired @Qualifier("sizeComparator") ComparingStrategies r){
        return new SameContentComparingSrategy(r);
    }   
 
    
    @Bean("IsExistBothSidesComparator")
    @Profile("!test")
    public ComparingStrategies getIsExistBothSidesComparator(@Autowired @Qualifier("sameContentComparator") ComparingStrategies r){
        return new IsExistBothSidesComparingStrategy(r);
    }  
    
    @Bean("isExistComparator")
    @Profile("!test")
    public ComparingStrategies getIsExistComparator(@Autowired @Qualifier("IsExistBothSidesComparator") ComparingStrategies r){
        return new IsExistComparingStrategy(r);
    } 
    
    
   
    @Bean("mockComparator")
    @Profile("test")
    public ComparingStrategies getMockComparator(){
        return new MockComparator(null);
    }   
    
       
    @Bean("sizeComparator")
    @Profile("test")
    public ComparingStrategies getSizeComparatorWithMock(@Autowired @Qualifier("mockComparator") ComparingStrategies r){
        return new SizeComparingStrategy(r);
    }    
    
             
    @Bean("sameContentComparator")
    @Profile("test")
    public ComparingStrategies getSameContentComparatorWithMock(@Autowired @Qualifier("mockComparator") ComparingStrategies r){
        return new SameContentComparingSrategy(r);
    }   
 
    
    @Bean("IsExistBothSidesComparator")
    @Profile("test")
    public ComparingStrategies getIsExistBothSidesComparatorWithMock(@Autowired @Qualifier("mockComparator") ComparingStrategies r){
        return new IsExistBothSidesComparingStrategy(r);
    }  
    
    @Bean("isExistComparator")
    @Profile("test")
    public ComparingStrategies getIsExistComparatorWithMock(@Autowired @Qualifier("mockComparator") ComparingStrategies r){
        return new IsExistComparingStrategy(r);
    }   
    
            
}
