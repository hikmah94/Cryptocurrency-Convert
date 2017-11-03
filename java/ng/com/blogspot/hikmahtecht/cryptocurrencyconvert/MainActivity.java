package ng.com.blogspot.hikmahtecht.cryptocurrencyconvert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import ng.com.blogspot.hikmahtecht.cryptocurrencyconvert.API.APIClient;
import ng.com.blogspot.hikmahtecht.cryptocurrencyconvert.API.APIService;
import ng.com.blogspot.hikmahtecht.cryptocurrencyconvert.Model.BTC;
import ng.com.blogspot.hikmahtecht.cryptocurrencyconvert.Model.CryptoCurrency;
import ng.com.blogspot.hikmahtecht.cryptocurrencyconvert.Model.ETH;
import ng.com.blogspot.hikmahtecht.cryptocurrencyconvert.Model.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CryptoCurrency CryptoCurrency;
    BTC btc;
    ETH eth;

    double BtcGetUsd, EthGetUSD; //define variable for the parsed objects value
    ProgressDialog progressDialog;
    TextView textView_btc;
    TextView textView_eth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to initialized progressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait ....");
        //to start progressDialog
        progressDialog.show();

        LoadCryptoCompaire();

        textView_btc = (TextView) findViewById(R.id.btc_Text);
        textView_eth = (TextView) findViewById(R.id.eth_Text);

        //this is to initialized cardView
        CardView cardView_btc = (CardView) findViewById(R.id.btc_card);
        CardView cardView_eth = (CardView) findViewById(R.id.eth_card);

        cardView_btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to parse both BTC & ETH value to ConvertActivity
                Intent intent = new Intent(getApplicationContext(), ConvertActivity.class);
                intent.putExtra("btc", BtcGetUsd);
                intent.putExtra("eth", EthGetUSD);
                startActivity(intent);
            }
        });
        cardView_eth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to parse both BTC & ETH value to ConvertActivity
                Intent intent = new Intent(getApplicationContext(), ConvertActivity.class);
                intent.putExtra("btc", BtcGetUsd);
                intent.putExtra("eth", EthGetUSD);
                startActivity(intent);
            }
        });

    }


    //to defined method for the response
    public void LoadCryptoCompaire() {
        //to initialized the APIClient
        APIClient apiClient = new APIClient();
        try {
            //to defined APIService to get client response
            APIService apiService = APIClient.getClient().create(APIService.class);
            //call client response
            Call<CryptoCurrency> btcCall = apiService.getBTC();
            btcCall.enqueue(new Callback<CryptoCurrency>() {
                @Override
                public void onResponse(Call<CryptoCurrency> call, Response<CryptoCurrency> response) {
                    //to initialized respond class
                    CryptoCurrency = new CryptoCurrency();
                    CryptoCurrency = response.body();

                    //to initialized the json object class
                    btc = new BTC();
                    eth = new ETH();

                    btc = CryptoCurrency.getBTC();
                    eth = CryptoCurrency.getETH();

                    //parse object to variable
                    BtcGetUsd = btc.getUSD();
                    EthGetUSD = eth.getUSD();

                    // to display result in TextView with Local Currency Symbols
                    textView_btc.setText("1 BTC : " + Utils.getCurrencySymbol("USD") + BtcGetUsd);
                    textView_eth.setText("1 ETH : " + Utils.getCurrencySymbol("USD") + EthGetUSD);

                    //stop progressDialog
                    progressDialog.dismiss();


                }

                @Override
                public void onFailure(Call<CryptoCurrency> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (Exception e) {

        }
    }

}