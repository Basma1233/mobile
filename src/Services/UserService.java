/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import Entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class UserService {
    User gUser = new User();

    public User parseUserJson(String json) {
        User u = null;
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            if (!user.isEmpty()) {
                u = new User();
                List<String> rolesUser = new ArrayList();
                float id = Float.parseFloat(user.get("id").toString());
                u.setId((int) id);
                List<String> roles = (List<String>) user.get("roles");
                for (String role : roles) {
                    rolesUser.add(role);
                }
                u.setLogin(json);
                u.setName(user.get("Name").toString());
                u.setLogin(user.get("Login").toString());
                u.setPassword(user.get("Password").toString());
               
               
                
            }
        } catch (IOException ex) {

        }
        return u;

    }

    
}
