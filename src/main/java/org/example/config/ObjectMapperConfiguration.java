package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperConfiguration {

    public static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper(){
        if (objectMapper==null){
            objectMapper=new ObjectMapper();
        }
        return objectMapper;
    }
}
