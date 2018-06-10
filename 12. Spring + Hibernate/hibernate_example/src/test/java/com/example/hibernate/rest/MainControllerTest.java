package com.example.hibernate.rest;

import com.example.hibernate.entity.Clients;
import com.example.hibernate.entity.Groups;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.text.MessageFormat;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    private Clients clients;

    private Groups groups;

    @Before
    public void setUp() throws Exception {
        clients = new Clients("adle", "abc@abc.com", "test");

    }

    @After
    public void tearDown() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/client/all"));
        mvc.perform(MockMvcRequestBuilders.delete("/group/all"));
    }

    @Test
    public void addClient() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/client/add")
                .param("login", clients.getLogin())
                .param("email", clients.getEmail())
                .param("passwd", clients.getPasswd())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addClientDuplicate() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/client/add")
                .param("login", clients.getLogin())
                .param("email", clients.getEmail())
                .param("passwd", clients.getPasswd())
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request);
    }

    @Test
    public void addClientMissingData() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/client/add")
                .param("login", clients.getLogin())
                .param("passwd", clients.getPasswd())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void getClient() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/client/adle")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /*@Test
    public void addClientToGroup() throws Exception {
        Groups ug = new Groups("ugTest");

        mvc.perform(MockMvcRequestBuilders.post("/client/add")
                .param("login", clients.getLogin())
                .param("email", clients.getEmail())
                .param("passwd", clients.getPasswd())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/group/add")
                .param("name", ug.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post(MessageFormat.format("/group/{0}/add/{1}", ug.getName(), clients.getLogin()))
                .param("name", ug.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/


}