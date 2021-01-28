package com.example.taskapplication.data;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiServices {
    @GET
    Observable<Response<ResponseBody>> getRequest(@Url String apiName);
}
