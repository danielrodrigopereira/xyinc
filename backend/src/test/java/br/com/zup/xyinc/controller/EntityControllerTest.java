package br.com.zup.xyinc.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import br.com.zup.xyinc.factory.EntityFactory;

@WebAppConfiguration
public class EntityControllerTest extends br.com.zup.xyinc.XyincApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private Mongo mongo;

    private MockMvc mockMvc;
    private String dbName = "test";
    private String entity = "Pessoa";
    //TODO: recuperar um id do testGetAll() 
    private String entityId = "5d522333dbe56e41782e1137"; 

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void testCreate() throws Exception {
    	
        Map<String, String> att = new HashMap<>();
        att.put("nome", "texto");
        att.put("referencia", "numero");
        att.put("valor", "valor");
        att.put("dataVencimento", "data");
        att.put("ativo", "ativo");
    	EntityFactory content = new EntityFactory();
        content.setName(entity);
        content.setAttributes(att);
        
        String teste = new Gson().toJson(content);
        mockMvc.perform(MockMvcRequestBuilders.post("/entity")
        		.content(new Gson().toJson(content))
                .contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entity")
	        .contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entity/"+entityId)
    	        .contentType(MediaType.APPLICATION_JSON))
    			.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/entity/"+entityId)
    	        .contentType(MediaType.APPLICATION_JSON))
    			.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
