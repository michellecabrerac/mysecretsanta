package com.michellecabrerac.mysecretsanta.service;

import com.michellecabrerac.mysecretsanta.model.User;

public interface UserService {
    User createUser(String email, String name, String surname);
    User getUserById(Long id);
    User getUserByEmail(String email);
}
