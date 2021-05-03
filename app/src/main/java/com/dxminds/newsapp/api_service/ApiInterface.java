package com.dxminds.newsapp.api_service;

import com.dxminds.newsapp.model.ArticlesModel;
import com.dxminds.newsapp.model.source_models.SourceModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("sources")
    Call<SourceModel> callGetSource(@Query("apiKey") String api_token);

    @GET("everything")
    Call<ArticlesModel> callArticles(@Query("apiKey") String api_token, @Query("q") String q);

}
