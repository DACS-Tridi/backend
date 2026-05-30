package com.dacs.backend.service;

import java.util.Optional;

import com.dacs.backend.model.entity.User;

public interface UserService extends CommonService<User>{

    Optional<User> findByUsername(String username);

    User findOrCreateByUsername(String username);
}
