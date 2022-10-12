//package com.sparta.northwindapi;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.northwindapi.dto.RegionDTO;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//public class RegionTests {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    public void region_getAll() throws Exception {
//        MvcResult result = mockMvc.perform(
//                get("http://localhost:8080/region/all")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//
//        System.out.println(result.getResponse().getContentAsString());
//        Assertions.assertTrue(result.getResponse().getContentAsString().contains("Eastern"));
//    }
//
//    @Test
//    public void region_getById() throws Exception {
//        MvcResult result = mockMvc.perform(
//                        get("http://localhost:8080/region/1")
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//
//        System.out.println(result.getResponse().getContentAsString());
//        Assertions.assertTrue(result.getResponse().getContentAsString().contains("Eastern"));
//    }
//
//    @Test
//    public void region_newRegion() throws Exception {
//        RegionDTO newRegion = new RegionDTO(5,"North-west");
//
//        String body = objectMapper.writeValueAsString(newRegion);
//
//        MvcResult result = mockMvc.perform(
//                post("http://localhost:8080/region")
//                        .content(body)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//
//        Assertions.assertTrue(result.getResponse().getContentAsString().contains("North-west"));
//    }
//}
