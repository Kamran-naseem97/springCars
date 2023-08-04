package com.sky.SpringCars.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.SpringCars.Domain.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // loads the application context
@AutoConfigureMockMvc // Creates a mock mvc bean
@Sql(scripts = {"classpath:car-schema.sql", "classpath:car-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class CarMvcTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception{
        //METHOD: POST
        //PATH: /car/create
        //BODY: JSON
        //HEADERS: CONTENT-TYPE: application\json
        Car car = new Car("Toyota Celica", 250);
        System.out.println("DATA: " + car);
        String carJSON = this.mapper.writeValueAsString(car);
        System.out.println("JSON: " + carJSON);
        RequestBuilder req = MockMvcRequestBuilders.post("/car/create")
                .content(carJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = status().isCreated();
        Car responseBody = new Car(3,"Toyota Celica", 250);
        System.out.println("DATA: " + responseBody);
        String responseBodyJSON = this.mapper.writeValueAsString(responseBody);
        System.out.println("JSON: " + responseBodyJSON);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(responseBodyJSON);
        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testCreate2() throws Exception{
        String carJSON = this.mapper.writeValueAsString(new Car("BMW M3 Touring", 550));
        String responseBodyJSON = this.mapper.writeValueAsString(new Car(3,"BMW M3 Touring", 550));
        this.mvc.perform(
                        MockMvcRequestBuilders.post("/car/create")
                                .content(carJSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(responseBodyJSON));
    }

    @Test
    void testRead() throws Exception{
        final int id = 1;
        String responseBody = this.mapper.writeValueAsString(new Car(id, "Toyota Supra A90", 380));
        this.mvc.perform(MockMvcRequestBuilders.get("/car/get/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testReadSearch() throws Exception{
        String responseBody = this.mapper.writeValueAsString(380);
        this.mvc.perform(MockMvcRequestBuilders.get("/car/findPower/Supra"))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));
    }

    @Test
    void testDelete() throws Exception{
        final int id = 2;
        String responseBody = this.mapper.writeValueAsString(new Car(id, "BMW 440i", 380));
        this.mvc.perform(delete("/car/remove/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testUpdate() throws Exception{
        final int id = 1;
        final String makeModel = "Ferrari 458 Italia";
        final Integer power = 570;

        String responseBody = this.mapper.writeValueAsString(new Car(id, "Ferrari 458 Italia", 570));
        this.mvc.perform(patch("/car/update/" + id
                + "?makeModel=" + makeModel
                + "&power=" + power))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testFailCreate(){

    }

    @Test
    void testNotFound(){

    }
}
