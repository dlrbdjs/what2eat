package site.what2eat.meal.global.apiPayload.code;

import org.springframework.http.HttpStatus;
import site.what2eat.meal.global.apiPayload.CustomResponse;

public interface BaseErrorCode {

    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();

    default CustomResponse<Void> getErrorResponse() {
        return CustomResponse.onFailure(getCode(), getMessage());
    }
}
