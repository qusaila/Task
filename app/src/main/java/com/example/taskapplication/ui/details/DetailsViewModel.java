package com.example.taskapplication.ui.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taskapplication.base.BaseViewModel;
import com.example.taskapplication.listener.ServerResponse;

import org.json.JSONArray;

import java.util.ArrayList;

public class DetailsViewModel extends BaseViewModel {
MutableLiveData<ArrayList<Contributors>>data;

    public DetailsViewModel() {
        data=new MutableLiveData<>() ;
        data.setValue(new ArrayList<>());
    }

    public void getContributorsUrl(String ContributoUrl){
        requestManger.getRequest(ContributoUrl, new ServerResponse() {
            @Override
            public void onSucess(JSONArray jsonArray) {
                data.setValue(parserManger.getContribours(jsonArray));
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
