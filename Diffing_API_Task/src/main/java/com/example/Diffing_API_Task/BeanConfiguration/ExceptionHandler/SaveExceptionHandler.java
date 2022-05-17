/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.BeanConfiguration.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author cheungkwaikwan
 */
public class SaveExceptionHandler extends ResponseStatusException {

  public SaveExceptionHandler(String message){
    super(HttpStatus.NOT_FOUND, message);
  }
  
}
