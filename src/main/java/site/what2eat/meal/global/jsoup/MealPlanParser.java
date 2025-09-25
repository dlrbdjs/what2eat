package site.what2eat.meal.global.jsoup;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import site.what2eat.meal.global.jsoup.dto.MealPlan;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class MealPlanParser {
    public MealPlan parse(Document doc) {
        // 현재 날짜
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        String day = today.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        // html에서 검색할 수 있도록 포매팅

        Element breakfast = getMealPlanElement(doc, today, MealType.BREAKFAST);
        Element lunch = getMealPlanElement(doc, today, MealType.LUNCH);
        Element dinner = getMealPlanElement(doc, today, MealType.DINNER);
        List<String> breakfastMenus = getMealPlanList(breakfast);
        List<String> lunchMenus = getMealPlanList(lunch);
        List<String> dinnerMenus = getMealPlanList(dinner);

//        log.info("breakfastMenus: {}", breakfastMenus);
//        log.info("lunchMenus: {}", lunchMenus);
//        log.info("dinnerMenus: {}", dinnerMenus);

        return MealPlan.builder()
                .day(day)
                .date(String.valueOf(today))
                .breakfast(breakfastMenus)
                .lunch(lunchMenus)
                .dinner(dinnerMenus)
                .build();
    }

    private Element getMealPlanElement(Document doc, LocalDate date, MealType mealType) {
        // LocalDate 입력값을 String 으로 포매팅
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MM월 dd일"));

        // html 여러 테그 중에서 이런 식단 정보를 담는, 그런데 오늘 날짜인 Element를 반환
        return doc.selectFirst(
                "tr:has(td[data-mqtitle=date]:contains(" + formattedDate + ")) td[data-mqtitle=" + mealType.getType() + "]"
        );
    }

    private List<String> getMealPlanList(Element mealPlan) {
//        log.info("getMealPlanListElement: {}", mealPlan);
//        log.info("getMealPlanListHtml: {}", mealPlan.html());
//        log.info("getMealPlanListText: {}", mealPlan.text());
        // 파싱한 식단표에서 <br>을 기준으로 배열 생성
        return Arrays.stream(mealPlan.html().split("<br>"))
                // 배열의 각각의 요소가 만약에 괄호로 감싸져 있다면 제거
                .map(menu -> menu.replaceAll(("\\(.*?\\)"), "").trim())
                .map(menu -> menu.replaceAll("&amp;", "&").trim())
                // 괄호로 감싸져 있던 문자열들이 ""이 되었으므로 비어있는 ""들을 제거
                .filter(menu -> !menu.isEmpty())
                // 리스트로 반환
                .toList();
    }
}


