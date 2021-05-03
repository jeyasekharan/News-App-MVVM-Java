package com.dxminds.newsapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dxminds.newsapp.custom_functions.ApiKeys;
import com.dxminds.newsapp.api_service.ApiInterface;
import com.dxminds.newsapp.api_service.ApiService;
import com.dxminds.newsapp.model.source_models.SourceModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceViewModel extends ViewModel {

    public MutableLiveData<SourceModel> requestSources() {
        final MutableLiveData<SourceModel> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService =
                ApiService.createBaseApiRetrofit().create(ApiInterface.class);

        apiService.callGetSource(ApiKeys.NEWS_API_KEY).enqueue(new Callback<SourceModel>() {
            @Override
            public void onResponse(Call<SourceModel> call, Response<SourceModel> response) {
                Log.e("tagggg", "getCurrencyList response="+response );

                if (response.isSuccessful() && response.body()!=null ) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SourceModel> call, Throwable t) {
                Log.e("taggg", "getProdList onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }
}
