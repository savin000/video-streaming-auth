package ru.savin.videostreamingauth.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.savin.videostreamingauth.entity.User;
import ru.savin.videostreamingauth.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final String NO_USER_FOUND = "No user found";
    private static final String USER_ALREADY_EXISTS = "User already exists";

    private final UserRepository userRepository;
    private final AES256TextEncryptor aes256TextEncryptor;

    @Autowired
    public UserService(UserRepository userRepository, AES256TextEncryptor aes256TextEncryptor) {
        this.userRepository = userRepository;
        this.aes256TextEncryptor = aes256TextEncryptor;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(NO_USER_FOUND));
    }

    public void addUser(User user) {
        if (this.userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new EntityExistsException(USER_ALREADY_EXISTS);
        } else {
            user.setPassword(aes256TextEncryptor.encrypt(user.getPassword()));
            this.userRepository.save(user);
        }
    }

    public void deleteUserByUsername(String username) {
        Optional<User> user = this.userRepository.findUserByUsername(username);

        if (user.isPresent()) {
            this.userRepository.delete(user.get());
        } else {
            throw new EntityExistsException(NO_USER_FOUND);
        }
    }
}
