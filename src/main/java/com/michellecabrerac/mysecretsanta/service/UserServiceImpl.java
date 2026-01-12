package com.michellecabrerac.mysecretsanta.service;

import com.michellecabrerac.mysecretsanta.exception.BusinessRuleException;
import com.michellecabrerac.mysecretsanta.exception.DuplicateEmailException;
import com.michellecabrerac.mysecretsanta.exception.UserNotFoundException;
import com.michellecabrerac.mysecretsanta.model.User;
import com.michellecabrerac.mysecretsanta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User with email:" + email + " not found"));
    }

    @Override
    public User createUser(String email, String name, String surname) {
        //Firstly validate fields
        validateRequiredFields(email, name);
        //Secondly go to repository to know if it is an existing user
        if(userRepository.existsByEmail(email)){
            throw new DuplicateEmailException("User already exists");
        }
        //Create the user
        User user = User.builder()
                .email(email)
                .name(name)
                .surname(surname).build();
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    private void validateRequiredFields(String email, String name) {
        //Validar que los campos no estén vacíos
        if(email == null || email.isBlank()){
            throw new BusinessRuleException("Email can't be empty");
        }
        if(name == null || name.isBlank()){
            throw new BusinessRuleException("Name can't be empty");
        }
        //Validar el formato del correo
        if(!email.contains("@")){ //TODO - añadir regex para verificar que cumple el formato de correo
            throw new BusinessRuleException("The email doesn't have the correct format");
        }
    }

    @Override
    public User updateUser(Long id, String name, String surname) {
        User user = getUserById(id);
        if(name != null && !name.isBlank()){
            user.setName(name);
        }
        if(surname != null && !surname.isBlank()){
            user.setSurname(surname);
        }

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long id) {
        //Validamos que el usuario existe
        User user = getUserById(id);

        //Validamos que no tenga participaciones activas y si las tiene lanzamos error
        validateNoActiveParticipations(user);

        //Eliminamos
        userRepository.delete(user);
    }

    private void validateNoActiveParticipations(User user) {
        //TODO add logic
    }


}
