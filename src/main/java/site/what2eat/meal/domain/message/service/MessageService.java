package site.what2eat.meal.domain.message.service;

import site.what2eat.meal.global.jsoup.dto.MealPlan;

public interface MessageService {

    void sendMessage(String phoneNumber);

    MealPlan getMealPlan();
}
