package mk.ukim.finki.veblabs.service.implementation;

import mk.ukim.finki.veblabs.exception.NonExistentUserException;
import mk.ukim.finki.veblabs.model.User;
import mk.ukim.finki.veblabs.repository.UserJpaRepository;
import mk.ukim.finki.veblabs.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImplementation implements UserService {
    private final UserJpaRepository userJpaRepository;

    public UserServiceImplementation(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User login(String username, String password) throws NonExistentUserException {
        return userJpaRepository.findByUsernameAndPassword(username,password).orElseThrow(NonExistentUserException::new);

    }

    @Override
    public User findByUsername(String username) throws NonExistentUserException {
        return userJpaRepository.findByUsername(username).orElseThrow(NonExistentUserException::new);
    }

    @Override
    public User save(String username, String name, String surname, String password, LocalDate dateOfBirth) {
        return userJpaRepository.save(new User(username,name,surname,password,dateOfBirth));
    }
}
