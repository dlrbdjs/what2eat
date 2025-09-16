package site.what2eat.meal.global.apiPayload.exception;

import lombok.Getter;
import site.what2eat.meal.global.apiPayload.code.BaseErrorCode;

@Getter
public class CustomException extends RuntimeException{

    private final BaseErrorCode code;

    public CustomException(BaseErrorCode errorCode) {
        this.code = errorCode;
    }
}