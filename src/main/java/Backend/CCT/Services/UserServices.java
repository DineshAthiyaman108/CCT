package Backend.CCT.Services;

import Backend.CCT.Dao.UserDao;
import Backend.CCT.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserDao userDao;

    public User updateUser(User user) {

       return  userDao.save(user);
    }
    public boolean FindByuser(String userName , String userPassword)
    {
       Optional<User> user =  userDao.findById(userName);
        if(user.isPresent())
        {
            User userDetail = user.get();
            if(userDetail.getUserName().equals(userName) && userDetail.getUserPassword().equals(userPassword))
            {
                return  true;
            }
            return false;
        }
        return false;
    }
    public User FindByName(String name)
    {
        System.out.println(name + "For Email userName");
        Optional<User> newUser = userDao.findById(name);
        if(newUser.isPresent())
        {
            return  newUser.get();
        }
        return  null;
    }
}
