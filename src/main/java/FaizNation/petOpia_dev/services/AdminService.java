package FaizNation.petOpia_dev.services;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final Map<String, String> users = new HashMap<>();
    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin1234";

    public boolean register(String username, String password) {
        if (ADMIN_USERNAME.equals(username)) {
            return false; 
        }
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, password);
        return true;
    }

    public boolean login(String username, String password) {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return true;
        }
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public boolean isAdmin(String username) {
        return ADMIN_USERNAME.equals(username);
    }
}