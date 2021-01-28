package com.example.taskapplication.ui.user;

import androidx.lifecycle.MutableLiveData;

import com.example.taskapplication.base.BaseViewModel;
import com.example.taskapplication.listener.ServerResponse;
import com.example.taskapplication.utils.Constants;

import org.json.JSONArray;

import java.util.ArrayList;

public class UserViewModel extends BaseViewModel {
    public MutableLiveData<ArrayList<User>> data = new MutableLiveData<>();

    public UserViewModel() {
        data.setValue(new ArrayList<>());
    }

    public void getUsers() {
        requestManger.getRequest(Constants.USERS, new ServerResponse() {
            @Override
            public void onSucess(JSONArray jsonObject) {
                data.setValue(parserManger.getUsersInfo(jsonObject));

            }

            @Override
            public void onFailed(String msg) {
                errorMsg.setValue(msg);
            }

            @Override
            public void onNoInternetConnection(String msg) {
                errorMsg.setValue(msg);
            }
        });

    }


}
