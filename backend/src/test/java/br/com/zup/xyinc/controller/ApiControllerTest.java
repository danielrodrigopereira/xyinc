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

@WebAppConfiguration
public class ApiControllerTest extends br.com.zup.xyinc.XyincApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String entity = "Pessoa";
    private String entityRecordId = "289bc39d-fb8e-46f3-b23d-6a81f2e57fbf";
    

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAll()
        throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/"+entity)
	        .contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetById()
        throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/"+entity+"/"+entityRecordId)
	        .contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreate()
            throws Exception {
        Map<Object, Object> content = new HashMap<>();
        content.put("nome", "Joao Teste");
        content.put("idade", 28);
        content.put("saldo", 10.5);
        content.put("aniversario", "10/10/1990");
        content.put("ativo", false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/"+entity)
        	.content(new Gson().toJson(content))
	        .contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdate()
            throws Exception {
        Map<Object, Object> content = new HashMap<>();
        content.put("nome", "Joao Teste - OK");
        content.put("saldo", 1065.5);
        content.put("idade", 28);
        content.put("aniversario", "10/10/1990");
        content.put("ativo", false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/"+entity+"/"+entityRecordId)
        		.content(new Gson().toJson(content))
                .contentType(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDelete()
        throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/"+entity+"/"+entityRecordId)
	        .contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
