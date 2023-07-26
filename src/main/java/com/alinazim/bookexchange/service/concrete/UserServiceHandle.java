package com.alinazim.bookexchange.service.concrete;

import com.alinazim.bookexchange.dao.entity.UserEntity;
import com.alinazim.bookexchange.dao.repository.UserRepository;
import com.alinazim.bookexchange.mapper.UserMapper;
import com.alinazim.bookexchange.model.request.UserRequest;
import com.alinazim.bookexchange.model.response.UserResponse;
import com.alinazim.bookexchange.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceHandle implements UserService {
    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequest userRequest) {
        userRepository.save(
                UserMapper.INSTANCE.buildUserEntity(userRequest)
        );
    }

    @Override
    public void updateUser(UserRequest userRequest, Long id) {
        UserEntity userEntity = fetchUserById(id);

        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(userRequest.getPassword());
        userRepository.save(userEntity);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return UserMapper.INSTANCE.buildUserResponse(fetchUserById(id));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return UserMapper.INSTANCE.buildUserResponses(userRepository.findAll());
    }

    private UserEntity fetchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
