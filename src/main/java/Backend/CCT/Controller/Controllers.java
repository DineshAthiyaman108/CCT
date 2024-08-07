package Backend.CCT.Controller;
import Backend.CCT.Model.UpdateCoin;
import Backend.CCT.Services.PriceCheck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;
import Backend.CCT.Model.Coin;
import Backend.CCT.Model.User;
import Backend.CCT.Services.CoinServices;
import Backend.CCT.Services.UserServices;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Controller
public class Controllers {

    @Autowired
    PriceCheck check;

    @Autowired
    CoinServices coinServices;

    @Autowired
    User user;

    @Autowired
    Coin coin;

    @Autowired
    UserServices userServices;

    @RequestMapping("/home")
    public String GetHome() {
        return "Home";
    }

    @RequestMapping("/login")
    public String getLogin() {
        return "Login";
    }
    @RequestMapping("/signup")
            public String getSignUp()
    {
        return  "SignUp";
    }
    @PostMapping("/signupdetails")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String number,
                        @RequestParam String email,
                        Model model) {
        user.setUserPassword(password);
        user.setUserName(username);
        user.setUserNumber(number);
        user.setUserEmail(email);
        return userServices.updateUser(user) != null ? "login" : "Error";

    }

    @RequestMapping("/coin")
    public  String getCoin()
    {
        return  "Coin";
    }
    @GetMapping("/coin/{id}")
    public ModelAndView getCoin(@PathVariable("id") String id, @RequestParam("price") String price, HttpSession session)
    {
        String name = (String) session.getAttribute("userName");
        BigDecimal coinPrice = new BigDecimal(price);
        coin.setCoinPrice(coinPrice);
        coin.setName(id);
        coin.setUserName(name);
        session.setAttribute("selectedUser" , coin);
        Coin userCoin =  coinServices.setCoin(coin);
        session.setAttribute("selectedCoin",userCoin);
        ModelAndView modelAndView = new ModelAndView("Mycoin"); // Name of the Thymeleaf template
        modelAndView.addObject("coin", userCoin);
        return modelAndView;

    }


    @GetMapping("/mycoin")
    public ModelAndView getMycoin(HttpSession session) {
        Coin coin = (Coin) session.getAttribute("selectedCoin");
        if (coin == null) {
            return new ModelAndView(new RedirectView("/coin"));
        }
        ModelAndView modelAndView = new ModelAndView("Mycoin");
        modelAndView.addObject("coin", coin);
        return modelAndView;
    }

    @PostMapping("/coin/newdata")
    public ResponseEntity<String> receiveCoinData(@RequestBody List<UpdateCoin> coins , HttpSession session) {
        System.out.println("Received coin data: " + coins);
        check.priceCheck(coins , session);
        return ResponseEntity.ok("Coin data received successfully");
    }

}
