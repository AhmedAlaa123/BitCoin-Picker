package com.example.cointicker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cointicker.R;
import com.example.cointicker.data.CoinData;
import com.example.cointicker.network.TickerInterface;
import com.example.cointicker.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView textView_btc,textView_eth,textView_ltc;
    private Spinner spinner;
    private String selectedCurrency="";
    TickerInterface tickerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_btc = findViewById(R.id.text_view_btc);
        textView_eth=findViewById(R.id.text_view_eth);
        textView_ltc=findViewById(R.id.text_view_ltc);
        spinner=findViewById(R.id.spinner_currency);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.currnciesList,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.baseUrl)
                .build();
        tickerInterface=retrofit.create(TickerInterface.class);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCurrency=parent.getItemAtPosition(position).toString();
        textView_btc.setText("1 "+Constants.cryptoList[0]+" = ? "+selectedCurrency);
        textView_eth.setText("1 "+Constants.cryptoList[1]+" = ? "+selectedCurrency);
        textView_ltc.setText("1 "+Constants.cryptoList[2]+" = ? "+selectedCurrency);

        makeCalling(Constants.cryptoList[0],textView_btc);
        makeCalling(Constants.cryptoList[1],textView_eth);
        makeCalling(Constants.cryptoList[2],textView_ltc);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void makeCalling(final String crypto, TextView textView)
    {
        final TextView textView1=textView;
        Call<CoinData> callBtc =tickerInterface.getCoinLTCData(crypto,selectedCurrency);
        callBtc.enqueue(new Callback<CoinData>() {
            @Override
            public void onResponse(Call<CoinData> call, Response<CoinData> response) {
                if(response!=null&&response.isSuccessful()) {
                    // String date = response.body().getUpdateTime();
                    //String base = response.body().getBase();
                    //String quote = response.body().getQuote();
                    String rate = ((int)response.body().getRate()) + "";
                    textView1.setText("1 "+crypto+ " = "+rate+" "+selectedCurrency);
                }
                else{
                    textView1.setText(response.code());
                }
            }

            @Override
            public void onFailure(Call<CoinData> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}