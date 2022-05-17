/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.DataObject;

/**
 *
 * @author cheungkwaikwan
 */
public class UserInputFactory implements DataFactory{

    @Override
    public UserInput createUserInputLeft(String id, String content) {
       return new UserInput(id,UserInput.DirectionType.LEFT.getType(),content);
    }
 
    @Override
    public UserInput createUserInputRight(String id, String content) {
       return new UserInput(id,UserInput.DirectionType.RIGHT.getType(),content);
    }

    @Override
    public UserInput createUserInput(String id, String type, String content) {
        return new UserInput(id,type,content);
    }
    
}
