package com.br.login.login.mapper;

import com.br.login.login.domain.User;
import com.br.login.login.dto.UserCreate;
import com.br.login.login.dto.UserView;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(target = "id",  ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User mapToUser(UserCreate userCreate);

    UserView mapToUserView(User user);
}
