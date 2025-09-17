package site.what2eat.meal.domain.message.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import site.what2eat.meal.domain.message.service.MessageService;
import site.what2eat.meal.domain.user.entity.User;
import site.what2eat.meal.domain.user.repository.UserRepository;
import site.what2eat.meal.global.jsoup.dto.MealPlan;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageScheduler {

    private final MessageService messageService;
    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 7 * * *")
    public void sendMealMessage() {
        log.info("모든 유저를 검색합니다.");
        List<User> users = userRepository.findAll();
        log.info("발견한 유저 수 = {}", users.size());

        log.info("식단표 정보를 요청합니다.");
        MealPlan mealPlan = messageService.getMealPlan();
        log.info("식단표 정보 수신 완료");

        log.info("식단표 정보로 메시지를 제작합니다.");
        String msg = messageService.createMsg(mealPlan);
        log.info("메시지 제작 완료");

        log.info("메시지 발송 시작");
        for (User user : users) {
            messageService.sendMessage(msg, user.getPhoneNumber());
        }
        log.info("메시지 발송 완료");
    }
}
