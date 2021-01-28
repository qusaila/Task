package com.example.taskapplication.listener;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;

public interface ServerResponse {
  public   void onSucess(JSONArray jsonArray);
    public  void onFailed(String msg);
    public void onNoInternetConnection(String msg);

}
