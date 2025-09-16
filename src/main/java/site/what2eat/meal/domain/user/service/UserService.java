package site.what2eat.meal.domain.user.service;

import site.what2eat.meal.domain.user.dto.request.UserReqDto;

public interface UserService {

    void addUser(UserReqDto.UserCreateReqDto userCreateReqDto);
}
