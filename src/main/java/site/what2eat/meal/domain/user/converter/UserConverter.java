package site.what2eat.meal.domain.user.converter;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import site.what2eat.meal.domain.user.dto.request.UserReqDto;
import site.what2eat.meal.domain.user.entity.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static User toUser(UserReqDto.UserCreateReqDto userCreateReqDto) {
        return User.builder()
                .phoneNumber(userCreateReqDto.phoneNumber())
                .isSent(false)
                .build();
    }
}
