package com.alinazim.bookexchange.mapper;

import com.alinazim.bookexchange.dao.entity.UserEntity;
import com.alinazim.bookexchange.model.request.UserRequest;
import com.alinazim.bookexchange.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity buildUserEntity(UserRequest userRequest);

    UserResponse buildUserResponse(UserEntity userEntity);

    List<UserEntity> buildUserEntities(List<UserRequest> userRequests);

    List<UserResponse> buildUserResponses(List<UserEntity> userEntities);
}
