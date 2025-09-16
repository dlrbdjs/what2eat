package site.what2eat.meal.domain.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import site.what2eat.meal.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    USER_CONFLICT(HttpStatus.CONFLICT, "USER409_1", "유저 중복"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
