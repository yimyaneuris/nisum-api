package org.nisum.nisumapi.service;

import org.nisum.nisumapi.entity.User;
import org.nisum.nisumapi.exceptions.BadEmailException;
import org.nisum.nisumapi.exceptions.BadPasswordException;
import org.nisum.nisumapi.exceptions.EmailHandlerException;
import org.nisum.nisumapi.exceptions.UserNotFoundException;
import org.nisum.nisumapi.repository.PhoneRepository;
import org.nisum.nisumapi.repository.UserRepository;
import org.nisum.nisumapi.utils.EmailValidator;
import org.nisum.nisumapi.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final PhoneRepository phoneRepository;

    @Autowired
    public UserService(UserRepository userRepository, PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    public User create(User user) {

         if(!EmailValidator.validateEmail(user.getEmail())) {
            throw new BadEmailException("This email is not valid");
        };

        //TODO validar por que no esta pasando el password
//        if(!PasswordValidator.validatePassword(user.getPassword())){
//            throw new BadPasswordException("Password is invalid");
//        };
        Optional<User> op = userRepository.findByEmail(user.getEmail());

        if(op.isPresent()) {
            throw new EmailHandlerException("This email already exist");
        }

        user.setToken(Token.createJWT("", "", "", 1000));
        user.setCreated(LocalDate.now());
        user.setModified(LocalDate.now());
        user.setLast_login(LocalDate.now());

        User userCreated = userRepository.save(user);

        user.getPhones().forEach(phone -> {
            phone.setUser(user);
            phoneRepository.save(phone);
        });

        return userCreated;
    }

    public User findById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("This user can not be found, please try again, or contact the admin");
        }
        return userRepository.getById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
