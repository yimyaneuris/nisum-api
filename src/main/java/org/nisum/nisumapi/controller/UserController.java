package org.nisum.nisumapi.controller;

import lombok.RequiredArgsConstructor;
import org.nisum.nisumapi.dto.converter.Converter;
import org.nisum.nisumapi.dto.response.UserDTOResponse;
import org.nisum.nisumapi.dto.resquest.UserDTORequest;
import org.nisum.nisumapi.exceptions.BadRequestException;
import org.nisum.nisumapi.exceptions.InternalServerErrorException;
import org.nisum.nisumapi.exceptions.ResourceNotFoundException;
import org.nisum.nisumapi.model.User;
import org.nisum.nisumapi.service.UserService;
import org.nisum.nisumapi.utils.Properties;
import org.nisum.nisumapi.utils.Validations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Converter userConverter;
    private final Properties customProperties;
    private final Validations validations;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.findAll();

        if(users.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        else
        {
            List<UserDTOResponse> usersDTO = users
                    .stream()
                    .map(userConverter::userToDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {

        Optional<User> optional = userService.findById(id);

        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("The user $0 can`t be found.", id));
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTORequest userDTO) {

        if(!validations.validatePassword(userDTO.password)) {
            throw new BadRequestException(customProperties.getEmailValidation());
        }

        if(userDTO.getEmail().isEmpty()) {
            throw new BadRequestException("A new user cannot already have an email");
        }

        if (userService.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new InternalServerErrorException("This email is already registered");
        }

        User user = userService.insert(userDTO);
        UserDTOResponse userDTOResponse = userConverter.userToDTO(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTOResponse);
    }
}
