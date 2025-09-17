package site.what2eat.meal.domain.message.service;

import site.what2eat.meal.global.jsoup.dto.MealPlan;

public interface MessageService {

    void sendMessage(String msg, String phoneNumber);

    MealPlan getMealPlan();

    String createMsg(MealPlan mealPlan);
}
