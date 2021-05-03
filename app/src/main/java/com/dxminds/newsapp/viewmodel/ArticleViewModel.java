package com.dxminds.newsapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.dxminds.newsapp.api_service.ApiInterface;
import com.dxminds.newsapp.api_service.ApiService;
import com.dxminds.newsapp.custom_functions.ApiKeys;
import com.dxminds.newsapp.model.ArticlesModel;
import com.dxminds.newsapp.model.source_models.SourceModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleViewModel {
    String id;

    public ArticleViewModel(String id) {
        this.id = id;
    }

    public MutableLiveData<ArticlesModel> requestArticles() {
        final MutableLiveData<ArticlesModel> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService =
                ApiService.createBaseApiRetrofit().create(ApiInterface.class);

        apiService.callArticles(ApiKeys.NEWS_API_KEY, id).enqueue(new Callback<ArticlesModel>() {
            @Override
            public void onResponse(Call<ArticlesModel> call, Response<ArticlesModel> response) {
                Log.e("tagggg", "getCurrencyList response="+response );

                if (response.isSuccessful() && response.body()!=null ) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArticlesModel> call, Throwable t) {
                Log.e("taggg", "getProdList onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }
}
