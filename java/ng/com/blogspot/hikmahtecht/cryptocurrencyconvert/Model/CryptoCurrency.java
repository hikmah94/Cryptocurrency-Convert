package ng.com.blogspot.hikmahtecht.cryptocurrencyconvert.Model;

/**
 * Created by hikmah on 11/1/2017.
 */

public class CryptoCurrency {
    //Create getter & setter for response objects
    private BTC BTC;
    private ETH ETH;

    public BTC getBTC() {
        return BTC;
    }

    public void setBTC(BTC BTC) {
        this.BTC = BTC;
    }

    public ETH getETH() {
        return ETH;
    }

    public void setETH(ETH ETH) {
        this.ETH = ETH;
    }
}