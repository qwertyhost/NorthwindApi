//package com.sparta.northwindapi;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.northwindapi.dto.CustomerDto;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//public class CustomerTests {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    private MockMvc mockMvc;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    public void setUp(){
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    public void postCustomer() throws Exception {
//        CustomerDto newCustomer = new CustomerDto("ABCDE","Big Important company","Boaty McBoatFace",
//                "Lord", "22 bakers street", "London", "BC", "020208",
//                "Narnia","05454-876","020-1234567");
//        String body = objectMapper.writeValueAsString(newCustomer);
//        MvcResult result = mockMvc.perform(
//                post("http://localhost:8080/customer")
//                        .content(body)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk()).andReturn();
//
//        assertTrue(result.getResponse().getContentAsString().contains("ABCDE"));
//        assertTrue(result.getResponse().getContentAsString().contains("London"));
//        assertTrue(result.getResponse().getContentAsString().contains("05454-876"));
//        assertTrue(result.getResponse().getContentAsString().contains("Narnia"));
//    }
//}
