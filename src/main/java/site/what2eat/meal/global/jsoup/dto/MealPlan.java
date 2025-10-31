package site.what2eat.meal.global.jsoup.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MealPlan(
        String day,
        String date,
        List<String> breakfast,
        List<String> lunch,
        List<String> dinner
) {
}
