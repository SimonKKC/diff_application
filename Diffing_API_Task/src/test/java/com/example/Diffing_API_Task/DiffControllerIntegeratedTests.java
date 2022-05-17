/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Diffing_API_Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 *
 * @author cheungkwaikwan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = {DiffingApiTaskApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DiffControllerIntegeratedTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void beforeAll() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void testSystemStatus() throws Exception {
        mockMvc.perform(get("/v1/diff/healthCheck")).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testgetTestByIDWithoutLeftRightContent() throws Exception {
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isNotFound());
    }

    @Test
    @Order(3)
    public void testPutLeft() throws Exception {
        String content = "{\"data\":\"AAA\"}";
        mockMvc.perform(put("/v1/diff/1/left").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
    }

    @Test
    @Order(4)
    public void testPutRight() throws Exception {
        String content = "{\"data\":\"AAA\"}";
        mockMvc.perform(put("/v1/diff/1/right").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
    }

    @Test
    @Order(5)
    public void testGetTestByIDWithProperData() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        String content = "{\"data\":\"AAAAAA==\"}";
        mockMvc.perform(put("/v1/diff/1/left").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(put("/v1/diff/1/right").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isOk()).andExpect(content().string(containsString("\"diffResultType\":\"Equals\"")));

    }

    @Test
    @Order(6)
    public void testGetTestByIDWithProperData2() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        String content = "{\"data\":\"AAAAAA==\"}";
        mockMvc.perform(put("/v1/diff/1/left").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(put("/v1/diff/1/right").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isOk()).andExpect(content().string(containsString("\"diffResultType\":\"Equals\"")));
        content = "{\"data\":\"AQABAQ==\"}";
        mockMvc.perform(put("/v1/diff/1/right").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"diffResultType\":\"ContentDoNotMatch\"")))
                .andExpect(content().string(containsString("\"offset\":\"1\"")))
                .andExpect(content().string(containsString("\"length\":\"1\"")))
                .andExpect(content().string(containsString("\"offset\":\"3\"")))
                .andExpect(content().string(containsString("\"length\":\"1\"")))
                .andExpect(content().string(containsString("\"offset\":\"5\"")))
                .andExpect(content().string(containsString("\"length\":\"1\"")));
    }

    @Test
    @Order(7)
    public void testGetTestByIDWithProperData3() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        String content = "{\"data\":\"AAAAAA==\"}";
        mockMvc.perform(put("/v1/diff/1/left").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(put("/v1/diff/1/right").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isOk()).andExpect(content().string(containsString("\"diffResultType\":\"Equals\"")));
        content = "{\"data\":\"AQABAQ==\"}";
        mockMvc.perform(put("/v1/diff/1/right").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"diffResultType\":\"ContentDoNotMatch\"")))
                .andExpect(content().string(containsString("\"offset\":\"1\"")))
                .andExpect(content().string(containsString("\"length\":\"1\"")))
                .andExpect(content().string(containsString("\"offset\":\"3\"")))
                .andExpect(content().string(containsString("\"length\":\"1\"")))
                .andExpect(content().string(containsString("\"offset\":\"5\"")))
                .andExpect(content().string(containsString("\"length\":\"1\"")));
        content = "{\"data\":\"AAA==\"}";
        mockMvc.perform(put("/v1/diff/1/left").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isOk()).andExpect(content().string(containsString("\"diffResultType\":\"SizeDoNotMatch\"")));
    }

    
    @Test
    @Order(8)
    public void testGetTestByIDWithProperData4() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        String content = "{\"data\":\"AAAAAA==\"}";
        mockMvc.perform(put("/v1/diff/1/left").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(put("/v1/diff/1/right").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isOk()).andExpect(content().string(containsString("\"diffResultType\":\"Equals\"")));
        content = "{\"data\":\"AQABAQ==\"}";
        mockMvc.perform(put("/v1/diff/1/right").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"diffResultType\":\"ContentDoNotMatch\"")))
                .andExpect(content().string(containsString("\"offset\":\"1\"")))
                .andExpect(content().string(containsString("\"length\":\"1\"")))
                .andExpect(content().string(containsString("\"offset\":\"3\"")))
                .andExpect(content().string(containsString("\"length\":\"1\"")))
                .andExpect(content().string(containsString("\"offset\":\"5\"")))
                .andExpect(content().string(containsString("\"length\":\"1\"")));
        content = "{\"data\":\"AAA==\"}";
        mockMvc.perform(put("/v1/diff/1/left").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
        mockMvc.perform(get("/v1/diff/1")).andExpect(status().isOk()).andExpect(content().string(containsString("\"diffResultType\":\"SizeDoNotMatch\"")));
        content = "{\"data\":null}";
        mockMvc.perform(put("/v1/diff/1/left").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isBadRequest());
    }    
    
}
