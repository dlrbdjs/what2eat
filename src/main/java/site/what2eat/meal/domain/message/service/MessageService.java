package site.what2eat.meal.domain.message.service;

import site.what2eat.meal.global.jsoup.dto.MealPlan;

import java.time.LocalDate;
import java.util.List;

public interface MessageService {

    void sendMessage(String msg, String phoneNumber);

    void sendMessageTest(String msg, String phoneNumber);

    List<MealPlan> getMealPlan(int repeat);

    List<MealPlan> getMealPlanOnce(LocalDate date);

    String createMsg(List<MealPlan> mealPlans);
}
