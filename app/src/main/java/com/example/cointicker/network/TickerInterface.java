package com.example.cointicker.network;

import androidx.lifecycle.LiveData;

import com.example.cointicker.data.CoinData;
import com.example.cointicker.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TickerInterface {
@GET("{crypto}/{currency}?apikey="+ Constants.apiKey)
Call<CoinData> getCoinLTCData(@Path("crypto") String crypto, @Path("currency") String currency);


}
