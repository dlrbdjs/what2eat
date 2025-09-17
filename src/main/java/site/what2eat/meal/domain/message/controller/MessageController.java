package site.what2eat.meal.domain.message.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.what2eat.meal.domain.message.service.MessageService;
import site.what2eat.meal.global.apiPayload.CustomResponse;

//@RestController
//@RequiredArgsConstructor
public class MessageController {
//    private final MessageService messageService;
//
//    @PostMapping("/message")
//    public CustomResponse<String> sendMessage(
//            @RequestParam String phoneNumber
//    ) {
//        messageService.sendMessage("test", phoneNumber);
//        return CustomResponse.onSuccess("메시지 전송 성공");
//    }
//
//    @GetMapping("/meal-test")
//    public CustomResponse<?> getMealPlan() {
//        return CustomResponse.onSuccess(messageService.getMealPlan());
//    }
}
