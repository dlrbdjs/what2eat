package site.what2eat.meal.domain.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.what2eat.meal.global.jsoup.MealPlanFetcher;
import site.what2eat.meal.global.jsoup.MealPlanParser;
import site.what2eat.meal.global.jsoup.dto.MealPlan;
import site.what2eat.meal.global.webclient.mealmessage.MealMessageClient;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MealMessageClient mealMessageClient;
    private final MealPlanFetcher mealPlanFetcher;
    private final MealPlanParser mealPlanParser;

    @Override
    public void sendMessage(String phoneNumber) {
        mealMessageClient.sendMessage("안녕", phoneNumber);
    }

    @Override
    public MealPlan getMealPlan() {
        try {
            return mealPlanParser.parse(mealPlanFetcher.fetchDocument());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
