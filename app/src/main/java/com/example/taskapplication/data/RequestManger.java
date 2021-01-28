package com.example.taskapplication.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;



import com.example.taskapplication.R;
import com.example.taskapplication.listener.ServerResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;


import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
 import okhttp3.ResponseBody;
import retrofit2.Response;

public class RequestManger {


    private ApiServices apiService;
    Context context;

    public RequestManger(Context context
    ) {
        this.context = context;
        apiService = ApiClient.getClient().create(ApiServices.class);
    }


    public void getRequest(String apiName, final ServerResponse serverResponse) {
        if (isInternetConnected()) {
            String api = apiName;
            final Observable<Response<ResponseBody>> observable = apiService

                    .getRequest(apiName);
            Observer<Response<ResponseBody>> responseObserver = new Observer<Response<ResponseBody>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Response<ResponseBody> responseBodyResponse) {
                    JSONArray jsonArray = null;

                    if (responseBodyResponse.isSuccessful()) {
                        try {
                            jsonArray = new JSONArray(responseBodyResponse.body().string());

                            Log.d("response=" + api, jsonArray.toString());


                            if (serverResponse != null) {
                                serverResponse.onSucess(jsonArray);
                            }
                        } catch (JSONException e) {
                            if (serverResponse != null) {
                                serverResponse.onSucess(jsonArray);
                            }
                            e.printStackTrace();
                        } catch (IOException e) {
                            if (serverResponse != null) {
                                serverResponse.onSucess(jsonArray);
                            }
                            e.printStackTrace();
                        }

                    } else {
                        // handell error code here
                        switch (responseBodyResponse.code()) {
                            case 500:

                                Log.d("500", "server down");
                                break;

                            case 404:
                                Log.d("404", "not found");

                                break;
                            case 400:
                                Log.d("400", "bad request");

                        }
                        if (serverResponse != null) {
                            serverResponse.onFailed(context.getResources().getString(R.string.general_msg));
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (serverResponse != null)
                        serverResponse.onFailed(context.getResources().getString(R.string.general_msg));

                }

                @Override
                public void onComplete() {
                    observable.unsubscribeOn(Schedulers.io());
                }
            };
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(responseObserver);
        } else {
            if (serverResponse != null)
                serverResponse.onNoInternetConnection(context.getResources().getString(R.string.no_internet));
        }
    }


    public boolean isInternetConnected() {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null)
            return false;
        else
            return true;
    }
}