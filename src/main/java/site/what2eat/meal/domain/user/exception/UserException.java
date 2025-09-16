package site.what2eat.meal.domain.user.exception;

import site.what2eat.meal.global.apiPayload.exception.CustomException;

public class UserException extends CustomException {
    public UserException(UserErrorCode errorCode) {
        super(errorCode);
    }
}
