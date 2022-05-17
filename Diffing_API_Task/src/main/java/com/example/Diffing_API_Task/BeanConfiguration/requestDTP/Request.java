/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.BeanConfiguration.requestDTP;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author cheungkwaikwan
 */
@Data
@RequiredArgsConstructor
public class Request {
   @NotNull(message="")
   @Size(min=1,max=4000, message="fuck your mother")
   private String data;
   
}
