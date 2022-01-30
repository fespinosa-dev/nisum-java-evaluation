package com.nisum.evaluation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.evaluation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected ObjectMapper objectMapper;


}
