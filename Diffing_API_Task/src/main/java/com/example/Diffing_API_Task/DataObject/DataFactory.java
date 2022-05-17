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
public interface DataFactory {
   UserInput createUserInputLeft(String id , String content);
   UserInput createUserInputRight(String id, String content);
   UserInput createUserInput(String id, String type ,String content);
}
