package ng.com.blogspot.hikmahtecht.cryptocurrencyconvert.API;



import ng.com.blogspot.hikmahtecht.cryptocurrencyconvert.Model.CryptoCurrency;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hikmah on 11/1/2017.
 */


    public interface APIService {
        //API call to get json object & append the URL
        @GET("/data/pricemulti?fsyms=BTC,ETH&tsyms=USD&e=Coinbase&extraParams=your_app_name")
        Call<CryptoCurrency> getBTC();
    }