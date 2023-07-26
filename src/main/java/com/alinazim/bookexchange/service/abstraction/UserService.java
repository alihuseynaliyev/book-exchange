package com.alinazim.bookexchange.service.abstraction;

import com.alinazim.bookexchange.model.request.UserRequest;
import com.alinazim.bookexchange.model.response.UserResponse;

import java.util.List;

public interface UserService {

    void createUser(UserRequest userRequest);

    void updateUser(UserRequest userRequest, Long id);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();
}