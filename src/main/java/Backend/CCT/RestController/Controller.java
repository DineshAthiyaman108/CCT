package Backend.CCT.RestController;


import Backend.CCT.Model.User;
import Backend.CCT.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpResponse;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {

    @Autowired
    User user;

    @Autowired
    UserServices userServices;

    @PostMapping("/userdata")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userServices.updateUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public void addUser(@RequestParam String userName, @RequestParam String userPassword, HttpSession session, HttpServletResponse response) {
        System.out.println(userName);
        System.out.println(userPassword);
        boolean flag = userServices.FindByuser(userName, userPassword);
        System.out.println(flag);
        if (flag) {
            session.setAttribute("userName", userName);
            try {
                response.sendRedirect("/home");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                response.sendRedirect("/login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
