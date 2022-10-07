package com.sparta.northwindapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwindapi.dto.OrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class OrderTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        OrderDTO newOrder = new OrderDTO(2222,new Date(1996,7,8),new Date(1996,7,8),
                new Date(1996,7,8), new BigDecimal(99.77), "Boaty McBoatFace", "A port", "A city",
                "RJ","05454-876","Brazil");
        String body = objectMapper.writeValueAsString(newOrder);
        MvcResult setup = mockMvc.perform(
                post("http://localhost:8080/order")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void getAllOrders() throws Exception{
        MvcResult result = mockMvc.perform(
                get("http://localhost:8080/order/all")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Vins et alcools Chevalier"));
        assertTrue(result.getResponse().getContentAsString().contains("Toms Spezialitten"));
        assertTrue(result.getResponse().getContentAsString().contains("Hanari Carnes"));
        assertTrue(result.getResponse().getContentAsString().contains("Victuailles en stock"));
    }

    @Test
    public void getOrders() throws Exception{
        MvcResult result = mockMvc.perform(
                get("http://localhost:8080/order/10249")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("10249"));
        assertTrue(result.getResponse().getContentAsString().contains("Toms Spezialitten"));
        assertTrue(result.getResponse().getContentAsString().contains("Luisenstr. 48"));
        assertTrue(result.getResponse().getContentAsString().contains("44087"));
    }

    @Test
    public void getOrders_notFound() throws Exception{
        MvcResult result = mockMvc.perform(
                get("http://localhost:8080/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Order not found"));
    }

    @Test
    public void postOrder() throws Exception {
        OrderDTO newOrder = new OrderDTO(2223,new Date(1996,7,8),new Date(1996,7,8),
                new Date(1996,7,8), new BigDecimal(99.77), "Boaty McBoatFace", "A port", "A city",
                "RJ","05454-876","Brazil");
        String body = objectMapper.writeValueAsString(newOrder);
        MvcResult result = mockMvc.perform(
                post("http://localhost:8080/order")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("2223"));
        assertTrue(result.getResponse().getContentAsString().contains("Boaty McBoatFace"));
        assertTrue(result.getResponse().getContentAsString().contains("05454-876"));
        assertTrue(result.getResponse().getContentAsString().contains("Brazil"));
    }

    @Test
    public void postOrder_notPosted() throws Exception {
        OrderDTO newOrder = new OrderDTO(5555,null,null,
                null, null, null, null, null,
                null,null,null);
        String body = objectMapper.writeValueAsString(newOrder);
        MvcResult result = mockMvc.perform(
                post("http://localhost:8080/order")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("Order could not be added"));
    }

    @Test
    public void updateOrder_ShipName() throws Exception {
        OrderDTO newOrder = new OrderDTO(2222,null,null,
                null, null, "Itsy Bitsy Teeny Weeny Tugboaty", null, null,
                null,null,null);
        String body = objectMapper.writeValueAsString(newOrder);
        MvcResult result = mockMvc.perform(
                patch("http://localhost:8080/order")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("2222"));
        assertTrue(result.getResponse().getContentAsString().contains("Itsy Bitsy Teeny Weeny Tugboaty"));
    }

    @Test
    public void updateOrder_NotAdded() throws Exception {
        OrderDTO newOrder = new OrderDTO(3333,null,null,
                null, null, "Itsy Bitsy Teeny Weeny Tugboaty", null, null,
                null,null,null);
        String body = objectMapper.writeValueAsString(newOrder);
        MvcResult result = mockMvc.perform(
                patch("http://localhost:8080/order")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("Order could not be updated"));
    }

    @Test
    public void updateOrder_ShipAddress() throws Exception {
        OrderDTO newOrder = new OrderDTO(2222,null,null,
                null, null, null, null, null,
                null,null,"United Kingdom");
        String body = objectMapper.writeValueAsString(newOrder);
        MvcResult result = mockMvc.perform(
                patch("http://localhost:8080/order")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("2222"));
        assertTrue(result.getResponse().getContentAsString().contains("United Kingdom"));
    }

    @Test
    public void removeOrder() throws Exception {
        MvcResult result = mockMvc.perform(
                delete("http://localhost:8080/order/remove/3333")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Order could not be removed"));
    }

}
