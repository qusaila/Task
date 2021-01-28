package com.example.taskapplication.parser;

import com.example.taskapplication.ui.details.Contributors;
import com.example.taskapplication.ui.user.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ParserManger {

public   ArrayList<User> getUsersInfo(JSONArray jsonArray){

    Type collectionType = new TypeToken<ArrayList<User>>(){}.getType();
    ArrayList<User> data = (ArrayList<User>) new Gson()
            .fromJson(String.valueOf(jsonArray), collectionType);

    return data;
}

    public   ArrayList<Contributors> getContribours(JSONArray jsonArray){

        Type collectionType = new TypeToken<ArrayList<Contributors>>(){}.getType();
        ArrayList<Contributors> data = (ArrayList<Contributors>) new Gson()
                .fromJson(String.valueOf(jsonArray), collectionType);

        return data;
    }

}
