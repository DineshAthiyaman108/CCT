package Backend.CCT.Services;

import Backend.CCT.Dao.CoinDao;
import Backend.CCT.Model.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinServices {

    @Autowired
    CoinDao coinDao;

    public  Coin setCoin(Coin coin)
    {
      return coinDao.save(coin);
    }

}

