package Backend.CCT.Services;

import Backend.CCT.Dao.UserDao;
import Backend.CCT.Model.Coin;
import Backend.CCT.Model.UpdateCoin;
import Backend.CCT.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PriceCheck {

    @Autowired
    User user;

    @Autowired
    EmailServices emailServices;

   @Autowired
    UserServices userServices;

    public void priceCheck(List<UpdateCoin> coins, HttpSession session) {
        Coin selectedCoin = (Coin) session.getAttribute("selectedUser");

        if (selectedCoin == null) {

            return;
        }

        for (UpdateCoin updateCoin : coins) {
            System.out.println(selectedCoin.getName() + " " + updateCoin.getCoinName());
            if (selectedCoin.getName().toLowerCase().equals(updateCoin.getCoinName().toLowerCase())) {
                BigDecimal currentPrice = updateCoin.getCoinPrice();
                BigDecimal previousPrice = selectedCoin.getCoinPrice();
                BigDecimal thresholdPrice = previousPrice.multiply(BigDecimal.valueOf(0.4));




                if (currentPrice.compareTo(thresholdPrice) > 0) {

                    String subject = "Price Alert: " + selectedCoin.getName();
                    String text = "The price of " + selectedCoin.getName() +
                            " has dropped below 40% of its previous value.\n" +
                            "Current Price: " + currentPrice + "\n" +
                            "Previous Price: " + previousPrice;
                    user = userServices.FindByName(selectedCoin.getUserName());
                    String recipientEmail = user.getUserEmail();
                    String senderEmail = "dineshathiyaman810@gmail.com";
                    emailServices.sendSimpleMessage(recipientEmail, subject, text, senderEmail);
                }
            }
        }
    }
}
