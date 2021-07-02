package fr.dauphine.miageif.tauxchange.TauxChange.Controller;

import java.io.IOException;
import java.math.BigDecimal;

import fr.dauphine.miageif.tauxchange.TauxChange.Model.TauxChange;
import fr.dauphine.miageif.tauxchange.TauxChange.TauxChangeApplication;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TauxChangeApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
public class ChangeControllerTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    @Order(1)
    public void getAllTauxChange() throws Exception {
        String uri = "/taux-change";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        TauxChange[] tauxChanges = mapFromJson(content, TauxChange[].class);
        assertTrue(tauxChanges.length > 0);
    }

    @Test
    @Order(2)
    public void getTauxChangeBySource() throws Exception {
        String uri = "/taux-change/source/EUR";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        TauxChange[] tauxChanges = mapFromJson(content, TauxChange[].class);
        assertEquals(10, tauxChanges.length);
    }

    @Test
    @Order(3)
    public void getTauxChangeByDest() throws Exception {
        String uri = "/taux-change/dest/JPY";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        TauxChange[] tauxChanges = mapFromJson(content, TauxChange[].class);
        assertEquals(5, tauxChanges.length);
    }

    @Test
    @Order(4)
    public void getTauxChangeByDate() throws Exception {
        String uri = "/taux-change/date/2021-06-23";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        TauxChange[] tauxChanges = mapFromJson(content, TauxChange[].class);
        assertEquals(3, tauxChanges.length);
    }

    @Test
    @Order(5)
    public void getTauxChangeBySourceAndDest() throws Exception {
        String uri = "/taux-change/source/EUR/dest/USD";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        TauxChange[] tauxChanges = mapFromJson(content, TauxChange[].class);
        assertEquals(5, tauxChanges.length);
    }

    @Test
    @Order(6)
    public void createTauxChange() throws Exception {
        String uri = "/taux-change";
        TauxChange tauxchange = new TauxChange("AUD", "USD", new BigDecimal("0.7586"), "2021-06-25");

        String inputJson = mapToJson(tauxchange);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        TauxChange _tauxchange = mapFromJson(content, TauxChange.class);
        _tauxchange.setId(null);
        assertEquals(tauxchange, _tauxchange);
    }

    @Test
    @Order(7)
    public void updateTauxChangeById() throws Exception {
        String uri = "/taux-change/id/10015";
        TauxChange tauxchange = new TauxChange("AUD", "USD", new BigDecimal("0.7582"), "2021-06-24");

        String inputJson = mapToJson(tauxchange);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        TauxChange _tauxchange = mapFromJson(content, TauxChange.class);
        tauxchange.setId(10015L);
        assertEquals(200, status);
        assertEquals(tauxchange, _tauxchange);
    }

    @Test
    @Order(8)
    public void updateDateForTauxChange() throws Exception {
        String uri = "/taux-change/id/10015/date/2021-05-23";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        TauxChange _tauxchange = mapFromJson(content, TauxChange.class);
        assertEquals(200, status);
        assertEquals("2021-05-23", _tauxchange.getDate());
    }

    @Test
    @Order(9)
    public void updateTauxForTauxChange() throws Exception {
        String uri = "/taux-change/id/10015/taux/1.7582";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        TauxChange _tauxchange = mapFromJson(content, TauxChange.class);
        assertEquals(200, status);
        assertEquals("1.7582", String.valueOf(_tauxchange.getTaux()));
    }

    @AfterAll
    public void deleteTauxChangeById() throws Exception {
        String uri = "/taux-change/id/10016";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }
}
