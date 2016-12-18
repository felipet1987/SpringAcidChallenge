/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hello;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired 
    private ObjectMapper mapper;
    
    

    	@Ignore
	    @Test
	    public void testIsThisReallyTrue() {
	        assertTrue(true);
	    }
    	
	    @Test
	    public void helloTest() throws Exception {
	    	
	    	mockMvc.perform(get("/Hello"))
	    	.andExpect(status().isOk());
	    	
	        assertTrue(true);
	    }
	    @Test
	    public void postTest() throws Exception {
	    	
	    	User user = new User("usuario1","imagen");		
	    			
	      String json = mapper.writeValueAsString(user);
	      
	      mockMvc.perform(post("/users")
	         .contentType(MediaType.APPLICATION_JSON)
	         .content(json)
	         .accept(MediaType.APPLICATION_JSON))
	         .andExpect(status().is2xxSuccessful());
	    }    
	    

    	




}
