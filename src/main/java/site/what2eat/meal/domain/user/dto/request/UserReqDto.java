package site.what2eat.meal.domain.user.dto.request;

public class UserReqDto {

    public record UserCreateReqDto(
            String phoneNumber,
            String verifyCode
    ) {

    }
}
