package site.what2eat.meal.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.what2eat.meal.domain.user.dto.request.UserReqDto;
import site.what2eat.meal.domain.user.service.UserService;
import site.what2eat.meal.global.apiPayload.CustomResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "USER", description = "User 관련 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "전화 번호 추가")
    @PostMapping("/add")
    public CustomResponse<String> addUser(
            @RequestBody UserReqDto.UserCreateReqDto userCreateReqDto
    ) {
        userService.addUser(userCreateReqDto);
        return CustomResponse.onSuccess("전화번호 등록 성공");
    }
}
