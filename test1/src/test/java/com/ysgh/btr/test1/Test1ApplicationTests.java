package com.ysgh.btr.test1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysgh.btr.test1.dao.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1ApplicationTests {

    @Autowired
    UserDao userDao;

    MockMvc mvc;
    MockHttpSession session;
    @Autowired
    WebApplicationContext webApplicationContext;

    String expectedIson;

    @Before
    public void setUp() throws JsonProcessingException{
        expectedIson = Obj2Json(userDao.selectByPrimaryKey(1));
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String Obj2Json(Object obj) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
    @Test
    public void contextLoads() throws Exception{
        String uri = "/user?userId=1";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("Error",200,status);
        Assert.assertEquals("Error",expectedIson.substring(0,10),content.substring(0,10));
    }

}
