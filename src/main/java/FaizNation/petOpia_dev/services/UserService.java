package FaizNation.petopia_dev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FaizNation.petopia_dev.models.User;
import FaizNation.petopia_dev.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return false; // Username already exists
        }
        User user = new User(username, password);
        userRepository.save(user);
        return true;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
