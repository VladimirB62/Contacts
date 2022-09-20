package com.telran.contacts.apiTests;

import api.dto.ContactDto;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class UpdateExistContactRestAssuredTest {

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imd1c2hpZGRpbmtAZ21haWwuY29tIn0.RvRPouJtVT3ZbpYwtU4a57NzbUcQecbrEN5XqeusiJk";
    int id;
    String newPhone;

    @BeforeMethod
    public void ensurePreconditions(){
        System.err.close();
        System.setErr(System.out);

        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";

        int i = (int) (System.currentTimeMillis()/1000*3600);
        ContactDto contactDto = ContactDto.builder()
                .address("Moscow")
                .description("goalkeeper")
                .email("Akinf" + i + "@gmail.com")
                .name("Igor")
                .lastName("Akinfeev")
                .phone("12345" + i)
                .build();

        id = given().header("Authorization", token)
                .contentType("application/json")
                .body(contactDto)
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("id");

    }

    @Test
    public void changeExistContactPositiveTest(){
        ContactDto contactDto = ContactDto.builder()
                .address("Moscow")
                .description("goalkeeper")
                .email("Akinf135@gmail.com")
                .name("Igor")
                .lastName("Akinfeev")
                .phone("123456789" )
                .id(id)
                .build();

        newPhone = given().header("Authorization", token)
                .contentType("application/json")
                .body(contactDto)
                .put("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("phone");

        System.out.println(newPhone);
    }

}
