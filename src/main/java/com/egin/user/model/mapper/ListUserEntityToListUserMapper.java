package com.egin.user.model.mapper;

import com.egin.user.model.User;
import com.egin.user.model.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListUserEntityToListUserMapper {

    public List<User> toListUser(List<UserEntity> listUserEntity) {
        return listUserEntity.stream()
                .map(UserEntityToUserMapper::toUser)
                .toList();
    }


}
