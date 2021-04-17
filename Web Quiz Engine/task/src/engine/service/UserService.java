package engine.service;

import engine.model.DTO.UserDTO;
import engine.model.User;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(UserDTO userDTO) {
        Optional<User> isEmailAddressExists = Optional.ofNullable(userRepository.findByEmail(userDTO.getEmail()));
        if (isEmailAddressExists.isPresent()) {
            return false;
        } else {
            userRepository.save(new User(userDTO.getEmail(), new BCryptPasswordEncoder().encode(userDTO.getPassword())));
            return true;
        }
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }
}
