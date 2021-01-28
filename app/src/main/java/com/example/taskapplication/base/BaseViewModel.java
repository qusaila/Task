package com.example.taskapplication.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taskapplication.BaseApplication;
import com.example.taskapplication.data.RequestManger;
import com.example.taskapplication.parser.ParserManger;

public class BaseViewModel  extends ViewModel {
   public RequestManger requestManger;
public ParserManger parserManger;
public  MutableLiveData<String>errorMsg=new MutableLiveData<>();
public BaseViewModel( ) {
        this.requestManger = new RequestManger(BaseApplication.getInstance());

        errorMsg.setValue("");
        parserManger=new ParserManger();
    }
}
