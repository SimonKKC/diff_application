/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.Repository;

import com.example.Diffing_API_Task.DataObject.UserInput;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author cheungkwaikwan
 */

public interface UserInputRepository {
    Optional<UserInput> findByIDAndLeftRight(String id, String type);
    Optional<List<UserInput>> findByID(String id);
    UserInput save(UserInput n);
}
