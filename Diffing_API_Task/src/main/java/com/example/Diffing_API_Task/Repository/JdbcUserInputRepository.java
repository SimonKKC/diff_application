/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task.Repository;

import com.example.Diffing_API_Task.BeanConfiguration.ExceptionHandler.SaveExceptionHandler;
import com.example.Diffing_API_Task.DataObject.DataFactory;
import com.example.Diffing_API_Task.DataObject.UserInput;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cheungkwaikwan
 */
@Repository
public class JdbcUserInputRepository implements UserInputRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Value("${jdbcUserInputRepository.findByIDAndLeftRight.sql}")
    private String findByIDAndLeftRightSql;

     @Value("${jdbcUserInputRepository.findByID.sql}")
    private String findByIDSql;   

    @Value("${jdbcUserInputRepository.save.updateSql}")
    private String updateSql;   

    @Value("${jdbcUserInputRepository.save.insertSql}")
    private String insertSql;   
    
    @Value("${jdbcUserInputRepository.save.exceptionalMessage}")
    private String exceptionalMessage;   

    @Value("${jdbcUserInputRepository.mapRowToUserInpur.column.id}")
    private String column_id;     
   
    @Value("${jdbcUserInputRepository.mapRowToUserInpur.column.type}")
    private String column_type;     
    
    @Value("${jdbcUserInputRepository.mapRowToUserInpur.column.content}")
    private String column_content;     
    
    @Autowired
    @Qualifier("UserInputFactory")
    DataFactory df;
    
    
    @Override
    public Optional<UserInput> findByIDAndLeftRight(String id, String type) {
        Optional<UserInput> rst;
        try {
            UserInput ui = jdbc.queryForObject(
                    findByIDAndLeftRightSql,
                    this::mapRowToUserInpur, id, type);
            rst = Optional.ofNullable(ui);

        } catch (EmptyResultDataAccessException e) {
            rst = Optional.ofNullable(null);
        }
        return rst;
    }

    @Override
    public Optional<List<UserInput>> findByID(String id) {
        Optional<List<UserInput>> rst;
        try {
            List<UserInput> ui = jdbc.query(
                    findByIDSql,
                    this::mapRowToUserInpur, id);
            
            if (ui.isEmpty()) {
                rst = Optional.ofNullable(null);
            } else {
                rst = Optional.ofNullable(ui);
            }
            
        } catch (EmptyResultDataAccessException e) {
            rst = Optional.ofNullable(null);
        }
        return rst;
    }

    @Override
    @Transactional
    public UserInput save(UserInput n) {
        
        try {
            Optional<UserInput> uii = this.findByIDAndLeftRight(n.getId(), n.getType());
            if (uii.isPresent()) {
                jdbc.update(
                        updateSql,
                        n.getContent(),
                        n.getId(),
                        n.getType());

            } else {
                jdbc.update(
                        insertSql,
                        n.getId(),
                        n.getType(),
                        n.getContent());
            }
            return n;
        } catch (Exception e) {
            throw new SaveExceptionHandler(exceptionalMessage);
        }
    }

    
    private UserInput mapRowToUserInpur(ResultSet rs, int rowNum)
            throws SQLException {
        return df.createUserInput( rs.getString(column_id),
                rs.getString(column_type),
                rs.getString(column_content));
    }

}
