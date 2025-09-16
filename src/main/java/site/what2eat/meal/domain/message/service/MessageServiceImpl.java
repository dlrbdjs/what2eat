package site.what2eat.meal.domain.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.what2eat.meal.global.webclient.MealMessageClient;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MealMessageClient mealMessageClient;

    @Override
    public void sendMessage(String phoneNumber) {
        mealMessageClient.sendMessage("안녕", phoneNumber);
    }
}
