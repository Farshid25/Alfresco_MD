package model.restModel;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    User user;


    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        users.add(new User("1","farshid"));
        return users;
    }
    public String getName(String name ){
        return user.getName();
    }
    public User create(String id, String name){
        User jadid = new User(id, name);
        //user.setName(name);
        return jadid;
    }
}
