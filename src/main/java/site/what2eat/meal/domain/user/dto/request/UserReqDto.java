package site.what2eat.meal.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static site.what2eat.meal.global.constant.Constant.*;

public class UserReqDto {

    public record UserCreateReqDto(
            @NotBlank(message = BLANK_PHONE_NUMBER)
            @Pattern(regexp = PHONE_NUMBER_PATTERN, message = WRONG_PHONE_NUMBER_PATTERN)
            String phoneNumber,
            String verifyCode
    ) {

    }
}
