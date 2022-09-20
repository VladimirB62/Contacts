package com.telran.contacts.apiTests;

import api.dto.*;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleteContactByIdRestAssuredTests {

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imd1c2hpZGRpbmtAZ21haWwuY29tIn0.RvRPouJtVT3ZbpYwtU4a57NzbUcQecbrEN5XqeusiJk";
    int id;

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
    public void deleteContactByIdPositiveTest(){
        String status = given().header("Authorization", token)
                .delete("contact/" + id)
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");
        System.out.println(status);

    }

    @Test
    public void deleteAllContactsPositiveTest(){
        String status = given().header("Authorization", token)
                .delete("clear")
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");
        System.out.println(status);

    }


}
