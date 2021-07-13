package org.nisum.nisumapi.service;

import org.nisum.nisumapi.entity.User;
import org.nisum.nisumapi.exceptions.UserNotFoundException;
import org.nisum.nisumapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {

        User userCreated = userRepository.save(user);



        // validar el email regex
        // validar contrasenia
        // validar que no exista ese email
        // obtener el token

//        if(user.getName().isEmpty() || user.getName() == null) {
//            throw new BadRequestException("This product cant be found, please try again");
//        }

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
