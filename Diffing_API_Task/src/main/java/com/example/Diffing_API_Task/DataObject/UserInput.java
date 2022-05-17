/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.DataObject;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author cheungkwaikwan
 */
@Data
@RequiredArgsConstructor
public class UserInput{

    @NotBlank(message = "${UserInput.Validator.message.idNotBlank}")
    @Size(max = 18, min = 1, message = "${UserInput.Validator.message.idSize}")
    private final String id;
 
    @NotBlank(message = "${UserInput.Validator.message.typeNotBlank}")
    @Size(max = 1, min = 1, message = "${UserInput.Validator.message.typeSize}")
    private final String type;

    @NotBlank(message = "${UserInput.Validator.message.contentNotBlank}")
    @Size(max = 4000, min = 1, message = "${UserInput.Validator.message.contentSize}")    
    private final String content;

    public static enum DirectionType {
        LEFT("L","LEFT"), RIGHT("R","RIGHT");
        
        private String type;
        private String name;
        DirectionType(String t,String name){
            this.type=t;
            this.name= name;
        }
        
        public String getType(){
            return this.type;
        }
        
        public String getName(){
            return this.name;
        }     
        
        
    }
    
    @Override
    public boolean equals(Object a){
        UserInput tmp = (UserInput)a;
        if(this.content.equals(tmp.content) && this.id.equals(tmp.id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.content);
        return hash;
    }

}
