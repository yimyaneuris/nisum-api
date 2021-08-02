package org.nisum.nisumapi.service;

import lombok.RequiredArgsConstructor;
import org.nisum.nisumapi.dto.converter.Converter;
import org.nisum.nisumapi.dto.resquest.UserDTORequest;
import org.nisum.nisumapi.exceptions.ResourceNotFoundException;
import org.nisum.nisumapi.model.Phone;
import org.nisum.nisumapi.model.User;
import org.nisum.nisumapi.repository.PhoneRepository;
import org.nisum.nisumapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final Converter converter;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${encrypted.password}")
    private String passEncrypt;

    public User insert(UserDTORequest userDTO) {

        User user = converter.userDTORequestToUser(userDTO);

        user.setToken(TokenService.generateToken(user));
        user.setPasswordHash(passEncrypt);
        user.setActive(true);

        if (userDTO.getPhones() != null) {
            List<Phone> phoneList = new ArrayList<>();
            userDTO.getPhones()
                    .stream()
                    .map(p -> new Phone(p.getId(), p.getNumber(), p.getCitycode(), p.getCountrycode(), user))
                    .forEach(phoneList::add);

            user.setPhones(phoneList);
        }
        userRepository.save(user);
        logger.debug("Created Information for User: {}", user);
        return user;
    }

    public Optional<User> findById(long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("This user can not be found, please try again, or contact the admin");
        }

        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
