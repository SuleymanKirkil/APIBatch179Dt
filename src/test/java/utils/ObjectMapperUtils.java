package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {

    // In this class we will create a method that will convert Json Object to any Data Type namely: Pojo or Map
    // This method is useful to understand logic of generic methods
    // <T> T ---> Any Data Type

    public static <T> T convertJsonToJava(String str, Class<T> responseJava ){
        try{
        return new ObjectMapper().readValue(str,responseJava);
    } catch (Exception e){
            throw new RuntimeException(e);
        }
}
}
