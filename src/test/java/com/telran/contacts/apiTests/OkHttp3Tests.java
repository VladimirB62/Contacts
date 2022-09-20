package com.telran.contacts.apiTests;

import api.dto.AuthRequestDto;
import api.dto.AuthResponseDto;
import api.dto.ContactDto;
import api.dto.ErrorDto;
import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttp3Tests {

    private String email = "gushiddink@gmail.com";
    private String password = "12345678Aa$";
    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imd1c2hpZGRpbmtAZ21haWwuY29tIn0.RvRPouJtVT3ZbpYwtU4a57NzbUcQecbrEN5XqeusiJk";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Test
    public void loginHttpClientNegativeTestWithInvalidEmail() throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("gushiddinkgmail.com")
                .password("12345678Aa$").build();


        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(requestDto));

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        getMessageAndCodeFromResponse(client, request, gson);


    }

    @Test
    public void loginTest() throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email(email)
                .password("12345678Aa$")
                .build();


        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(requestDto));

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        getMessageAndCodeFromResponse(client, request, gson);
    }

    @Test
    public void addNewContactOkHttp3Test() throws IOException{
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        int i = (int) (System.currentTimeMillis()/1000*3600);

        ContactDto contactDto = ContactDto.builder()
                .address("Moscow")
                .description("goalkeeper")
                .email("Akinf" + i + "@gmail.com")
                .name("Igor")
                .lastName("Akinfeev")
                .phone("12345" + i)
                .build();


        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(contactDto));

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact")
                .header("Authorization",token)
                .post(requestBody)
                .build();


        getMessageAndCodeFromResponse(client, request, gson);
    }



    private void getMessageAndCodeFromResponse(OkHttpClient client, Request request, Gson gson) throws IOException {
        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();

        if (response.isSuccessful()){
            AuthResponseDto responseDto = gson.fromJson(responseJson, AuthResponseDto.class);
            System.out.println(responseDto.getToken());
            Assert.assertTrue(response.isSuccessful());
            Assert.assertEquals(response.code(), 200);
        }else{
            ErrorDto errorDto = gson.fromJson(responseJson,ErrorDto.class);
            System.out.println(errorDto.getCode());
            System.out.println(errorDto.getMessage());
            Assert.assertEquals(response.code(), 400);
        }

    }
}
