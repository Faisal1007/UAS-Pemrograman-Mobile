package com.example.corona.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.corona.api.ApiEndPoint;
import com.example.corona.api.ApiService;
import com.example.corona.model.CountryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CountryViewModel extends AndroidViewModel {

    private MutableLiveData<CountryModel> mutableLiveData = new MutableLiveData<>();

    public CountryViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCountryData() {
        Retrofit retrofit = ApiService.getRetrofitService();
        ApiEndPoint apiEndpoint = retrofit.create(ApiEndPoint.class);
        Call<CountryModel> call = apiEndpoint.getSummaryIdn();
        call.enqueue(new Callback<CountryModel>() {
            @Override
            public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CountryModel> call, Throwable t) {

            }
        });
    }

    public LiveData<CountryModel> getCountryData() {
        return mutableLiveData;
    }
}
